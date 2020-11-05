package egoz.go.tz.helpdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import egoz.go.tz.helpdesk.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
