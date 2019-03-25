package com.example.belocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class addStore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String userUUID = bundle.getString("userUUID");

        Toast.makeText(addStore.this, "Welcome to BeLocal! Please register your Awesome Store", Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_add_store);
    }
}
