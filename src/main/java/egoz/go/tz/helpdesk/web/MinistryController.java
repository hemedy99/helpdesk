package egoz.go.tz.helpdesk.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import egoz.go.tz.helpdesk.dtos.Ministry.MinistryRequestDto;
import egoz.go.tz.helpdesk.dtos.Ministry.MinistryResponseDto;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.Ministry;
import egoz.go.tz.helpdesk.services.MinistryService;
import egoz.go.tz.helpdesk.web.api.MinistryApi;

@RestController
public class MinistryController implements MinistryApi {
   
    @Autowired
    private MinistryService ministryService;

    @Override
    public ResponseEntity<MinistryResponseDto>saveMinistry(@Valid MinistryRequestDto ministryDto){
        
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        Ministry min = modelMapper.map(ministryDto,Ministry.class);
        ministryService.saveMinistry(min);

        MinistryResponseDto response = modelMapper.map(min, MinistryResponseDto.class);
		return  ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<List<MinistryResponseDto>> listMinistries(int page, int size)throws NotFoundException, JsonProcessingException {
      PageRequest pageRequest = PageRequest.of(page, size);
      List<Ministry> min = ministryService.getMinistries(pageRequest);

      ModelMapper modelMapper = new ModelMapper();
      modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
      List<MinistryResponseDto> ministries = min.stream().map(v -> modelMapper.map(v, MinistryResponseDto.class)).collect(Collectors.toList());
      return  ResponseEntity.ok(ministries);
    }
    

    @Override
	public ResponseEntity<MinistryResponseDto>getMinistry(Long id)throws NotFoundException, JsonProcessingException {
        Optional<Ministry> min = ministryService.getMinistryById(id);
        if(!min.isPresent()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

       ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        MinistryResponseDto minDto = modelMapper.map(min.get(),MinistryResponseDto.class);
		return  ResponseEntity.ok(minDto);
    }
    
    @Override
    public ResponseEntity<MinistryResponseDto> updateMinistry(Long id, MinistryRequestDto minDto)
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
        MinistryResponseDto minDt = modelMapper.map(oldMin,MinistryResponseDto.class);
      return ResponseEntity.ok(minDt);
     }

     
    }
  
    @Override
	public ResponseEntity<MinistryResponseDto> deleteMinistry(Long id)
			throws NotFoundException {
        Optional<Ministry> mn = ministryService.getMinistryById(id);
      if(!mn.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }else{

      Ministry min = mn.get();
      ministryService.delete(min);
      ModelMapper modelMapper = new ModelMapper();
     modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
     MinistryResponseDto mDto = modelMapper.map(min,MinistryResponseDto.class); 
     return ResponseEntity.ok(mDto);
     }
    
	}

}

