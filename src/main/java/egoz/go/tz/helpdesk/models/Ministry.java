package egoz.go.tz.helpdesk.models;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ministry")
public class Ministry {
    
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ministry_id_sequence")
  @SequenceGenerator(name = "ministry_id", sequenceName = "ministry_id_sequence",initialValue = 1, allocationSize = 1)
  @Column(name = "ministryId", updatable = false, nullable = false)
  private Long id;


   @Column(name ="ministryName")
  private String ministryName;


  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ministry")	
  @JsonManagedReference(value="ministry")
	@EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
	@JsonIgnore
  private Set<Taasisi> taasisi ;

  // public void mapWithDto(MinistryDto ministryDto){
  //   this.id=ministryDto.getId();
  //   this.ministryName=ministryDto.getMinistryName();
  // }

  
}
