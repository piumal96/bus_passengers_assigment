package com.example.bus_passengers_assigment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText edtEmail, edtPassword;
    Button bttnSignIn, bttnSignUp;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mAuth = FirebaseAuth.getInstance();
        //edit text box reference
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);


        //Button reffrence
        bttnSignIn = findViewById(R.id.bttn_sign_in);
        bttnSignUp = findViewById(R.id.bttn_sign_up);

        bttnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateEmail() && validatePassword())
                    signIn();

            }
        });

        //ime option to enter key
        edtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled=false;
                if (actionId== EditorInfo.IME_ACTION_SEND){
                    handled=true;
                    if(validateEmail() && validatePassword()){
                        signIn();
                    }

                }
                return handled;
            }
        });
        bttnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Home_page.class);
                startActivity(i);
            }
        });



    }
    private void signIn()
    {
        mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                db = FirebaseFirestore.getInstance();
                userID = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = db.collection(userID).document("Roll");

                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Signin is Sucsuss", Toast.LENGTH_LONG).show();


                    EnterDetail();
//                DocumentReference documentReference = fStore.collection("users").document(userId);


                }else
                {
                    Toast.makeText(MainActivity.this, "Signin is Failed", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            //Transition to next activity
        }
    }

    private void EnterDetail()
    {
        Intent i=new Intent(MainActivity.this,passenger_recycler.class);
        startActivity(i);
    }
    //validate Cheking
    private boolean validateEmail() {
        String emailInput = edtEmail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            Toast.makeText(MainActivity.this, "Field can't be empty", Toast.LENGTH_LONG).show();
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(MainActivity.this, "Enter Correct Email", Toast.LENGTH_LONG).show();
            return false;
        } else {
            edtEmail.setError(null);
            return true;
        }
    }
    //validate Cheking
    private boolean validatePassword() {
        String passwordInput = edtPassword.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            Toast.makeText( MainActivity.this, "Field can't be empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (passwordInput.length() < 8) {
            Toast.makeText( MainActivity.this, "Password must be at least 5 characters", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
//            MainActivityConfirmPassError.setText("Password Matched");
            return true;
        }
    }

}