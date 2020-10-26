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

import egoz.go.tz.helpdesk.dtos.ApiResponse;
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
        Ministry min = ministryService.saveMinistry(ministryDto);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        MinistryDto minDto = modelMapper.map(min,MinistryDto.class);
		return  ResponseEntity.ok(minDto);
    }


    @Override
    public ResponseEntity<List<Ministry>> listMinistries(int page, int size)throws NotFoundException, JsonProcessingException {
      PageRequest pageRequest = PageRequest.of(page, size);
      List<Ministry> min = ministryService.getMinistries(pageRequest);
      return  ResponseEntity.ok(min);
    }
    

    @Override
	public ResponseEntity<MinistryDto>getMinistry(String id)throws NotFoundException, JsonProcessingException {
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
    public ResponseEntity<MinistryDto> updateMinistry(String id, MinistryDto minDto)
        throws NotFoundException {
      ModelMapper modelMapper = new ModelMapper();
      modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
      Ministry min = modelMapper.map(minDto,Ministry.class);   
      
     Ministry ministry = ministryService.updateMinistry(id, min);
     MinistryDto mDto = modelMapper.map(ministry,MinistryDto.class);  
      return ResponseEntity.ok(mDto);
    }
  
    @Override
	public ResponseEntity<MinistryDto> deleteMinistry(String id)
			throws NotFoundException {
    Ministry min = ministryService.delete(id);
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
    MinistryDto mDto = modelMapper.map(min,MinistryDto.class); 
		return ResponseEntity.ok(mDto);
	}

}

