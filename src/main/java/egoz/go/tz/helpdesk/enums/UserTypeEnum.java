package egoz.go.tz.helpdesk.enums;

public enum UserTypeEnum {

    SUPER_ADMINISTRATOR("SUPER_ADMINISTRATOR"),
    ADMINISTRATOR("ADMINISTRATOR"),
    TECHNICIAN("TECHNICIAN"),
    REQUESTER("REQUESTER");

    private String userType;


    UserTypeEnum(String userType){
        this.userType=userType;
        
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public String getUserType() {
        return userType;
    }

    
}
