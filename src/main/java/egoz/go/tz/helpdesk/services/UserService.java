package egoz.go.tz.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.User;
import egoz.go.tz.helpdesk.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User saveUser(@Valid User user)  {
        return userRepo.save(user);
    }
    
      public List<User> getUsers(Pageable pagerequest) {
		return userRepo.findAll(pagerequest).getContent();
    }

    public Optional<User> getUserById(Long id)  {
		Optional<User> user = userRepo.findById(id);
		return user;
    }

    public User delete(Long id) throws NotFoundException {
        User user = userRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found"));
      
            userRepo.delete(user);
        return user;
      }
}
