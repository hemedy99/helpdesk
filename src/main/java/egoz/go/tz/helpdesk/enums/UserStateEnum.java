package egoz.go.tz.helpdesk.enums;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum UserStateEnum {
    PENDING("PENDING"),
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    LOCKED("LOCKED"),
    SUSPENDED("SUSPENDED");

    private String userState;

     UserStateEnum(String userState) {
        this.userState = userState;
    }

    public String getUserState() {
        return this.userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    private static Map<String, UserStateEnum> FORMAT_MAP =
    Stream.of(UserStateEnum.values())
        .collect(Collectors.toMap(s -> s.userState, Function.identity()));

@JsonValue
public String toValue() {
  return userState;
}

@JsonCreator
public static UserStateEnum fromValue(String name) throws Exception {
  return java.util.Optional.ofNullable(FORMAT_MAP.get(name))
      .orElseThrow(() -> new IllegalArgumentException(name));
}



}
