package egoz.go.tz.helpdesk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import egoz.go.tz.helpdesk.models.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {


    
}
