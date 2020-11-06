package egoz.go.tz.helpdesk.enums;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RequestCategoryEnum {
    DOMAIN_AND_WEB("DOMAIN AND WEB"),
    HARDWARE("HARDWARE"),
    SOFTWARE("SOFTWARE"),
    OS("OS"),
    INTERNET("INTERNET"),
    NETWORK("NETWORK");

    private String requestCategory;

    RequestCategoryEnum(String requestCategory) {
        this.requestCategory = requestCategory;
    }

    public String getrequestCategory() {
        return this.requestCategory;
    }

    public void setrequestCategory(String requestCategory) {
        this.requestCategory = requestCategory;
    }

    private static Map<String, RequestCategoryEnum> FORMAT_MAP =
    Stream.of(RequestCategoryEnum.values())
        .collect(Collectors.toMap(s -> s.requestCategory, Function.identity()));

@JsonValue
public String toValue() {
  return requestCategory;
}

@JsonCreator
public static RequestCategoryEnum fromValue(String name) throws Exception {
  return java.util.Optional.ofNullable(FORMAT_MAP.get(name))
      .orElseThrow(() -> new IllegalArgumentException(name));
}  
}
