package com.example.begard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Log_In extends AppCompatActivity {

    private EditText edtUserID,edtPassword;
    private Button btnSignin,btnSignUp;
    private TextView txtHaveNotRegistered;
    private FirebaseAuth myfirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);

        myfirebaseAuth = FirebaseAuth.getInstance();
        edtUserID =findViewById(R.id.edtEmail);
        edtPassword =findViewById(R.id.edtPassword);
        txtHaveNotRegistered =findViewById(R.id.chTextForgottenPassword);
        btnSignin = findViewById(R.id.btnSignIn);
        btnSignUp =findViewById(R.id.btnSignUp);


        if (myfirebaseAuth.getCurrentUser() != null) {
            updateUI(myfirebaseAuth.getCurrentUser());
        }
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Log_In.this,Sign_Up.class);
                startActivity(intent);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserID = edtUserID.getText().toString();
                String password = edtPassword.getText().toString();
                if(UserID.isEmpty()){
                    edtUserID.setError("Please enter your UserID");
                    edtUserID.requestFocus();
                }else if(password.isEmpty()){
                    edtPassword.setError("Please enter you'r password");
                    edtPassword.requestFocus();
                }else if( UserID.isEmpty() && password.isEmpty()){
                    Toast.makeText(Log_In.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }else if(!(UserID.isEmpty() && password.isEmpty())) {
                    myfirebaseAuth.signInWithEmailAndPassword(UserID, password).addOnCompleteListener(Log_In.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!(task.isSuccessful())) {
                                Toast.makeText(Log_In.this, "Log in Error!", Toast.LENGTH_SHORT).show();
                            } else {
                                FirebaseUser firebaseUser = myfirebaseAuth.getCurrentUser();
                                updateUI(firebaseUser);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = myfirebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            updateUI(firebaseUser);
        }
    }
    public void updateUI(FirebaseUser currentUser) {
        Intent profileIntent = new Intent(Log_In.this, MainActivity.class);
        profileIntent.putExtra("email", currentUser.getEmail());
        Log.v("DATA", currentUser.getUid());
        startActivity(profileIntent);
    }
}
