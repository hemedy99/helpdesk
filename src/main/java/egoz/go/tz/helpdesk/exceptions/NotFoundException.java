package egoz.go.tz.helpdesk.exceptions;

import lombok.Getter;

public class NotFoundException extends ApiException {

    private static final long serialVersionUID = 1L;

    @Getter
    private int code;
  
    public NotFoundException(int code, String msg) {
      super(code, msg);
      this.code = code;
    }
  
    public NotFoundException(String msg) {
      super(400, msg);
      this.code = 400;
    }
}
    
