package API;

import java.util.List;

public class EntryResponse {
    public List<List<Entry>> data;
    int status;
    String error;

    public int getStatus() {
        return status;
    }

    public List<List<Entry>> getData() {

        return data;
    }

    public String getError() {
        return error;
    }

}
