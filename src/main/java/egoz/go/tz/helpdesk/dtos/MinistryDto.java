package egoz.go.tz.helpdesk.dtos;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinistryDto {

private String id;

@NotNull(message = "Ministry Name is Required") 
private String ministryName;
    
}
