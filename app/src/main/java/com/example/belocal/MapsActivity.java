package com.example.belocal;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private UserService userService;
    private StoreService storeService;

    MUser mUser = new MUser();
    MStore mStore = new MStore();

    String userUUID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        userUUID = bundle.getString("userUUID");

        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //Obtener Tienda
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


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng mPunto = getLocationFromAddress(getApplicationContext(), mStore.getPostal_address());

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(mPunto).title("Marker in Store"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mPunto));
    }


    public void addProduct(View v){
        Intent myIntent = new Intent(getBaseContext(), CameraProductActivity.class);
        startActivity(myIntent);
    }



    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }


    public void goToProduct(View v){

        Intent myIntent = new Intent(getBaseContext(), ProductActivity.class);
        startActivity(myIntent);
        myIntent.putExtra("userUUID", userUUID);
        startActivityForResult(myIntent, 1);

    }

    public void goToCamera(View v){

        Intent myIntent = new Intent(getBaseContext(), CameraProductActivity.class);
        startActivity(myIntent);
        myIntent.putExtra("userUUID", userUUID);
        startActivityForResult(myIntent, 1);

    }


    public void goToEditStore(View v){

        Intent myIntent = new Intent(getBaseContext(), editStore.class);
        startActivity(myIntent);
        myIntent.putExtra("userUUID", userUUID);
        startActivityForResult(myIntent, 1);

    }



}
