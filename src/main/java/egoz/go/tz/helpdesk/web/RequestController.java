package egoz.go.tz.helpdesk.web;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import egoz.go.tz.helpdesk.dtos.RequestDto;
import egoz.go.tz.helpdesk.enums.RequestCategoryEnum;
import egoz.go.tz.helpdesk.enums.RequestPriorityEnum;
import egoz.go.tz.helpdesk.enums.RequestStatusEnum;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.Request;
import egoz.go.tz.helpdesk.services.RequestService;
import egoz.go.tz.helpdesk.web.api.RequestApi;

@RestController
public class RequestController implements RequestApi {
    @Autowired
    private RequestService requestService;

    @Override
    public ResponseEntity<RequestDto>saveRequest(@Valid RequestDto requestDto){
        
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        Request req = modelMapper.map(requestDto,Request.class);
        requestService.saveRequest(req);
		return  ResponseEntity.ok(requestDto);
    }


    @Override
    public ResponseEntity<List<Request>>listRequests(
        int page,
        int size,
        RequestStatusEnum requestStatus,
         RequestPriorityEnum requestPriority,
         RequestCategoryEnum requestCategory, LocalDateTime fromDate,LocalDateTime toDate)
        throws NotFoundException, JsonProcessingException,RestClientException, ParseException{
        return ResponseEntity.ok(requestService.getAllRequests(requestStatus,requestPriority,requestCategory,fromDate,toDate,page,size));
    }
}
