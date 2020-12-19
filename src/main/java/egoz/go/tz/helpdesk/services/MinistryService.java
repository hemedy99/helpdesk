package egoz.go.tz.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import egoz.go.tz.helpdesk.models.Ministry;
import egoz.go.tz.helpdesk.repository.MinistryRepository;

@Service
public class MinistryService {

    @Autowired
    private MinistryRepository ministryRepo;


    public Ministry saveMinistry(@Valid Ministry ministry)  {
      return ministryRepo.save(ministry);
    }
  
    public List<Ministry> getMinistries(Pageable pagerequest) {
		return ministryRepo.findAll(pagerequest).getContent();
    }
 
    public Optional<Ministry> getMinistryById(Long id)  {
		Optional<Ministry> min = ministryRepo.findById(id);
		return min;
  }
  

public Ministry delete(Ministry min) {
  ministryRepo.delete(min);
  return min;
}
}
