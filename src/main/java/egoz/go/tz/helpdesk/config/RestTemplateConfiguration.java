package egoz.go.tz.helpdesk.config;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder,
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter,
        MappingJackson2XmlHttpMessageConverter xmlHttpMessageConverter) {
  
      RestTemplate restTemplate = restTemplateBuilder.build();
  
      restTemplate.setMessageConverters(
          Arrays.asList(jsonHttpMessageConverter, new FormHttpMessageConverter()));
  
      return restTemplate;
    }
  
    @Bean
    public MappingJackson2HttpMessageConverter jsonHttpMessageConverter(ObjectMapper objectMapper) {
      return new MappingJackson2HttpMessageConverter(objectMapper);
    } 
}
