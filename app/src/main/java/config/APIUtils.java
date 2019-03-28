package config;

import service.ImagenService;
import service.ProductService;
import service.StoreService;
import service.UrlFriendlyService;
import service.UserService;

public class APIUtils {

    private APIUtils(){
    }

    public static final String API_URL = "http://192.168.0.12:8090";
    public static final String API_URL_IMAGEN = "http://192.168.0.12:8090";

    public static StoreService getStoreService(){
        return RetrofitClient.getClient(API_URL).create(StoreService.class);
    }

    public static ProductService getProductService(){
        return RetrofitClient.getClient(API_URL).create(ProductService.class);
    }

    public static UrlFriendlyService getUrlFriendlyService(){
        return RetrofitClient.getClient(API_URL).create(UrlFriendlyService.class);
    }

    public static ImagenService getImagenService(){
        return RetrofitClient.getClient(API_URL_IMAGEN).create(ImagenService.class);
    }

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }



}
