package com.example.begard;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;

public class Sign_Up extends AppCompatActivity implements View.OnClickListener {

    EditText edtFullName, edtUserID, edtPassWord, edtEmail, edtPhoneNumber;
    Button btnSignUp;
    TextView txtHaveAnAccount;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    User user;
    private static final String USER = "user";
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        edtFullName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtUserID = findViewById(R.id.edtUserID);
        edtPassWord = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtHaveAnAccount = findViewById(R.id.txtSignin);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(USER);
        firebaseAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(Sign_Up.this);
        txtHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent0 = new Intent(Sign_Up.this, Log_In.class);
                startActivity(intent0);
            }
        });

    }

    @Override
    public void onClick(View view) {
        String fullname = edtFullName.getText().toString();
        String email = edtEmail.getText().toString();
        String UserID = edtUserID.getText().toString();
        String number = edtPhoneNumber.getText().toString();
        String password = edtPassWord.getText().toString();

        if (fullname.isEmpty()) {
            edtFullName.setError("Please enter the fullname");
            edtFullName.requestFocus();
        } else if (email.isEmpty()) {
            edtEmail.setError("Please enter an Email");
            edtEmail.requestFocus();
        } else if (UserID.isEmpty()) {
            edtUserID.setError("Please enter your userID");
            edtUserID.requestFocus();
        } else if (password.isEmpty()) {
            edtPassWord.setError("Please enter your password");
            edtPassWord.requestFocus();
        } else if (fullname.isEmpty() && email.isEmpty() && UserID.isEmpty() && password.isEmpty()) {
            Toast.makeText(Sign_Up.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
        } else {
            user = new User(UserID,fullname,email,number,password);
            /*DatabaseManager dbm = new DatabaseManager(getApplicationContext());
            dbm.insertUser(user);*/
            registerUser(email, password);
        }

    }

    public void registerUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Sign_Up.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Sign_Up.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void updateUI(FirebaseUser firebaseUser) {
        Intent i = new Intent(Sign_Up.this, Log_In.class);
        String keyID = databaseReference.push().getKey();
        databaseReference.child(keyID).setValue(user);
        startActivity(i);
    }
}