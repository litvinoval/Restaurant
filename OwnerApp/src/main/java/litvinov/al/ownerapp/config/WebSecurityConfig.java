package litvinov.al.ownerapp.config;

import litvinov.al.ownerapp.pojo.PrincipalOwner;
import litvinov.al.ownerapp.service.CustomOAuth2OwnerService;
import litvinov.al.ownerapp.service.PrincipalOwnerService;
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
    private CustomOAuth2OwnerService oauthOwnerService;

    @Autowired
    private PrincipalOwnerService principalOwnerService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/","/login**", "/oauth/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(oauthOwnerService)
                .and()
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            Authentication authentication)
                                                                throws IOException, ServletException {
                            PrincipalOwner principalOwner = (PrincipalOwner) authentication.getPrincipal();
                            principalOwnerService
                                    .loadPrincipalUserIntoDb(principalOwner);
                            response.sendRedirect("/restaurant");
                        }
                    })
                .and()
                    .logout().
                        logoutSuccessUrl("https://google.com").permitAll()
                .and()
                    .csrf().disable();
    }


}