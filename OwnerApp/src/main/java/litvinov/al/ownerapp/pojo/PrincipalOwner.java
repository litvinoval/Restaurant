package litvinov.al.ownerapp.pojo;


import litvinov.al.domain.common.Booking;
import litvinov.al.domain.common.Restaurant;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
public class PrincipalOwner implements OAuth2User, Serializable{
    private String email;
    private String name;
    private LocalDateTime lastVisit;
    private Collection<GrantedAuthority> roles;


    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("email", email);
        attributes.put("name", name);
        attributes.put("lastVisit", lastVisit);
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return this.name + " " + this.email;
    }
}

