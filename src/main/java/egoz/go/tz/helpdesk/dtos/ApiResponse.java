package egoz.go.tz.helpdesk.dtos;

import java.util.List;

public class ApiResponse<T> {
	public boolean status;
	public String message;
	public T data;
	public List<String> error;
  
	public ApiResponse(boolean status, String message, T data, List<String> error) {
	  this.status = status;
	  this.message = message;
	  this.data = data;
	  this.error = error;
	}
  
	public static <Data> ApiResponse<Data> error(String message, List<String> error) {
	  return new ApiResponse<>(false, message, null, error);
	}
  
	public static <Data> ApiResponse<Data> success(Data data) {
	  return new ApiResponse<>(true, "Success", data, null);
	}


}