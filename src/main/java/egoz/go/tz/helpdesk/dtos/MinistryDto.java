package egoz.go.tz.helpdesk.dtos;

import java.util.Set;

import javax.validation.constraints.NotNull;

import egoz.go.tz.helpdesk.models.Taasisi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinistryDto {

private Long id;
private String ministryName;
private Set<Taasisi> taasisi;
    
}
