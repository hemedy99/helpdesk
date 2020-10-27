package egoz.go.tz.helpdesk.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taasisi_id_sequence")
  @SequenceGenerator(name = "taasisi_id", sequenceName = "taasisi_id_sequence",
      initialValue = 1, allocationSize = 1)
  @Column(name = "taasisi_id", updatable = false, nullable = false)
  private Long id;

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
