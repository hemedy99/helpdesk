package egoz.go.tz.helpdesk.dtos;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import egoz.go.tz.helpdesk.enums.RequestCategoryEnum;
import egoz.go.tz.helpdesk.enums.RequestPriorityEnum;
import egoz.go.tz.helpdesk.enums.RequestStatusEnum;
import egoz.go.tz.helpdesk.models.User;
import lombok.Data;

@Data
public class RequestDto {
    private Long id; 
    @Enumerated(EnumType.STRING)
     private RequestStatusEnum requestStatus ;

     @Enumerated(EnumType.STRING)
      private RequestPriorityEnum requestPriority ;

      @Enumerated(EnumType.STRING)
      private RequestCategoryEnum requestCategory ;

      private String subject;

      private String description;

      private LocalDateTime createdDate;

      private LocalDateTime dueDate;

      private User user;
      
}
