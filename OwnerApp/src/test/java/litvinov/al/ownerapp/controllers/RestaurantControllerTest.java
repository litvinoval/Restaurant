package litvinov.al.ownerapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import litvinov.al.domain.common.Restaurant;
import litvinov.al.ownerapp.pojo.PrincipalOwner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class RestaurantControllerTest {
    private MockMvc mockMvc;
    @Resource
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .alwaysDo(print())
                .apply(springSecurity())
                .build();
    }
    @Test
    void createRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setId("ODUV");
        restaurant.setName("gvozdika");
        PrincipalOwner owner = new PrincipalOwner();
        owner.setName("hi");
        owner.setEmail("example@mail.ru");
        owner.setRoles(List.of(new SimpleGrantedAuthority("ROLE_USER")));
        String cont = objectMapper.writeValueAsString(restaurant);
        System.out.println(restaurant);
        mockMvc.perform(
                post("http://localhost:9000/restaurant/create")
                        .contentType(MediaType.APPLICATION_JSON).content(cont)
                .with(oauth2Login().oauth2User(owner)))
                .andExpect(status().isCreated());
    }

    @Test
    void getCreationWorkspace() throws Exception {
        PrincipalOwner owner = new PrincipalOwner();
        owner.setName("hi");
        owner.setEmail("alex.litvinov.200101@gmail.com");
        owner.setRoles(List.of(new SimpleGrantedAuthority("ROLE_CREATOR")));
        mockMvc.perform(get("http://localhost:9000/restaurant")
                        .with(oauth2Login().oauth2User(owner)))
                .andExpect(status().isOk());
    }
}