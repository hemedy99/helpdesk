package egoz.go.tz.helpdesk.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import egoz.go.tz.helpdesk.dtos.MinistryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ministry")
public class Ministry {
    
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "ministry_id", updatable = false, nullable = false)
  private String id; 

  @NotNull
  @NonNull
   @Column(name ="ministry_name")
  private String ministryName;

  // public void mapWithDto(MinistryDto ministryDto){
  //   this.id=ministryDto.getId();
  //   this.ministryName=ministryDto.getMinistryName();
  // }

  
}
