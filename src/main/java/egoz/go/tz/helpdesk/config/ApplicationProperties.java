package egoz.go.tz.helpdesk.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

@Configuration
@Validated
@Getter
@Setter
@ConfigurationProperties("app")
public class ApplicationProperties {

  private final Swagger swagger = new Swagger();

  @Getter
  @Setter
  public class Swagger {
    private String title;

    private String description;

    private String contactName;

    private String contactUrl;

    private String contactEmail;

    private String version;
  }
}
