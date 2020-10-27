package egoz.go.tz.helpdesk.services;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egoz.go.tz.helpdesk.dtos.TaasisiDto;
import egoz.go.tz.helpdesk.models.Taasisi;
import egoz.go.tz.helpdesk.repository.TaasisiRepository;

@Service
public class TaasisiService {
    @Autowired
    private TaasisiRepository taasisiRepo;

    public Taasisi saveTaasisi(@Valid TaasisiDto taasisiDto)  {
        ModelMapper modelMapper = new ModelMapper();
          modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
          Taasisi min = modelMapper.map(taasisiDto,Taasisi.class);
          min = taasisiRepo.save(min);
        return min;
      }
    
}
