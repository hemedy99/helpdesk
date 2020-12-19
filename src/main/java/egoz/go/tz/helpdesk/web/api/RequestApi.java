package egoz.go.tz.helpdesk.web.api;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import egoz.go.tz.helpdesk.dtos.Request.RequestRequestDto;
import egoz.go.tz.helpdesk.dtos.Request.RequestResponseDto;
import egoz.go.tz.helpdesk.enums.RequestCategoryEnum;
import egoz.go.tz.helpdesk.enums.RequestPriorityEnum;
import egoz.go.tz.helpdesk.enums.RequestStatusEnum;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.Request;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Request-Management", description = "Manage Requests on the web")
@RequestMapping("/requests")
public interface RequestApi {
    
    @ApiOperation(value = "Save Request", notes = "Save Request")
    @RequestMapping(value = "/",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<RequestResponseDto> saveRequest(@RequestBody RequestRequestDto requestDto);
    
    @ApiOperation(value = "Gets a list of All Request", notes = "Gets a list of All Request")
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<RequestResponseDto>>listRequests(
    @RequestParam(defaultValue = "0", required = false) int page,
    @RequestParam(defaultValue = "10", required = false) int size)
    // @RequestParam( defaultValue = "",required = false) RequestStatusEnum requestStatus,
    // @RequestParam(defaultValue = "",required = false) RequestPriorityEnum requestPriority,
    // @RequestParam(defaultValue = "",required = false) RequestCategoryEnum requestCategory,
    // @RequestParam(defaultValue = "", required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime fromDate,
    // @RequestParam(defaultValue = "", required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime toDate)
    throws NotFoundException, JsonProcessingException,RestClientException, ParseException;

}
