package com.example.kundjanasiththonglek.loginfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
//    private TextView textView;
    private TextView info;
    private Button signout,newPost,viewPost, location;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//        textView = (TextView) findViewById(R.id.textView);
//        signout = (Button) findViewById(R.id.signout);
//        mAuth = FirebaseAuth.getInstance();
//        textView.setText("Welcome "+mAuth.getCurrentUser().getEmail());
//        signout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            }
//        });
        mAuth = FirebaseAuth.getInstance();
        signout = (Button) findViewById(R.id.signout);
        info = (TextView) findViewById(R.id.textView);
        newPost = (Button) findViewById(R.id.newPost);
        viewPost = (Button) findViewById(R.id.viewPost);
        location = (Button) findViewById(R.id.location);

        if(mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            info.setText("Welcome "+user.getEmail());
        }
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NewPost.class));
            }
        });
        viewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewPosts.class));
            }
        });
        location.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewLocation.class));
            }
        });
    }
}
