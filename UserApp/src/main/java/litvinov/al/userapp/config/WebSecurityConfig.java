package litvinov.al.userapp.config;

import litvinov.al.userapp.pojo.CustomUser;
import litvinov.al.userapp.service.CustomOAuth2UserService;
import litvinov.al.userapp.service.PrincipalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    private PrincipalUserService principalUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/","/login**", "/oauth/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(oauthUserService)
                .and()
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            Authentication authentication)
                                                                throws IOException, ServletException {
                            CustomUser customUser = (CustomUser) authentication.getPrincipal();
                            principalUserService
                                    .loadPrincipalUserIntoDb(customUser);
                            response.sendRedirect("/main");
                        }
                    })
                .and()
                    .logout().
                        logoutSuccessUrl("https://google.com").permitAll();
    }


}