package litvinov.al.ownerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@SpringBootApplication
@EntityScan("litvinov.al.domain")
public class OwnerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwnerAppApplication.class, args);
    }



}
