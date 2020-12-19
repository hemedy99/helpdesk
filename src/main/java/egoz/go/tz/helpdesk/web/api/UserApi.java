package egoz.go.tz.helpdesk.web.api;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egoz.go.tz.helpdesk.dtos.User.UserRequestDto;
import egoz.go.tz.helpdesk.dtos.User.UserResponseDto;
import egoz.go.tz.helpdesk.enums.UserStateEnum;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "User-Management", description = "Manage Users on the web")
@RequestMapping("/user")
public interface UserApi {
    @ApiOperation(value = "Save User", notes = "Save User")
    @RequestMapping(value = "/",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userDto);
    
    @ApiOperation(value = "Gets a list of All Users", notes = "Gets a list of All Users")
    @RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<UserResponseDto>>listUsers(
    @RequestParam(defaultValue = "0", required = false) int page,
    @RequestParam(defaultValue = "10", required = false) int size)throws NotFoundException, JsonProcessingException;


    @ApiOperation(value = "View User By Id", notes = "View User By Id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserResponseDto>getUser(@RequestParam Long id)throws NotFoundException, JsonProcessingException;

  
    @ApiOperation(value = "Update User", notes = "Update User")	  
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json") 
     public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,@RequestBody UserRequestDto userDto)
    throws NotFoundException, JsonProcessingException;     

    @ApiOperation(value = "Change User State", notes = "Change User State")	  
    @RequestMapping(value = "/update-status/{id}", method = RequestMethod.PUT, produces = "application/json") 
     public ResponseEntity<UserResponseDto> updateUserStatus(@PathVariable Long id,@RequestParam(name = "userStatus") UserStateEnum userState)
    throws NotFoundException, JsonProcessingException;  

    @ApiOperation(value = "Change User Password", notes = "Change User Password")	  
    @RequestMapping(value = "/update-password/{id}", method = RequestMethod.PUT, produces = "application/json") 
     public ResponseEntity<UserResponseDto> updateUserPassword(@PathVariable Long id,@RequestParam(name = "password")String password)
    throws NotFoundException, JsonProcessingException;  
    
    @ApiOperation(value = "Delete User", notes = "Delete User")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<UserResponseDto>deleteUser(@RequestParam Long id)
			throws NotFoundException;
}
