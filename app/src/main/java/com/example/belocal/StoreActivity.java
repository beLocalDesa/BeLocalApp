package com.example.belocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.StoreAdapter;
import config.APIUtils;
import model.MStore;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.StoreService;

public class StoreActivity extends AppCompatActivity {

    Button btnInsertStore;
    Button btnGetStore;
    ListView listViewStore;
    StoreService storeService;
    List<MStore> listStore = new ArrayList<MStore>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        btnGetStore = (Button) findViewById(R.id.getStore);
        btnInsertStore = (Button) findViewById(R.id.insertStore);
        listViewStore = (ListView) findViewById(R.id.listStore);
        storeService = APIUtils.getStoreService();

        btnGetStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUsersList();
            }
        });

    }

    public void getUsersList(){
        Call<List<MStore>> call = storeService.getStores();
        call.enqueue(new Callback<List<MStore>>() {
            @Override
            public void onResponse(Call<List<MStore>> call, Response<List<MStore>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(StoreActivity.this, "Obteniendo Datos", Toast.LENGTH_SHORT).show();
                    listStore = response.body();
                    listViewStore.setAdapter(new StoreAdapter(StoreActivity.this, R.layout.list_store, listStore));
                }
            }

            @Override
            public void onFailure(Call<List<MStore>> call, Throwable t) {
                Log.e("ERROR: " , t.getMessage());
            }
        });

    }



}
