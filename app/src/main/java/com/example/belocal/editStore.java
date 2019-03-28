package com.example.belocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import config.APIUtils;
import model.MStore;
import model.MUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.StoreService;
import service.UserService;

public class editStore extends AppCompatActivity {

    private StoreService storeService;
    MStore store = new MStore();

    private UserService userService;
    MUser user = new MUser();

    private Long idStore;
    private String nameStore;
    private String phoneStore;
    private String addressStore;
    private String postalCodeStore;
    private String categoryStore;
    private String emailStore;
    private String districtStore;
   // private String editCategoryStore;
   // private String editDistrictStore;

    private TextView editNameStoreTextView;
    private TextView editPhoneStoreTextView;
    private TextView editAddressStoreTextView;
    private TextView editPostalCodeStoreTextView;
    private TextView categoryStoreTextView;
    private TextView editEmailStoreTextView;
    private TextView districtStoreTextView;
    //private Spinner editCategoryStore;
    //private TextView editDistrictStoreTextView;

    String district_codes [] =  getResources().getStringArray(R.array.district_codes);
    String category_codes [] =  getResources().getStringArray(R.array.category_codes);

    String userUUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_store);

        Bundle bundle = getIntent().getExtras();
        userUUID = bundle.getString("userUUID");

        editNameStoreTextView = (TextView) findViewById(R.id.editNameStore);
        editPhoneStoreTextView = (TextView) findViewById(R.id.editPhoneStore);
        editEmailStoreTextView = (TextView) findViewById(R.id.editEmailStore);
        editAddressStoreTextView = (TextView) findViewById(R.id.editAddressStore);
        editPostalCodeStoreTextView = (TextView) findViewById(R.id.editPostalCodeStore);
       // districtStoreTextView = (TextView) findViewById(R.id.spinnerDistrictStore);
       // categoryStoreTextView = (TextView) findViewById(R.id.spinnerCategoryStore);

        Toast.makeText(editStore.this, "Edit your Information", Toast.LENGTH_LONG).show();

        userService = APIUtils.getUserService();

        storeService = APIUtils.getStoreService();

        Call<MUser> userCall = userService.getUserByUuid(userUUID);
        userCall.enqueue(new Callback<MUser>() {
            @Override
            public void onResponse(Call<MUser> call, Response<MUser> response) {
                if(response.isSuccessful()){
                    user = response.body();

                    Call<MStore> storeCall = storeService.getStoreById(user.getIdStore());

                    storeCall.enqueue(new Callback<MStore>() {
                        @Override
                        public void onResponse(Call<MStore> call, Response<MStore> response) {
                            store = response.body();
                            setNamesToInputs(store);
                            idStore = store.getId();
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

    private void setNamesToInputs(MStore store) {

        editNameStoreTextView.setText(store.getName());
        editPhoneStoreTextView.setText(store.getPhone()+"");
        editEmailStoreTextView.setText(store.getEmail());
        editAddressStoreTextView.setText(store.getPostal_address());
        editPostalCodeStoreTextView.setText(store.getPostal_code()+"");
        //districtStoreTextView.setText(store.getDistrict()+"");
        //categoryStoreTextView.setText(store.getCategory()+"");

    }

    public void saveRecord(View v){

        nameStore = editNameStoreTextView.getText().toString();
        phoneStore = editPhoneStoreTextView.getText().toString();
        emailStore = editEmailStoreTextView.getText().toString();
        addressStore = editAddressStoreTextView.getText().toString();
        postalCodeStore = editPostalCodeStoreTextView.getText().toString();


        store.setName(nameStore);
        store.setPhone(Long.parseLong(phoneStore));
        store.setEmail(emailStore);
        store.setPostal_address(addressStore);
        store.setPostal_code(Long.parseLong(postalCodeStore));
        //store.setCategory(Long.parseLong(categoryTextViewS));
        //store.setDistrict(Long.parseLong(districtTextViewS));

        Log.d("INFO:", "Enviando Informacion");

        storeService.updateStore(idStore,store);

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
