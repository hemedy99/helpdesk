package egoz.go.tz.helpdesk.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egoz.go.tz.helpdesk.dtos.TaasisiDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Taasisi-Management", description = "Manage Taasisi on the web")
@RequestMapping("/taasisi")
public interface TaasisiApi {
    @ApiOperation(value = "Save Taasisi", notes = "Save Taasisi")
    @RequestMapping(value = "/",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<TaasisiDto> saveMinistry(@RequestBody TaasisiDto taasisiDto);
    
}
