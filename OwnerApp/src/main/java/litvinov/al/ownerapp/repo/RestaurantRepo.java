package litvinov.al.ownerapp.repo;

import litvinov.al.domain.common.Restaurant;
import litvinov.al.domain.owners.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface RestaurantRepo extends CrudRepository<Restaurant, String> {

    @Query(value = "select '*' from Restaurant r join Owner o on o.email=:email")
    List<Restaurant> findRestaurantsByOwner(@Param(value = "email") String email);

    @Query(value = "select '*' from Restaurant r join Owner o on" +
            " o.email=:email where r.name=:name")
    Restaurant findRestaurantByOwner(@Param(value = "email") String email,
                                     @Param(value = "name") String name);
}
