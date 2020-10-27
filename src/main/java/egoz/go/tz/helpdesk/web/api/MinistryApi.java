package egoz.go.tz.helpdesk.web.api;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egoz.go.tz.helpdesk.dtos.MinistryDto;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.Ministry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Ministry-Management", description = "Manage Ministry on the web")
@RequestMapping("/ministry")
public interface MinistryApi {

    @ApiOperation(value = "Save Ministry", notes = "Save Ministry")
    @RequestMapping(value = "/",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<MinistryDto> saveMinistry(@RequestBody MinistryDto ministryDto);
    

    @ApiOperation(value = "Gets a list of All Ministries", notes = "Gets a list of All Ministries")
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<Ministry>>listMinistries(
    @RequestParam(defaultValue = "0", required = false) int page,
    @RequestParam(defaultValue = "10", required = false) int size)throws NotFoundException, JsonProcessingException;


    @ApiOperation(value = "View Ministry By Id", notes = "View Ministry By Id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<MinistryDto>getMinistry(@RequestParam Long id)throws NotFoundException, JsonProcessingException;

  
    @ApiOperation(value = "Update Ministry", notes = "Update Ministry")	  
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json") 
     public ResponseEntity<MinistryDto> updateMinistry(@PathVariable Long id,@RequestBody MinistryDto minDto)
    throws NotFoundException, JsonProcessingException;     
    
    @ApiOperation(value = "Delete Ministry", notes = "Delete Ministry")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<MinistryDto>deleteMinistry(@RequestParam Long id)
			throws NotFoundException;
 
}
