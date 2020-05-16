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

public class MainActivity extends AppCompatActivity {
    public EditText inputEmail, inputPassword;
    public Button btnSignUp;
    public TextView t1;
    public FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEmail = (EditText) findViewById(R.id.loginpwd);
        inputPassword = (EditText) findViewById(R.id.edittext);
        auth = FirebaseAuth.getInstance();
        btnSignUp = (Button) findViewById(R.id.button2);

        t1 = (TextView) findViewById(R.id.textView2);
       btnSignUp.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String email = inputEmail.getText().toString();
                                            String pass = inputPassword.getText().toString();
                                            if (email.isEmpty()) {
                                                inputEmail.setError("Enter email id");

                                            } else if (pass.isEmpty()) {
                                                inputPassword.setError("Enter password");
                                            } else if (email.isEmpty() && pass.isEmpty()) {
                                                Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT);
                                            } else if (!(email.isEmpty() && pass.isEmpty())) {
                                                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            startActivity(new Intent(MainActivity.this, Main2Activity.class));

                                                        } else {

                                                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT);
                                                        }
                                                    }
                                                });
                                            } else {
                                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT);
                                            }

                                        }
                                    });
      t1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i = new Intent(MainActivity.this,Main2Activity.class);
              startActivity(i);
          }
      });







    }
}
