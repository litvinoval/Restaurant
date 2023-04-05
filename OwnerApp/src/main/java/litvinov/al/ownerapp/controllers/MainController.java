package litvinov.al.ownerapp.controllers;

import litvinov.al.domain.common.Restaurant;
import litvinov.al.ownerapp.pojo.PrincipalOwner;
import litvinov.al.ownerapp.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/main")
public class MainController {
    @Autowired
    private RestaurantRepo restaurantRepo;

    @GetMapping
    public Iterable<Restaurant> getRestaurants(
            @AuthenticationPrincipal PrincipalOwner owner){

        return restaurantRepo
                .findRestaurantsByOwner(owner.getEmail());
    }


}
