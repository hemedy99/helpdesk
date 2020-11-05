package egoz.go.tz.helpdesk.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Taasisi {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taasisi_id_sequence")
  @SequenceGenerator(name = "taasisi_id", sequenceName = "taasisi_id_sequence",
      initialValue = 1, allocationSize = 1)
  @Column(name = "taasisiId", updatable = false, nullable = false)
  private Long id;

  @NotNull
  @NonNull
   @Column(name ="taasisiName")
  private String taasisiName;

  @ManyToOne (optional = false) 
  @JoinColumn(name = "ministryId", referencedColumnName = "ministryId")
  @JsonBackReference(value="ministry")
	@EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
  private Ministry ministry;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "taasisi")	
     @JsonManagedReference(value="user")
       @EqualsAndHashCode.Exclude
       @lombok.ToString.Exclude
       @JsonIgnore
     private Set<User> users ;
}
