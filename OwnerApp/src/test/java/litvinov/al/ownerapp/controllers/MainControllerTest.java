package litvinov.al.ownerapp.controllers;

import litvinov.al.ownerapp.pojo.PrincipalOwner;
import litvinov.al.ownerapp.repo.OwnerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class MainControllerTest {

    private MockMvc mockMvc;
    @Resource
    private WebApplicationContext context;
    @Autowired
    private OwnerRepo ownerRepo;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .alwaysDo(print())
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    void getRestaurants() throws Exception {
        PrincipalOwner owner = new PrincipalOwner();
        owner.setName("hi");
        owner.setEmail("alex.litvinov.200101@gmail.com");
        owner.setRoles(List.of(new SimpleGrantedAuthority("ROLE_USER")));
        mockMvc.perform(get("http://localhost:9000/main")
                .with(oauth2Login().oauth2User(owner)))
                .andExpect(status().isOk());
    }
}