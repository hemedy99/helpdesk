package egoz.go.tz.helpdesk.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import egoz.go.tz.helpdesk.dtos.MinistryDto;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.Ministry;
import egoz.go.tz.helpdesk.services.MinistryService;
import egoz.go.tz.helpdesk.web.api.MinistryApi;

@RestController
public class MinistryController implements MinistryApi {
   
    @Autowired
    private MinistryService ministryService;

    @Override
    public ResponseEntity<MinistryDto>saveMinistry(@Valid MinistryDto ministryDto){
        
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        Ministry min = modelMapper.map(ministryDto,Ministry.class);
        ministryService.saveMinistry(min);
		return  ResponseEntity.ok(ministryDto);
    }


    @Override
    public ResponseEntity<List<Ministry>> listMinistries(int page, int size)throws NotFoundException, JsonProcessingException {
      PageRequest pageRequest = PageRequest.of(page, size);
      List<Ministry> min = ministryService.getMinistries(pageRequest);
      return  ResponseEntity.ok(min);
    }
    

    @Override
	public ResponseEntity<MinistryDto>getMinistry(Long id)throws NotFoundException, JsonProcessingException {
        Optional<Ministry> min = ministryService.getMinistryById(id);
        if(!min.isPresent()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        MinistryDto minDto = modelMapper.map(min.get(),MinistryDto.class);
		return  ResponseEntity.ok(minDto);
    }
    
    @Override
    public ResponseEntity<MinistryDto> updateMinistry(Long id, MinistryDto minDto)
        throws NotFoundException {
      
      Optional<Ministry> mn = ministryService.getMinistryById(id);
      if(!mn.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }else{

      Ministry oldMin = mn.get();
      oldMin.setMinistryName(minDto.getMinistryName());
     ministryService.saveMinistry(oldMin);
     ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        MinistryDto minDt = modelMapper.map(oldMin,MinistryDto.class);
      return ResponseEntity.ok(minDt);
     }

     
    }
  
    @Override
	public ResponseEntity<MinistryDto> deleteMinistry(Long id)
			throws NotFoundException {
    Ministry min = ministryService.delete(id);
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
    MinistryDto mDto = modelMapper.map(min,MinistryDto.class); 
		return ResponseEntity.ok(mDto);
	}

}

