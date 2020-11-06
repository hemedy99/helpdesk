package egoz.go.tz.helpdesk.enums;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RequestPriorityEnum {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    NORMAL("NORMAL"),
    HIGH("HIGH");

    private String requestPriority;

    RequestPriorityEnum(String requestPriority) {
        this.requestPriority = requestPriority;
    }

    public String getRequestPriority() {
        return this.requestPriority;
    }

    public void setRequestPriority(String requestPriority) {
        this.requestPriority = requestPriority;
    }

    private static Map<String, RequestPriorityEnum> FORMAT_MAP =
    Stream.of(RequestPriorityEnum.values())
        .collect(Collectors.toMap(s -> s.requestPriority, Function.identity()));

@JsonValue
public String toValue() {
  return requestPriority;
}

@JsonCreator
public static RequestPriorityEnum fromValue(String name) throws Exception {
  return java.util.Optional.ofNullable(FORMAT_MAP.get(name))
      .orElseThrow(() -> new IllegalArgumentException(name));
}
 
}
