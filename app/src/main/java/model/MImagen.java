package model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class MImagen {

    @SerializedName("path")
    private String path;
    @SerializedName("imagen")
    private Bitmap imagen;
    @SerializedName("name")
    private String name;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
