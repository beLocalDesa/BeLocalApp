package config;

import model.MStore;
import service.StoreService;

public class APIUtils {

    private APIUtils(){
    }

    public static final String API_URL = "http://192.168.0.12:8090";

    public static StoreService getStoreService(){
        return RetrofitClient.getClient(API_URL).create(StoreService.class);
    }


}
