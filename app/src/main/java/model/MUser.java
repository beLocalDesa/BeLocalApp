package model;

import com.google.gson.annotations.SerializedName;

public class MUser {

    @SerializedName("id")
    private Long id;
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("idStore")
    private Long idStore;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getIdStore() {
        return idStore;
    }

    public void setIdStore(Long idStore) {
        this.idStore = idStore;
    }
}
