package com.example.bus_passengers_assigment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Enter_detail extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button bttnRegister;
    EditText edtSignUpEmail, edtSignUpPassword, edtConfirmPassword, fName, mNumber, nId,addres;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_detail);

        setContentView(R.layout.activity_enter_detail);
        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        bttnRegister = findViewById(R.id.bttn_register);


        addres=findViewById(R.id.edt_id_hospital);
        fName = findViewById(R.id.edt_full_name);
        mNumber = findViewById(R.id.edt_phone_number);
        nId = findViewById(R.id.edt_id_number);

        bttnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Enter_detail.this,thanks_page.class);
                startActivity(i);
                InsertData();


                Toast.makeText(Enter_detail.this, "check your connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void InsertData() {
//        mAuth.createUserWithEmailAndPassword(edtSignUpEmail.getText().toString(), edtSignUpPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {

        String fullName = fName.getText().toString().trim();
        String mobileNumber = mNumber.getText().toString().trim();
        String nationalId = nId.getText().toString().trim();
        String address=addres.getText().toString().trim();

        userID = mAuth.getCurrentUser().getUid();
        Map<String, Object> user = new HashMap<>();
        user.put("fName", fullName);
        user.put("NationalID", nationalId);
        user.put("Address",address);
        user.put("MobileNumber", mobileNumber);
        user.put("Roll","passenger");

        Task<DocumentReference> documentReference = db.collection("Passenger").add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(Enter_detail.this, "Insert Complite", Toast.LENGTH_LONG).show();

            }
        });



//                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Log.d("", "onSuccess User Profile is Created" + userID);
//
//                        }
//                    });

        //             Toast.makeText(Enter_detail.this, "Sign is Sucsuss", Toast.LENGTH_LONG).show();



//                } else {
//                    Toast.makeText(Enter_detail.this, "Sign is Failed", Toast.LENGTH_LONG).show();
//
//                }
    }

//        });

//    }


}
