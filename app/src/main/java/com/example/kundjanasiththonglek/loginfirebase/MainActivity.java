package com.example.kundjanasiththonglek.loginfirebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button signin, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), SignIn.class));
        }
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                String getemail = email.getText().toString();
                String getpassword = password.getText().toString();
                callsignin(getemail,getpassword);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                String getemail = email.getText().toString();
                String getpassword = password.getText().toString();
                callsignup(getemail, getpassword);
            }
        });


    }

    private void callsignup(String email, String password) {
        Log.d("Test",email.toString());
        Log.d("Test",password.toString());
        if(!email.isEmpty()&!password.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("test", "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Sign Up Failed",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                userProfile();
                                Toast.makeText(MainActivity.this, "Account Created",
                                        Toast.LENGTH_SHORT).show();
                                Log.d("Test", "Account Created");
                            }

                            // ...
                        }
                    });
        }
        else{
            Toast.makeText(MainActivity.this, "Please enter your username and password",Toast.LENGTH_SHORT).show();
        }
    }

    private void userProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(email.getText().toString()).build();
            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d("Test", "User profile updated");
                    }
                }
            });

        }
    }

    private void callsignin(String email, String password){
        if(!email.isEmpty()&!password.isEmpty()) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("Test", "Sign in is successful: "+task.isSuccessful());
                if(!task.isSuccessful()){
                    Log.d("Test", "Sign In With Email Failed: "+task.getException());
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(MainActivity.this, SignIn.class);
                    finish();
                    startActivity(i);
                }
            }
        });
        }
        else{
            Toast.makeText(MainActivity.this, "Please enter your username and password",Toast.LENGTH_SHORT).show();
        }
    }
}
