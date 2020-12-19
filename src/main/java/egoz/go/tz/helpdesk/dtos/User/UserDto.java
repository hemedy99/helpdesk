package egoz.go.tz.helpdesk.dtos.User;

import java.util.Set;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import egoz.go.tz.helpdesk.dtos.Request.RequestDto;
import egoz.go.tz.helpdesk.enums.UserStateEnum;
import egoz.go.tz.helpdesk.enums.UserTypeEnum;
import egoz.go.tz.helpdesk.models.Taasisi;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserTypeEnum userType ;
    private String firstName ;
    private String middleName ;
    private String lastName ;
    private String email ;
    private String userName ;
    private String password ;
    private String mobile ;

    @Enumerated(EnumType.STRING)
    private UserStateEnum userState ;
    private Taasisi taasisi;
    private Set<RequestDto> requests ;
}
