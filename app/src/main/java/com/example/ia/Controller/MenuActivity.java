package com.example.ia.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ia.Modal.Medicine.Med;
import com.example.ia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private Spinner spinner;
    private ArrayList<Med> MedList;
    private ArrayList<Med> filteredList;
    private TextView abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        MedList = new ArrayList<Med>();
        spinner = this.findViewById(R.id.spinner);
        filteredList = new ArrayList<Med>();
        abc = findViewById(R.id.textView12);

        String id = getIntent().getStringExtra("id");
        CollectionReference a = firestore.collection("Med");

        firestore.collection("Med").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                System.out.println(task.getResult());
                QuerySnapshot s = task.getResult();
                s.toObjects(Med.class);
                MedList = (ArrayList<Med>) s.toObjects(Med.class);
                Toast.makeText(getApplicationContext(), MedList.size()+"", Toast.LENGTH_SHORT).show();
            }
        });

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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String selected = spinnerOptions.get(position);
               ArrayList<Med> list = new ArrayList<>();
               for(int i = 0; i < MedList.size(); i++){
                   if(MedList.get(i).getMedName().equals(selected)){
                       list.add(MedList.get(i));
                   }
               }
               filteredList = list;
//               Toast.makeText(getApplicationContext(), MedList.size() + "", Toast.LENGTH_SHORT).show();
               abc.setText(filteredList.size() + "");
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }

        });
    }



    public void goAddMed(View V) {
        Intent intent = new Intent(this, AddMedActivity.class);
        startActivity(intent);
    }

    public void goMedInfo(View v) {
        Intent intent = new Intent(this, MedInfoActivity.class);
        startActivity(intent);
    }
}
