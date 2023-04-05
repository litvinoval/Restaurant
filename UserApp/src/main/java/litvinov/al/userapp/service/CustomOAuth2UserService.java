package litvinov.al.userapp.service;

import litvinov.al.userapp.pojo.CustomUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User =  super.loadUser(userRequest);
        CustomUser customUser = new CustomUser();
        customUser.setName(
                oauth2User.getAttribute("name"));
        customUser.setEmail(
                oauth2User.getAttribute("email"));
        customUser.setRoles(
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        customUser.setLastVisit(
                LocalDateTime.now());
        return customUser;
    }


}