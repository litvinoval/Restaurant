package litvinov.al.userapp.controllers;


import litvinov.al.domain.common.Cuisine;
import litvinov.al.userapp.pojo.CustomUser;
import litvinov.al.userapp.repo.CuisineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/main")
public class MainController {
    @Autowired
    private CuisineRepo cuisineRepo;

    @GetMapping(produces = "application/json")
    public Iterable<Cuisine> getAllCuisines(){
        return cuisineRepo.findAll();
    }



}
