package egoz.go.tz.helpdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import egoz.go.tz.helpdesk.models.Taasisi;

@Repository
public interface TaasisiRepository extends JpaRepository<Taasisi, Long> {
    
}
