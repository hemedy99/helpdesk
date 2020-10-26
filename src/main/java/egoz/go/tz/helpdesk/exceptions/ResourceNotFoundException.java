package egoz.go.tz.helpdesk.exceptions;

public class ResourceNotFoundException extends Exception{
  	
//	@Getter
//	@Setter
//	private ApiError error;
	
	public ResourceNotFoundException(String resourceName) {
		super(resourceName);
//		error.setMessage(resourceName+": Not Found");
//		error.addError(resourceName, "Not Found");
	}  
}
