package API;

public class SessionResponse {
    int status;
    Session data;
    String error;

    public int getStatus() {
        return status;
    }

    public Session getSession() {
        return data;
    }

    public String getError() {
        return error;
    }


}