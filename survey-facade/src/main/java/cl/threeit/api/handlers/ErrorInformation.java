package cl.threeit.api.handlers;

public class ErrorInformation {

    private int code;
    private String description;
    private String uriRequested;

    public ErrorInformation(int code, String description, String uriRequested) {
        this.code = code;
        this.description = description;
        this.uriRequested = uriRequested;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUriRequested() {
        return uriRequested;
    }

    public void setUriRequested(String uriRequested) {
        this.uriRequested = uriRequested;
    }
}
