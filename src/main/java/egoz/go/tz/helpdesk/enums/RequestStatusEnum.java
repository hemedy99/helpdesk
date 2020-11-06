package egoz.go.tz.helpdesk.enums;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RequestStatusEnum {
    OPEN("OPEN"),
    ASSIGNED("ASSIGNED"),
    ONHOLD("ONHOLD"),
    INPROGRESS("INPROGRESS"),
    CLOSED("CLOSED");

    private String requestStatus;

    RequestStatusEnum(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    private static Map<String, RequestStatusEnum> FORMAT_MAP =
    Stream.of(RequestStatusEnum.values())
        .collect(Collectors.toMap(s -> s.requestStatus, Function.identity()));

@JsonValue
public String toValue() {
  return requestStatus;
}

@JsonCreator
public static RequestStatusEnum fromValue(String name) throws Exception {
  return java.util.Optional.ofNullable(FORMAT_MAP.get(name))
      .orElseThrow(() -> new IllegalArgumentException(name));
}

}
