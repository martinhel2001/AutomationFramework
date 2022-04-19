package enums;

public enum EmailUser {
    AKAUNTING_admin("akaunting-admin@automationcampus.com.ar","Trinity110"),
    CHURCH_admin("church-admin@automationcampus.com.ar","Trinity110"),
    SENTRIFUGO_admin("sentrifugo-admin@automationcampus.com.ar","Trinity110"),
    YETI_admin("yeti-admin@automationcampus.com.ar","Trinity110");

    private String userName;
    private String password;

    private EmailUser(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
