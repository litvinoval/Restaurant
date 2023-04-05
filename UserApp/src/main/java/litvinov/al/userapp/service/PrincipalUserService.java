package litvinov.al.userapp.service;


import litvinov.al.domain.users.Role;
import litvinov.al.domain.users.User;
import litvinov.al.userapp.pojo.CustomUser;
import litvinov.al.userapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service
public class PrincipalUserService {
    @Autowired
    private UserRepo userRepo;
    public void loadPrincipalUserIntoDb(CustomUser customUser){
        Map<String, Object> attributes = customUser.getAttributes();
        User user = userRepo.findUserByEmail((String) attributes.get("email"));
        if(user == null){

            user = new User();
            user.setEmail((String) attributes.get("email"));
            user.setName((String) attributes.get("name"));
            user.setRoles(
                    convertToRole(
                            customUser.getAuthorities().iterator()));
        } else{
            customUser.setBookings(
                    user.getBookings());
        }
        user.setLastVisit(LocalDateTime.now());
        userRepo.save(user);
    }

    private List<Role> convertToRole(
            Iterator<? extends GrantedAuthority> it){

        List<Role> roles = new ArrayList<>();
        while(it.hasNext()){
            String value = it.next().getAuthority();
            roles.add(new Role(value));
        }
        return roles;
    }

}
