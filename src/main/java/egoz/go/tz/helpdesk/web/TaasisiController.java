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

import egoz.go.tz.helpdesk.dtos.TaasisiDto;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.Taasisi;
import egoz.go.tz.helpdesk.services.TaasisiService;
import egoz.go.tz.helpdesk.web.api.TaasisiApi;

@RestController
public class TaasisiController implements TaasisiApi{
    @Autowired
    private TaasisiService taasisiService;

    @Override
    public ResponseEntity<TaasisiDto>saveTaasisi(@Valid TaasisiDto taasisiDto){
        
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        Taasisi tas = modelMapper.map(taasisiDto,Taasisi.class);
        taasisiService.saveTaasisi(tas);
		return  ResponseEntity.ok(taasisiDto);
    }



    @Override
    public ResponseEntity<List<Taasisi>> listTaasisi(int page, int size)throws NotFoundException, JsonProcessingException {
      PageRequest pageRequest = PageRequest.of(page, size);
      List<Taasisi> tas = taasisiService.getTaasisi(pageRequest);
      return  ResponseEntity.ok(tas);
    }
    

    @Override
	public ResponseEntity<TaasisiDto>getTaasisi(Long id)throws NotFoundException, JsonProcessingException {
        Optional<Taasisi> tas = taasisiService.getTaasisiById(id);
        if(!tas.isPresent()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        TaasisiDto tasDto = modelMapper.map(tas.get(),TaasisiDto.class);
		return  ResponseEntity.ok(tasDto);
    }
    
    @Override
    public ResponseEntity<TaasisiDto> updateTaasisi(Long id, TaasisiDto tasDto)
        throws NotFoundException {
      
      Optional<Taasisi> tas = taasisiService.getTaasisiById(id);
      if(!tas.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }else{

      Taasisi oldTas = tas.get();
      oldTas.setTaasisiName(tasDto.getTaasisiName());
      oldTas.setMinistry(tasDto.getMinistry());
     taasisiService.saveTaasisi(oldTas);
     ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        TaasisiDto tasDt = modelMapper.map(oldTas,TaasisiDto.class);
      return ResponseEntity.ok(tasDt);
     }

     
    }
  
    @Override
	public ResponseEntity<TaasisiDto> deleteTaasisi(Long id)
			throws NotFoundException {
    Taasisi tas = taasisiService.delete(id);
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
    TaasisiDto tasDto = modelMapper.map(tas,TaasisiDto.class); 
		return ResponseEntity.ok(tasDto);
	}
}
