package egoz.go.tz.helpdesk.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "taasisi_id", updatable = false, nullable = false)
  private String id; 

  @NotNull
  @NonNull
   @Column(name ="taasisi_name")
  private String taasisiName;

  @ManyToOne(optional = false)
  @JoinColumn(name = "ministry_id", referencedColumnName = "ministry_id")
  @JsonBackReference(value = "taasisi")
  @EqualsAndHashCode.Exclude
  @lombok.ToString.Exclude
  // @JsonIgnore
  Ministry ministry;
}
