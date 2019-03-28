package model;

import com.google.gson.annotations.SerializedName;

public class MProduct {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("shop")
    private Long shop;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getShop() {
        return shop;
    }

    public void setShop(Long shop) {
        this.shop = shop;
    }
}
