package com.example.ia.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.ia.Modal.Medicine.Afterin;
import com.example.ia.Modal.Medicine.Avetrol;
import com.example.ia.Modal.Medicine.Caldril;
import com.example.ia.Modal.Medicine.Kaynesten;
import com.example.ia.Modal.Medicine.Lastoril;
import com.example.ia.Modal.Medicine.Liftrin;
import com.example.ia.Modal.Medicine.Vermidon;
import com.example.ia.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddMedActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private LinearLayout layout;

    private EditText activeIngrediantField;
    private EditText typeField;
    private EditText strengthField;
    private EditText countryField;
    private EditText maDateField;
    private EditText maExpDateField;
    private EditText maRewDateField;
    private EditText maPrepDateField;


    private Spinner userRoleSpinner;
    private String selectedRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        layout = findViewById(R.id.LOaddMed);
        userRoleSpinner = (Spinner) findViewById(R.id.SPaddMed);
        setupSpinner();
    }


    private void setupSpinner() {
        String[] userTypes = {"Vermidon", "Caldril", "Lastoril", "Avetrol", "Afterin", "Lifterin", "Kaynesten"};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(AddMedActivity.this,
                android.R.layout.simple_spinner_item, userTypes);
        langArrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userRoleSpinner.setAdapter(langArrAdapter);

        //triggered whenever user selects something different
        userRoleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedRole = parent.getItemAtPosition(position).toString();
                addFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addFields(){
        commonFields();
    }

    public void commonFields(){
        layout.removeAllViewsInLayout();
        activeIngrediantField = new EditText(this);
        activeIngrediantField.setHint("Active Ingrediant");
        layout.addView(activeIngrediantField);
        strengthField = new EditText(this);
        strengthField.setHint("Strength");
        layout.addView(strengthField);
        typeField = new EditText(this);
        typeField.setHint("Type");
        layout.addView(typeField);
        countryField = new EditText(this);
        countryField.setHint("country");
        layout.addView(countryField);
        maDateField = new EditText(this);
        maDateField.setHint("MA Date");
        layout.addView(maDateField);
        maExpDateField = new EditText(this);
        maExpDateField.setHint("MA Expiry Date");
        layout.addView(maExpDateField);
        maRewDateField = new EditText(this);
        maRewDateField.setHint("MA Renewal Date");
        layout.addView(maRewDateField);
        maPrepDateField = new EditText(this);
        maPrepDateField.setHint("Preperation Date");
        layout.addView(maPrepDateField);
    }

    public void addMed(View v){
        String activeIngrediantString = activeIngrediantField.getText().toString();
        String strengthString = strengthField.getText().toString();
        String typeString = typeField.getText().toString();
        String countryString = countryField.getText().toString();
        String maDateString = maDateField.getText().toString();
        String maExpDateString = maExpDateField.getText().toString();
        String maRewDate = maRewDateField.getText().toString();
        String maPrepDate = maPrepDateField.getText().toString();


        DocumentReference newRideRef = firestore.collection(Constants.MED_CONSTANT).document();
        String medIDString = newRideRef.getId();

        if(selectedRole.equals("Vermidon")){
            Vermidon newVermidon = new Vermidon(activeIngrediantString, strengthString, typeString, countryString, maDateString, maExpDateString, maRewDate, maPrepDate, medIDString, "Vermidon");
            firestore.collection("Med").document(medIDString).set(newVermidon);
        }

        if(selectedRole.equals("Caldril")){
            Caldril newCaldril = new Caldril(activeIngrediantString, strengthString, typeString, countryString, maDateString, maExpDateString, maRewDate, maPrepDate, medIDString, "Caldril");
            firestore.collection("Med").document(medIDString).set(newCaldril);
        }

        if(selectedRole.equals("Lastoril")){
            Lastoril newLastoril = new Lastoril(activeIngrediantString, strengthString, typeString, countryString, maDateString, maExpDateString, maRewDate, maPrepDate, medIDString, "Lastoril");
            firestore.collection("Med").document(medIDString).set(newLastoril);
        }

        if(selectedRole.equals("Avetrol")){
            Avetrol newAvetrol = new Avetrol(activeIngrediantString, strengthString, typeString, countryString, maDateString, maExpDateString, maRewDate, maPrepDate, medIDString, "Avetrol");
            firestore.collection("Med").document(medIDString).set(newAvetrol);
        }

        if(selectedRole.equals("Afterin")){
            Afterin newAfterin = new Afterin(activeIngrediantString, strengthString, typeString, countryString, maDateString, maExpDateString, maRewDate, maPrepDate, medIDString, "Afterin");
            firestore.collection("Med").document(medIDString).set(newAfterin);
        }

        if(selectedRole.equals("Lifterin")){
            Liftrin newLifterin = new Liftrin(activeIngrediantString, strengthString, typeString, countryString, maDateString, maExpDateString, maRewDate, maPrepDate, medIDString, "Lifterin");
            firestore.collection("Med").document(medIDString).set(newLifterin);
        }

        if(selectedRole.equals("Kaynesten")){
            Kaynesten newKaynesten = new Kaynesten(activeIngrediantString, strengthString, typeString, countryString, maDateString, maExpDateString, maRewDate, maPrepDate, medIDString, "Kaynesten");
            firestore.collection("Med").document(medIDString).set(newKaynesten);
        }
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}