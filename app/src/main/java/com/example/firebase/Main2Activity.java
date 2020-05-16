package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button btnSignUp;
    private TextView t1,t3;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        inputEmail = (EditText) findViewById(R.id.edittext2);
        inputPassword = (EditText) findViewById(R.id.edittext);
        auth = FirebaseAuth.getInstance();
        btnSignUp = (Button) findViewById(R.id.button2);
        t1 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);



        authStateListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = auth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(Main2Activity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                    startActivity(i);

                } else {
                    Toast.makeText(Main2Activity.this, "Please login", Toast.LENGTH_SHORT).show();
                }

            }
        };
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = inputEmail.getText().toString();
                String pass = inputPassword.getText().toString();
                if (email.isEmpty()) {
                    inputEmail.setError("Please enter email");
                    inputEmail.requestFocus();

                } else if (pass.isEmpty()) {
                    inputPassword.setError("enter password");
                    inputPassword.requestFocus();
                } else if (email.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Fields are empty", Toast.LENGTH_SHORT);
                } else if (!(email.isEmpty() && pass.isEmpty())) {
                    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent in = new Intent(Main2Activity.this, Main3Activity.class);
                                startActivity(in);

                            } else {

                                Toast.makeText(Main2Activity.this, "Login error", Toast.LENGTH_SHORT).show();
                            }

                        }

                    });

                } else {
                    Toast.makeText(Main2Activity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }


        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(inte);

            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              FirebaseAuth authe = FirebaseAuth.getInstance();
              String email = inputEmail.getText().toString();
              authe.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      if(task.isSuccessful()){
                          Toast.makeText(Main2Activity.this,
                                  "A password reset mail has been sent at" + inputEmail.getText().toString(),
                                  Toast.LENGTH_LONG).show();
                      }
                  }
              });
            }
        });

    }
    @Override
    protected void onStart() {

        super.onStart();
        auth.addAuthStateListener(authStateListener);

    }

}


