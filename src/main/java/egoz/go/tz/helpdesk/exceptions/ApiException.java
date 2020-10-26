package egoz.go.tz.helpdesk.exceptions;

import lombok.Getter;

public class ApiException extends Exception {
    private static final long serialVersionUID = 1L;

  @Getter
  private int code;

  public ApiException(int code, String msg) {
    super(msg);
    this.code = code;
  }
}
