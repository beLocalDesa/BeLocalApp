package model;

import com.google.gson.annotations.SerializedName;

public class MUrlFriendly {

    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("url")
    private String url;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
}
