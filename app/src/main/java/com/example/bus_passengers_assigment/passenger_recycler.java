package com.example.bus_passengers_assigment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class passenger_recycler extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_recycler);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db=FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();
        myAdapter=new MyAdapter(passenger_recycler.this,userArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListener();
    }
    private void EventChangeListener(){
        db.collection("Passenger").orderBy("fName", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error !=null){
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                            Log.e("Firestore Error,",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc :value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                userArrayList.add(dc.getDocument().toObject(User.class));
                            }

                            myAdapter .notifyDataSetChanged();
                            //progressDialog.dismiss();

                        }
                    }
                });

    }
}