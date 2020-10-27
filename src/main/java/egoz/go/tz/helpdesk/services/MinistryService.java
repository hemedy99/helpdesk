package egoz.go.tz.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import egoz.go.tz.helpdesk.dtos.MinistryDto;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.Ministry;
import egoz.go.tz.helpdesk.repository.MinistryRepository;

@Service
public class MinistryService {

    @Autowired
    private MinistryRepository ministryRepo;


    public Ministry saveMinistry(@Valid MinistryDto ministryDto)  {
      ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        Ministry min = modelMapper.map(ministryDto,Ministry.class);
        min = ministryRepo.save(min);
      return min;
    }
  
    public List<Ministry> getMinistries(Pageable pagerequest) {
		return ministryRepo.findAll(pagerequest).getContent();
    }
 
    public Optional<Ministry> getMinistryById(Long id)  {
		Optional<Ministry> min = ministryRepo.findById(id);
		return min;
  }
  

  public Ministry updateMinistry(Long id, Ministry min)throws NotFoundException{
    Ministry ministry = ministryRepo.findById(id).orElseThrow(() -> new NotFoundException("Ministry not found"));
  
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    ministry = modelMapper.map(min, Ministry.class);
    ministry = ministryRepo.save(ministry);

return ministry;
}

public Ministry delete(Long id) throws NotFoundException {
  Ministry min = ministryRepo.findById(id)
      .orElseThrow(() -> new NotFoundException("Ministry not found"));

  ministryRepo.delete(min);
  return min;
}
}
