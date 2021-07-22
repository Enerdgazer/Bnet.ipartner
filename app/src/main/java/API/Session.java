package API;

import com.google.gson.annotations.SerializedName;

public class Session {
    @SerializedName("session")
    String session;

    public String getNumberSession() {
        return session;
    }
}
