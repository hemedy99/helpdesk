package egoz.go.tz.helpdesk.dtos;

import egoz.go.tz.helpdesk.models.Ministry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaasisiDto {
    private Long id;
    private String taasisiName;
    private Ministry ministry;
}
