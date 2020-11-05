package egoz.go.tz.helpdesk.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import egoz.go.tz.helpdesk.enums.UserStateEnum;
import egoz.go.tz.helpdesk.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    @SequenceGenerator(name = "user_id", sequenceName = "user_id_sequence",initialValue = 1, allocationSize = 1)
    @Column(name = "userId", updatable = false, nullable = false)
    private Long id;  

    @Column(name ="userType")
    @Enumerated(EnumType.STRING)
     private UserTypeEnum userType ;


     @Column(name ="firstName")
     private String firstName ;

     @Column(name ="middleName")
     private String middleName ;

     @Column(name ="lastName")
     private String lastName ;

     @Column(name ="email")
     private String email ;

     @Column(name ="userName")
     private String userName ;

     @Column(name ="password")
     private String password ;

     @Column(name ="mobile")
     private String mobile ;

     @Column(name ="userState")
     @Enumerated(EnumType.STRING)
     private UserStateEnum userState ;

     @ManyToOne (optional = false) 
    @JoinColumn(name = "taasisiId", referencedColumnName = "taasisiId")
    @JsonBackReference(value="user")
	@EqualsAndHashCode.Exclude
	@lombok.ToString.Exclude
    private Taasisi taasisi;


     

}
