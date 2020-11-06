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

import egoz.go.tz.helpdesk.dtos.UserDto;
import egoz.go.tz.helpdesk.enums.UserStateEnum;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.User;
import egoz.go.tz.helpdesk.services.UserService;
import egoz.go.tz.helpdesk.web.api.UserApi;

@RestController
public class UserController implements UserApi{


    @Autowired
    private UserService userService;
    
    @Override
    public ResponseEntity<UserDto>saveUser(@Valid UserDto userDto){
        
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        User user = modelMapper.map(userDto,User.class);
        userService.saveUser(user);
		return  ResponseEntity.ok(userDto);
    }


    @Override
    public ResponseEntity<List<User>> listUsers(int page, int size)throws NotFoundException, JsonProcessingException {
      PageRequest pageRequest = PageRequest.of(page, size);
      List<User> user = userService.getUsers(pageRequest);
      return  ResponseEntity.ok(user);
    }
    

    @Override
	public ResponseEntity<UserDto>getUser(Long id)throws NotFoundException, JsonProcessingException {
        Optional<User> user = userService.getUserById(id);
        if(!user.isPresent()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        UserDto userDto = modelMapper.map(user.get(),UserDto.class);
		return  ResponseEntity.ok(userDto);
    }
    
    @Override
    public ResponseEntity<UserDto> updateUser(Long id, UserDto userDto)
        throws NotFoundException {
      
      Optional<User> user = userService.getUserById(id);
      if(!user.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }else{

      User oldUser = user.get();

      Optional.ofNullable(userDto.getUserType()).ifPresent(d -> oldUser.setUserType(d));
      Optional.ofNullable(userDto.getFirstName()).ifPresent(d -> oldUser.setFirstName(d));
      Optional.ofNullable(userDto.getMiddleName()).ifPresent(d -> oldUser.setMiddleName(d));  
      Optional.ofNullable(userDto.getLastName()).ifPresent(d -> oldUser.setLastName(d)); 
      Optional.ofNullable(userDto.getEmail()).ifPresent(d -> oldUser.setEmail(d));
      Optional.ofNullable(userDto.getUserName()).ifPresent(d -> oldUser.setUserName(d));
      Optional.ofNullable(userDto.getMobile()).ifPresent(d -> oldUser.setMobile(d));  
      
     userService.saveUser(oldUser);
     ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        UserDto usr = modelMapper.map(oldUser,UserDto.class);
      return ResponseEntity.ok(usr);
     }
    }

     @Override
    public ResponseEntity<UserDto> updateUserStatus(Long id, UserStateEnum userState)
        throws NotFoundException {
      
      Optional<User> user = userService.getUserById(id);
      if(!user.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }else{

      User oldUser = user.get();
      oldUser.setUserState(userState);  
      
     userService.saveUser(oldUser);
     ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        UserDto usr = modelMapper.map(oldUser,UserDto.class);
      return ResponseEntity.ok(usr);
     }

     
    }

    @Override
    public ResponseEntity<UserDto> updateUserPassword(Long id, String password)
        throws NotFoundException {
      
      Optional<User> user = userService.getUserById(id);
      if(!user.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }else{

      User oldUser = user.get();
      oldUser.setPassword(password);  
      
     userService.saveUser(oldUser);
     ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        UserDto usr = modelMapper.map(oldUser,UserDto.class);
      return ResponseEntity.ok(usr);
     }

     
    }
  
    @Override
	public ResponseEntity<UserDto> deleteUser(Long id)
			throws NotFoundException {
    User user = userService.delete(id);
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
    UserDto userDto = modelMapper.map(user,UserDto.class); 
		return ResponseEntity.ok(userDto);
	}
}
