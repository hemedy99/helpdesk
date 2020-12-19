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

import egoz.go.tz.helpdesk.dtos.Taasisi.TaasisiRequestDto;
import egoz.go.tz.helpdesk.dtos.Taasisi.TaasisiResponseDto;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.Ministry;
import egoz.go.tz.helpdesk.models.Taasisi;
import egoz.go.tz.helpdesk.services.MinistryService;
import egoz.go.tz.helpdesk.services.TaasisiService;
import egoz.go.tz.helpdesk.web.api.TaasisiApi;

@RestController
public class TaasisiController implements TaasisiApi{
    @Autowired
    private TaasisiService taasisiService;

    @Autowired
    private MinistryService minService;
    @Override
    public ResponseEntity<TaasisiResponseDto>saveTaasisi(@Valid TaasisiRequestDto taasisiDto){
        
      Optional<Ministry> min = minService.getMinistryById(taasisiDto.getMinistryId());
       if(!min.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        Taasisi tas = modelMapper.map(taasisiDto,Taasisi.class);
        tas.setMinistry(min.get());
        taasisiService.saveTaasisi(tas);
        TaasisiResponseDto tResponse= modelMapper.map(tas,TaasisiResponseDto.class);
		return  ResponseEntity.ok(tResponse);
    }



    @Override
    public ResponseEntity<List<TaasisiResponseDto>> listTaasisi(int page, int size)throws NotFoundException, JsonProcessingException {
      PageRequest pageRequest = PageRequest.of(page, size);
      List<Taasisi> tas = taasisiService.getTaasisi(pageRequest);
      ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
      List<TaasisiResponseDto> taslist =tas.stream().map(v -> modelMapper.map(v, TaasisiResponseDto.class)).collect(Collectors.toList());
      return  ResponseEntity.ok(taslist);
    }
    

    @Override
	public ResponseEntity<TaasisiResponseDto>getTaasisi(Long id)throws NotFoundException, JsonProcessingException {
        Optional<Taasisi> tas = taasisiService.getTaasisiById(id);
        if(!tas.isPresent()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        TaasisiResponseDto tasDto = modelMapper.map(tas.get(),TaasisiResponseDto.class);
		return  ResponseEntity.ok(tasDto);
    }
    
    @Override
    public ResponseEntity<TaasisiResponseDto> updateTaasisi(Long id, TaasisiRequestDto tasDto)
        throws NotFoundException {
      
      Optional<Taasisi> tas = taasisiService.getTaasisiById(id);
      if(!tas.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }else{

      Taasisi oldTas = tas.get();
      oldTas.setTaasisiName(tasDto.getTaasisiName());

      Optional<Ministry> min = minService.getMinistryById(tasDto.getMinistryId());
        if(!min.isPresent()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
      oldTas.setMinistry(min.get());
     taasisiService.saveTaasisi(oldTas);
     ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        TaasisiResponseDto tasDt = modelMapper.map(oldTas,TaasisiResponseDto.class);
      return ResponseEntity.ok(tasDt);
     }

     
    }
  
    @Override
	public ResponseEntity<TaasisiResponseDto> deleteTaasisi(Long id)
			throws NotFoundException {

        Optional<Taasisi> tas = taasisiService.getTaasisiById(id);
        if(!tas.isPresent()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }else{
        taasisiService.delete(tas.get());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        TaasisiResponseDto tasDto = modelMapper.map(tas,TaasisiResponseDto.class); 
		return ResponseEntity.ok(tasDto);
       }

    
	}
}
