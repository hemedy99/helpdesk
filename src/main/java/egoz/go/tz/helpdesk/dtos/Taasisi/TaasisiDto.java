package egoz.go.tz.helpdesk.dtos.Taasisi;

import egoz.go.tz.helpdesk.dtos.Ministry.MinistryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaasisiDto {
    private Long id;
    private String taasisiName;
    private MinistryDto ministry;
}
