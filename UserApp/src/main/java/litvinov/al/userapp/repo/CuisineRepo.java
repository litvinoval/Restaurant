package litvinov.al.userapp.repo;

import litvinov.al.domain.common.Cuisine;
import org.springframework.data.repository.CrudRepository;

public interface CuisineRepo extends CrudRepository<Cuisine, String> {
    Iterable<Cuisine> findAll();
}
