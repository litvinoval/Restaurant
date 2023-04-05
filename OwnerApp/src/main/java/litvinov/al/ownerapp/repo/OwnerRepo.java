package litvinov.al.ownerapp.repo;

import litvinov.al.domain.owners.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OwnerRepo extends CrudRepository<Owner, Long> {

    Owner findOwnerByEmail(String email);
}
