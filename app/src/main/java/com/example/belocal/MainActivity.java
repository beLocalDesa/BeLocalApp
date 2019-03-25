package com.example.belocal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 7177;
    List<AuthUI.IdpConfig> providers;
    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignOut = (Button) findViewById(R.id.singOut);

        btnSignOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //LogOut
                AuthUI.getInstance()
                        .signOut(MainActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                btnSignOut.setEnabled(false);
                                showSignInOptions();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        showSignInOptions();
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.MyTheme)
                .build(), MY_REQUEST_CODE
        );

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MY_REQUEST_CODE){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){

                //Get User
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                //Show email on Toast
                Toast.makeText( this, user.getEmail(), Toast.LENGTH_SHORT ).show();
                //Get Buttom SignOut TODO: Mejorar - Borrar o Colocar Boton en Otra Pantalla (vista y logica)
                //btnSignOut.setEnabled(true);
                //Intent myIntent = new Intent(getBaseContext(), MapsActivity.class);
                //startActivity(myIntent);

                Log.e("INFO: " , "SIGNIN METHOD");
                if(user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp() ){
                    Log.e("INFO: " , "NEW USER");
                    Intent myIntent1 = new Intent(getBaseContext(), addStore.class);
                    myIntent1.putExtra("userUUID", user.getUid());
                    startActivityForResult(myIntent1, 1);
                }else{
                    Log.e("INFO: " , "OLD USER");
                    Intent myIntent2 = new Intent(getBaseContext(), MapsActivity.class);
                    myIntent2.putExtra("userUUID", user.getUid());
                    startActivityForResult(myIntent2, 1);
                }

            }else{
                Toast.makeText( this, response.getError().getMessage(), Toast.LENGTH_SHORT ).show();
            }
        }
    }


    public void goToStore(View v){
        Intent myIntent = new Intent(getBaseContext(), StoreActivity.class);
        startActivity(myIntent);
    }


}
