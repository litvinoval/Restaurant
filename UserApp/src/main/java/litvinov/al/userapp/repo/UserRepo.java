package litvinov.al.userapp.repo;


import litvinov.al.domain.users.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
}
