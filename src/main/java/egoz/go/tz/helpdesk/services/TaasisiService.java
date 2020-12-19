package egoz.go.tz.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.Taasisi;
import egoz.go.tz.helpdesk.repository.TaasisiRepository;

@Service
public class TaasisiService {
    @Autowired
    private TaasisiRepository taasisiRepo;

    public Taasisi saveTaasisi(@Valid Taasisi taasisi)  {
        return taasisiRepo.save(taasisi);
      }

      public List<Taasisi> getTaasisi(Pageable pagerequest) {
        return taasisiRepo.findAll(pagerequest).getContent();
        }
     
        public Optional<Taasisi> getTaasisiById(Long id)  {
        Optional<Taasisi> tas = taasisiRepo.findById(id);
        return tas;
      }
      
    
    public Taasisi delete(Taasisi tas)  {
          taasisiRepo.delete(tas);
      return tas;
    }
    
}
