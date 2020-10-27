package egoz.go.tz.helpdesk.web;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import egoz.go.tz.helpdesk.dtos.TaasisiDto;
import egoz.go.tz.helpdesk.models.Taasisi;
import egoz.go.tz.helpdesk.services.TaasisiService;
import egoz.go.tz.helpdesk.web.api.TaasisiApi;

public class TaasisiController implements TaasisiApi{
    @Autowired
    private TaasisiService taasisiService;

    @Override
    public ResponseEntity<TaasisiDto>saveMinistry(@Valid TaasisiDto taasisiDto){
        Taasisi tas = taasisiService.saveTaasisi(taasisiDto);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        TaasisiDto tasDto = modelMapper.map(tas,TaasisiDto.class);
		return  ResponseEntity.ok(tasDto);
    }
}
