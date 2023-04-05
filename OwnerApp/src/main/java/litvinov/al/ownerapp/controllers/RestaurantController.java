package litvinov.al.ownerapp.controllers;

import litvinov.al.domain.common.Cuisine;
import litvinov.al.domain.common.Restaurant;
import litvinov.al.ownerapp.pojo.PrincipalOwner;
import litvinov.al.ownerapp.repo.OwnerRepo;
import litvinov.al.ownerapp.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private OwnerRepo ownerRepo;

    @GetMapping
    public List<Cuisine.Type> getCreationWorkspace(){

        return Arrays
                .stream(Cuisine.Type.values())
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{name}")
    public Restaurant getRestaurantByName(@AuthenticationPrincipal PrincipalOwner owner,
                                          @PathVariable String name){
        return restaurantRepo
                .findRestaurantByOwner(owner.getEmail(), name);
    }

    @PostMapping("/create")
    public Restaurant createRestaurant(@AuthenticationPrincipal PrincipalOwner owner,
                                       @RequestBody Restaurant restaurant){
        restaurant.setOwner(
                ownerRepo.findOwnerByEmail(
                            owner.getEmail()));

        return restaurantRepo.save(restaurant);
    }
}
