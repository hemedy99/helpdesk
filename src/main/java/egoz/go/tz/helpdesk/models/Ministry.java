package egoz.go.tz.helpdesk.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ministry_id_sequence")
  @SequenceGenerator(name = "ministry_id", sequenceName = "ministry_id_sequence",initialValue = 1, allocationSize = 1)
  @Column(name = "ministry_id", updatable = false, nullable = false)
  private Long id;

  @NotNull
  @NonNull
   @Column(name ="ministry_name")
  private String ministryName;

  // public void mapWithDto(MinistryDto ministryDto){
  //   this.id=ministryDto.getId();
  //   this.ministryName=ministryDto.getMinistryName();
  // }

  
}
