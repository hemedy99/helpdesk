package egoz.go.tz.helpdesk.dtos.User;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import egoz.go.tz.helpdesk.enums.UserStateEnum;
import egoz.go.tz.helpdesk.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

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

    private Long taasisiId;
}
