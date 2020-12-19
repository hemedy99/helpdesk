package egoz.go.tz.helpdesk.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import egoz.go.tz.helpdesk.dtos.User.UserRequestDto;
import egoz.go.tz.helpdesk.dtos.User.UserResponseDto;
import egoz.go.tz.helpdesk.enums.UserStateEnum;
import egoz.go.tz.helpdesk.exceptions.NotFoundException;
import egoz.go.tz.helpdesk.models.Taasisi;
import egoz.go.tz.helpdesk.models.User;
import egoz.go.tz.helpdesk.services.TaasisiService;
import egoz.go.tz.helpdesk.services.UserService;
import egoz.go.tz.helpdesk.web.api.UserApi;

@RestController
public class UserController implements UserApi{


    @Autowired
    private UserService userService;

    @Autowired
    private TaasisiService tasService;
    
    @Override
    public ResponseEntity<UserResponseDto>saveUser(@Valid UserRequestDto userDto){
        
       Optional<Taasisi> tas = tasService.getTaasisiById(userDto.getTaasisiId());
       if(!tas.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        User user = modelMapper.map(userDto,User.class);
        user.setTaasisi(tas.get());
        userService.saveUser(user);
        UserResponseDto resp = modelMapper.map(user,UserResponseDto.class);
		return  ResponseEntity.ok(resp);
    }


    @Override
    public ResponseEntity<List<UserResponseDto>> listUsers(int page, int size)throws NotFoundException, JsonProcessingException {
      PageRequest pageRequest = PageRequest.of(page, size);
      List<User> user = userService.getUsers(pageRequest);
      ModelMapper modelMapper = new ModelMapper();
      modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
      List<UserResponseDto> users = user.stream().map(v -> modelMapper.map(v, UserResponseDto.class)).collect(Collectors.toList());
      return  ResponseEntity.ok(users);
    }
    

    @Override
	public ResponseEntity<UserResponseDto>getUser(Long id)throws NotFoundException, JsonProcessingException {
        Optional<User> user = userService.getUserById(id);
        if(!user.isPresent()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        UserResponseDto userDto = modelMapper.map(user.get(),UserResponseDto.class);
		return  ResponseEntity.ok(userDto);
    }
    
    @Override
    public ResponseEntity<UserResponseDto> updateUser(Long id, UserRequestDto userDto)
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
        UserResponseDto usr = modelMapper.map(oldUser,UserResponseDto.class);
      return ResponseEntity.ok(usr);
     }
    }

     @Override
    public ResponseEntity<UserResponseDto> updateUserStatus(Long id, UserStateEnum userState)
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
        UserResponseDto usr = modelMapper.map(oldUser,UserResponseDto.class);
      return ResponseEntity.ok(usr);
     }

     
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUserPassword(Long id, String password)
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
        UserResponseDto usr = modelMapper.map(oldUser,UserResponseDto.class);
      return ResponseEntity.ok(usr);
     }

     
    }
  
    @Override
	public ResponseEntity<UserResponseDto> deleteUser(Long id)
			throws NotFoundException {
    
        Optional<User> user = userService.getUserById(id);
        if(!user.isPresent()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }  else{
        userService.delete(user.get());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setAmbiguityIgnored(true);
        UserResponseDto userDto = modelMapper.map(user,UserResponseDto.class); 
		return ResponseEntity.ok(userDto);
       }

    
	}
}
