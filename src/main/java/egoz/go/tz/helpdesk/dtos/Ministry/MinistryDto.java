package egoz.go.tz.helpdesk.dtos.Ministry;

import java.util.Set;

import egoz.go.tz.helpdesk.dtos.Taasisi.TaasisiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinistryDto {

private Long id;
private String ministryName;
private Set<TaasisiDto> taasisi;
    
}
