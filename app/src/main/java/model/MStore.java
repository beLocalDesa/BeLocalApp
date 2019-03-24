package model;

import com.google.gson.annotations.SerializedName;

public class MStore {

    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private long phone;
    @SerializedName("email")
    private String email;
    @SerializedName("postal_address")
    private String postal_address;
    @SerializedName("postal_code")
    private long postal_code;
    @SerializedName("url")
    private long url;
    @SerializedName("district")
    private long district;
    @SerializedName("category")
    private long category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public void setPostal_address(String postal_address) {
        this.postal_address = postal_address;
    }

    public long getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(long postal_code) {
        this.postal_code = postal_code;
    }

    public long getUrl() {
        return url;
    }

    public void setUrl(long url) {
        this.url = url;
    }

    public long getDistrict() {
        return district;
    }

    public void setDistrict(long district) {
        this.district = district;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }



}
