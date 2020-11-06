package egoz.go.tz.helpdesk.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import egoz.go.tz.helpdesk.enums.RequestCategoryEnum;
import egoz.go.tz.helpdesk.enums.RequestPriorityEnum;
import egoz.go.tz.helpdesk.enums.RequestStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_sequence")
    @SequenceGenerator(name = "request_id", sequenceName = "request_sequence",initialValue = 1, allocationSize = 1)
    @Column(name = "requestId", updatable = false, nullable = false)
    private Long id;  

    @Column(name ="requestStatus")
    @Enumerated(EnumType.STRING)
     private RequestStatusEnum requestStatus ;

     @Column(name ="requestPriority")
     @Enumerated(EnumType.STRING)
      private RequestPriorityEnum requestPriority ;

      @Column(name ="requestCategory")
     @Enumerated(EnumType.STRING)
      private RequestCategoryEnum requestCategory ;

      @Column(name ="subject")
      private String subject;

      @Column(name ="description")
      private String description;

      @Column(name ="createdDate")
      private LocalDateTime createdDate;

      @Column(name ="dueDate")
      private LocalDateTime dueDate;

      @ManyToOne (optional = false) 
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @JsonBackReference(value="user")
	@EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
    private User user;


}
