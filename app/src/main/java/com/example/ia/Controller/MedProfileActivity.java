package com.example.ia.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ia.Modal.Medicine.Med;
import com.example.ia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MedProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private TextView name;
    private TextView ai;
    private TextView country;
    private TextView maDate;
    private TextView maExtDate;
    private TextView maPrepDate;
    private TextView maRewDate;
    private TextView strength;
    private TextView type;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_profile);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        name = findViewById(R.id.TVName);
        ai = findViewById(R.id.TVai);
        country = findViewById(R.id.TVCountry);
        maDate = findViewById(R.id.TVmaDate);
        maExtDate = findViewById(R.id.TVmaExtDate);
        maPrepDate = findViewById(R.id.TVmaPrepDate);
        maRewDate = findViewById(R.id.TVmaRewDate);
        strength = findViewById(R.id.TVStrength);
        type = findViewById(R.id.TVtype);


       String id = getIntent().getStringExtra("id");
       System.out.println("ID IS" + id);

        CollectionReference a = firestore.collection("Med");
        Query query = a.whereEqualTo("state", "CA");

        firestore.collection("Med").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                System.out.println(task.getResult());
                DocumentSnapshot s = task.getResult();
                s.toObject(Med.class);
                Med m = s.toObject(Med.class);
                System.out.println("PRINT M" + m.getMedName());

                name.setText(m.getMedName());
                ai.setText(m.getActiveIngrediant());
                country.setText(m.getCountry());
                maDate.setText(m.getMaDate());
                maExtDate.setText(m.getMaExpDate());
                maPrepDate.setText(m.getMaPrepDate());
                maRewDate.setText(m.getMaRewDate());
                strength.setText(m.getStrength());
                type.setText(m.getType());
            }
        });

    }


}