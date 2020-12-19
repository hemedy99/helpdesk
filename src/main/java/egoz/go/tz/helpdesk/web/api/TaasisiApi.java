package egoz.go.tz.helpdesk.web.api;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egoz.go.tz.helpdesk.dtos.Taasisi.TaasisiRequestDto;
import egoz.go.tz.helpdesk.dtos.Taasisi.TaasisiResponseDto;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.Taasisi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Taasisi-Management", description = "Manage Taasisi on the web")
@RequestMapping("/taasisi")
public interface TaasisiApi {
    @ApiOperation(value = "Save Taasisi", notes = "Save Taasisi")
    @RequestMapping(value = "/",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<TaasisiResponseDto> saveTaasisi(@RequestBody TaasisiRequestDto taasisiDto);
    
    @ApiOperation(value = "Gets a list of All Taasisi", notes = "Gets a list of All Taasisi")
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<TaasisiResponseDto>>listTaasisi(
    @RequestParam(defaultValue = "0", required = false) int page,
    @RequestParam(defaultValue = "10", required = false) int size)throws NotFoundException, JsonProcessingException;

    @ApiOperation(value = "View Taasisi By Id", notes = "View Taasisi By Id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<TaasisiResponseDto>getTaasisi(@RequestParam Long id)throws NotFoundException, JsonProcessingException;

  
    @ApiOperation(value = "Update Taasisi", notes = "Update Taasisi")	  
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json") 
     public ResponseEntity<TaasisiResponseDto> updateTaasisi(@PathVariable Long id,@RequestBody TaasisiRequestDto taasisiDto)
    throws NotFoundException, JsonProcessingException;     
    
    @ApiOperation(value = "Delete Taasisi", notes = "Delete Taasisi")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<TaasisiResponseDto>deleteTaasisi(@RequestParam Long id)
			throws NotFoundException;
}
