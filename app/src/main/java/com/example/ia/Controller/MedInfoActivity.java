package com.example.ia.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ia.Modal.Medicine.Med;
import com.example.ia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MedInfoActivity extends AppCompatActivity implements BookAdapter.OnNoteListener, AdapterView.OnItemSelectedListener{

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    //added for testing
    private ArrayList<Med> MedList;

    private Spinner spinner;


    RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_info);

        MedList = new ArrayList<Med>();

        recView = findViewById(R.id.recView);
        //creating the spinner
        spinner = this.findViewById(R.id.spinner);

        ArrayList<String> spinnerOptions = new ArrayList<>();
        spinnerOptions.add("No Filter");
        spinnerOptions.add("Vermidon");
        spinnerOptions.add("Liftrin");
        spinnerOptions.add("Lastoril");
        spinnerOptions.add("Kaynesten");
        spinnerOptions.add("Caldril");
        spinnerOptions.add("Avetrol");
        spinnerOptions.add("Afterin");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerOptions);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void testDB(View v) {
        MedList.clear();
        TaskCompletionSource<String> getAllRidesTask = new TaskCompletionSource<>();
        firestore.collection(Constants.MED_CONSTANT).whereEqualTo("open", true)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        MedList.add(document.toObject(Med.class));
                    }
                    getAllRidesTask.setResult(null);
                }
                else {
                    Log.d("MedInfoActivity", "Error getting documents from db: ", task.getException());
                }
            }
        });
        // when all rides have been retrieved, update RecyclerView
        getAllRidesTask.getTask().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                recView.setAdapter(new BookAdapter(MedList, MedInfoActivity.this));
                recView.setLayoutManager(new LinearLayoutManager(MedInfoActivity.this));
            }
        });
    }

    public void goBack(View V) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(int position) {
        MedList.get(position);

        Intent intent = new Intent(this, MedProfileActivity.class);
        intent.putExtra("id", MedList.get(position).getMedID());
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}