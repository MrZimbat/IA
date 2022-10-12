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
    private ArrayList<Med> displayMedList;

    private Spinner spinner;


    RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_info);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        MedList = new ArrayList<Med>();
        displayMedList = new ArrayList<>();

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

//    }

//    public void testDB(View v) {
        MedList.clear();
        TaskCompletionSource<String> getAllRidesTask = new TaskCompletionSource<>();
        firestore.collection(Constants.MED_CONSTANT)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    System.out.println(task.getResult().size());
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Med med = document.toObject(Med.class);
                        MedList.add(med);
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
                System.out.println("MEDLIST" + MedList);
                displayMedList = (ArrayList<Med>) MedList.clone();
                recView.setAdapter(new BookAdapter(displayMedList, MedInfoActivity.this));
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
        displayMedList.get(position);

        Intent intent = new Intent(this, MedProfileActivity.class);
        intent.putExtra("id", displayMedList.get(position).getMedID().toString());
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
        System.out.println("Spinner selected = " + selected);


        if(selected.equals("No Filter")){
            recView.setAdapter(new BookAdapter(displayMedList, MedInfoActivity.this));
            recView.setLayoutManager(new LinearLayoutManager(this));
            return;
        }

        displayMedList.clear();

        if(selected.equals("Afterin")) {
            System.out.println("SELECTED SOMETHING");
            for (Med v : MedList) {
                if (v.getMedName().equals("Afterin")) {
                    displayMedList.add(v);
                }
            }
        }

        else if(selected.equals("Avetrol")) {
            for (Med v : MedList) {
                if (v.getMedName().equals("Avetrol")) {
                    displayMedList.add(v);
                }
            }
        }

        else if(selected.equals("Caldril")) {
            for (Med v : MedList) {
                if (v.getMedName().equals("Caldril")) {
                    displayMedList.add(v);
                }
            }
        }

        else if(selected.equals("Kaynesten")) {
            for (Med v : MedList) {
                if (v.getMedName().equals("Kaynesten")) {
                    displayMedList.add(v);
                }
            }
        }

        else if(selected.equals("Lastoril")) {
            for (Med v : MedList) {
                if (v.getMedName().equals("Lastoril")) {
                    displayMedList.add(v);
                }
            }
        }

        else if(selected.equals("Liftrin")) {
            for (Med v : MedList) {
                if (v.getMedName().equals("Liftrin")) {
                    displayMedList.add(v);
                }
            }
        }

        else if(selected.equals("Vermidon")) {
//            System.out.println("SELECTED SOMETHING");
            for (Med v : MedList) {
                System.out.println(v.getMedName());
                if (v.getMedName().equals("Vermidon")) {
                    displayMedList.add(v);
                }
            }
        }

        recView.setAdapter(new BookAdapter(displayMedList, MedInfoActivity.this));
        recView.setLayoutManager(new LinearLayoutManager(MedInfoActivity.this));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}