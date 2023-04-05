package litvinov.al.ownerapp.service;


import litvinov.al.domain.owners.Owner;
import litvinov.al.domain.owners.OwnerRole;
import litvinov.al.domain.users.Role;
import litvinov.al.domain.users.User;
import litvinov.al.ownerapp.pojo.PrincipalOwner;
import litvinov.al.ownerapp.repo.OwnerRepo;
import litvinov.al.ownerapp.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service
public class PrincipalOwnerService {
    @Autowired
    private OwnerRepo ownerRepo;
    @Autowired
    private RestaurantRepo restaurantRepo;

    public void loadPrincipalUserIntoDb(PrincipalOwner principalOwner){
        Map<String, Object> attributes = principalOwner.getAttributes();
        Owner owner = ownerRepo.findOwnerByEmail((String) attributes.get("email"));
        if(owner == null){
            owner = new Owner();
            owner.setEmail((String) attributes.get("email"));
            owner.setName((String) attributes.get("name"));
            owner.setOwnerRoles(
                    convertToRole(
                            principalOwner.getAuthorities().iterator()));
            ownerRepo.save(owner);
        }

    }

    private List<OwnerRole> convertToRole(
            Iterator<? extends GrantedAuthority> it){

        List<OwnerRole> roles = new ArrayList<>();
        while(it.hasNext()){
            String value = it.next().getAuthority();
            roles.add(new OwnerRole(value));
        }
        return roles;
    }

}
