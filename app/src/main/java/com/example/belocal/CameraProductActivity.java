package com.example.belocal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import model.MImagen;
import model.MProduct;
import model.MStore;
import model.MUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.ImagenService;
import service.ProductService;
import service.StoreService;
import service.UserService;

public class CameraProductActivity extends AppCompatActivity {

    ImageView imageViewProduct;
    private ImagenService imagenService;
    private UserService userService;
    private StoreService storeService;
    private ProductService productService;
    MImagen mImagen = new MImagen();
    MUser mUser = new MUser();
    MProduct mProduct = new MProduct();
    MStore mStore = new MStore();
    String nameProductS = "";

    TextView nameProduct;

    String userUUID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_product);

        Bundle bundle = getIntent().getExtras();
        userUUID = bundle.getString("userUUID");

        Button btnCamera = (Button) findViewById(R.id.openCamara);
        imageViewProduct = (ImageView) findViewById(R.id.photoView);
        nameProduct = (TextView) findViewById(R.id.txtNameProduct);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });


        Call<MUser> userCall = userService.getUserByUuid(userUUID);
        userCall.enqueue(new Callback<MUser>() {
            @Override
            public void onResponse(Call<MUser> call, Response<MUser> response) {
                if(response.isSuccessful()) {
                    mUser = response.body();

                    Call<MStore> storeCall = storeService.getStoreById(mUser.getIdStore());
                    storeCall.enqueue(new Callback<MStore>() {
                        @Override
                        public void onResponse(Call<MStore> call, Response<MStore> response) {
                            mStore = response.body();
                        }

                        @Override
                        public void onFailure(Call<MStore> call, Throwable t) {

                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<MUser> call, Throwable t) {

            }
        });







    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageViewProduct.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 640, 480, false));

        mImagen.setImagen(bitmap);

    }


    public void saveRecord(View v){

        nameProductS = nameProduct.getText().toString();

        mProduct.setName(nameProductS);
        mProduct.setShop(mStore.getId());

        productService.insertProduct(mProduct);

        mImagen.setName(nameProductS);

        Log.d("INFO:", "Enviando Informacion");

        imagenService.insertImagen(mImagen);


        Toast.makeText( this, "Guardado", Toast.LENGTH_LONG ).show();
        try {
            //set time in mili
            Thread.sleep(3000);

        }catch (Exception e){
            e.printStackTrace();
        }

        Intent myIntent1 = new Intent(getBaseContext(), MapsActivity.class);
        myIntent1.putExtra("userUUID", userUUID);
        startActivityForResult(myIntent1, 1);

    }







}
