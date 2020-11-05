package egoz.go.tz.helpdesk.enums;

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




}
