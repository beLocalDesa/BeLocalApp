package com.example.belocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import config.APIUtils;
import model.MStore;
import service.StoreService;

public class addStore extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private StoreService storeService;
    MStore store = new MStore();

    private TextView idTextView;
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView emailTextView;
    private TextView postal_addressTextView;
    private TextView postal_codeTextView;
    private TextView urlTextView;
    private Spinner districtTextView;
    private Spinner categoryTextView;

    private String nameTextViewS;
    private String phoneTextViewS;
    private String emailTextViewS;
    private String postal_addressTextViewS;
    private String postal_codeTextViewS;
    private String districtTextViewS;
    private String categoryTextViewS;

    String district_codes [] =  getResources().getStringArray(R.array.district_codes);
    String category_codes [] =  getResources().getStringArray(R.array.category_codes);

    String userUUID;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        userUUID = bundle.getString("userUUID");

        Toast.makeText(addStore.this, "Welcome to BeLocal! Please register your Awesome Store", Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_add_store);

        storeService = APIUtils.getStoreService();

        nameTextView = (TextView) findViewById(R.id.textStoreName);
        phoneTextView = (TextView) findViewById(R.id.txtPhoneStore);
        emailTextView = (TextView) findViewById(R.id.editText11);
        postal_addressTextView = (TextView) findViewById(R.id.txtAddressStore);
        postal_codeTextView = (TextView) findViewById(R.id.txtPostalCodeStore);
        districtTextView = (Spinner) findViewById(R.id.spinnerDistrictStore);
        categoryTextView = (Spinner) findViewById(R.id.spinnerCategoryStore);


        ArrayAdapter<CharSequence> adapterSpinner1 = ArrayAdapter.createFromResource(this,R.array.district,R.layout.support_simple_spinner_dropdown_item);
        adapterSpinner1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtTextView.setAdapter(adapterSpinner1);
        districtTextView.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterSpinner2 = ArrayAdapter.createFromResource(this,R.array.category,R.layout.support_simple_spinner_dropdown_item);
        adapterSpinner2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryTextView.setAdapter(adapterSpinner2);
        categoryTextView.setOnItemSelectedListener(this);


        //String urlTextViewS;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.spinnerDistrictStore)
        {
            //districtTextViewS = adapterView.getItemAtPosition(i).toString();
            districtTextViewS = district_codes[i];
        }
        else if(adapterView.getId() == R.id.spinnerCategoryStore)
        {
            //categoryTextViewS = adapterView.getItemAtPosition(i).toString();
            categoryTextViewS = category_codes[i];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void saveRecord(View v){

        nameTextViewS = nameTextView.getText().toString();
        phoneTextViewS = phoneTextView.getText().toString();
        emailTextViewS = emailTextView.getText().toString();
        postal_addressTextViewS = postal_addressTextView.getText().toString();
        postal_codeTextViewS = postal_codeTextView.getText().toString();


        store.setName(nameTextViewS);
        store.setPhone(Long.parseLong(phoneTextViewS));
        store.setEmail(emailTextViewS);
        store.setPostal_address(postal_addressTextViewS);
        store.setPostal_code(Long.parseLong(postal_codeTextViewS));
        store.setCategory(Long.parseLong(categoryTextViewS));
        store.setDistrict(Long.parseLong(districtTextViewS));

        Log.d("INFO:", "Enviando Informacion");

        storeService.insertStore(store);

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
