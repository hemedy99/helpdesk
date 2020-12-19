package egoz.go.tz.helpdesk.web;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import egoz.go.tz.helpdesk.dtos.Request.RequestRequestDto;
import egoz.go.tz.helpdesk.dtos.Request.RequestResponseDto;
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
    public ResponseEntity<RequestResponseDto>saveRequest(@Valid RequestRequestDto requestDto){
        
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        Request req = modelMapper.map(requestDto,Request.class);
        requestService.saveRequest(req);
        RequestResponseDto response = modelMapper.map(req,RequestResponseDto.class);
		return  ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<List<RequestResponseDto>>listRequests(int page,int size)
        throws NotFoundException, JsonProcessingException,RestClientException, ParseException{
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Request> reqs= requestService.getAllRequest(pageRequest);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
      List<RequestResponseDto> reqList =reqs.stream().map(v -> modelMapper.map(v, RequestResponseDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(reqList);
    }
}
