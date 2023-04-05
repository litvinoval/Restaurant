package litvinov.al.ownerapp.service;

import litvinov.al.ownerapp.pojo.PrincipalOwner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomOAuth2OwnerService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User =  super.loadUser(userRequest);
        PrincipalOwner owner = new PrincipalOwner();
        owner.setName(
                oauth2User.getAttribute("name"));
        owner.setEmail(
                oauth2User.getAttribute("email"));
        owner.setRoles(
                List.of(new SimpleGrantedAuthority("ROLE_CREATOR")));
        return owner;
    }


}