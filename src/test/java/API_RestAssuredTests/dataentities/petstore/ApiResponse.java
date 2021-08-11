package API_RestAssuredTests.dataentities.petstore;

public class ApiResponse {
    private int code;
    private String type;
    private String message;

    public ApiResponse(){
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
