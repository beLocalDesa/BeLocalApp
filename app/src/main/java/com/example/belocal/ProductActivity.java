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

import adapter.ProductAdapter;
import adapter.StoreAdapter;
import config.APIUtils;
import model.MProduct;
import model.MStore;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.ProductService;
import service.StoreService;

public class ProductActivity extends AppCompatActivity {

    Button btnInsertProduct;
    Button btnGetProduct;
    ListView listViewProduct;
    ProductService productService;
    List<MProduct> listProduct = new ArrayList<MProduct>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        btnGetProduct = (Button) findViewById(R.id.getProduct);
        btnInsertProduct = (Button) findViewById(R.id.insertProduct);
        listViewProduct = (ListView) findViewById(R.id.listProduct);
        productService = APIUtils.getProductService();

        btnGetProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProductList();
            }
        });



    }

    public void getProductList(){
        Call<List<MProduct>> call = productService.getProducts();
        call.enqueue(new Callback<List<MProduct>>() {
            @Override
            public void onResponse(Call<List<MProduct>> call, Response<List<MProduct>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProductActivity.this, "Obteniendo Datos", Toast.LENGTH_SHORT).show();
                    listProduct = response.body();
                    listViewProduct.setAdapter(new ProductAdapter(ProductActivity.this, R.layout.list_store, listProduct));
                }
            }

            @Override
            public void onFailure(Call<List<MProduct>> call, Throwable t) {
                Log.e("ERROR: " , t.getMessage());
            }

        });

    }



}
