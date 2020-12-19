package egoz.go.tz.helpdesk.dtos.Taasisi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaasisiRequestDto {
    private String taasisiName;
    private Long ministryId;
}
