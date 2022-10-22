package enums;

public enum EmailServer {
    AUTOMATIONCAMPUS("mail.automationcampus.com.ar");

    private String host;

    private EmailServer(String hostname) {
        this.host=hostname;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
