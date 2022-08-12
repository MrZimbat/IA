package com.example.ia.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ia.Modal.UserType.Country;
import com.example.ia.Modal.UserType.Global;
import com.example.ia.Modal.UserType.Regional;
import com.example.ia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private LinearLayout layout;
    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;
    private EditText regionField;
    private EditText countryField;
    private Spinner userRoleSpinner;
    private String selectedRole;
    private String uid;
    private static int uidGenerator = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        layout = findViewById(R.id.linearLayoutCreateUser);
        userRoleSpinner = findViewById(R.id.selectTypeSpinnerspinner);
        setupSpinner();
        uid = "" + uidGenerator;
        uidGenerator++;
    }

    private void setupSpinner() {
        String[] userType = {"Regional regulatory head", "Country regulatory head", "Global regulatory head"};
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(SignUpActivity.this,
                android.R.layout.simple_spinner_item, userType);
        langArrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userRoleSpinner.setAdapter(langArrAdapter);

        //triggered whenever user selects something different
        userRoleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRole = parent.getItemAtPosition(position).toString();
                addFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public void addFields() {
        commonFields();
        if(selectedRole.equals("Regional regulatory head")){
            regionField = new EditText(this);
            regionField.setHint("region");
            layout.addView(regionField);
        }

        if(selectedRole.equals("Country regulatory head")){
            countryField = new EditText(this);
            countryField.setHint("country");
            layout.addView(countryField);
        }
    }

    public void commonFields() {
        layout.removeAllViewsInLayout();
        nameField = new EditText(this);
        nameField.setHint("Name");
        layout.addView(nameField);
        emailField = new EditText(this);
        emailField.setHint("Email");
        layout.addView(emailField);
        passwordField = new EditText(this);
        passwordField.setHint("Password");
        layout.addView(passwordField);
    }

    public void SignUp(View v){

        String nameString = nameField.getText().toString();
        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();

        DocumentReference newRideRef = firestore.collection(Constants.MED_CONSTANT).document();
        String UserId = newRideRef.getId();

        mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d("SIGN UP", "successfully signed up the user");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                        else {
                            Log.d("SIGN UP", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this,"Sign up failed", Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
        if(selectedRole.equals("Country")){
            String countrya = String.valueOf(countryField.getText());
            Country newUser = new Country(nameString, emailString, "Country", UserId, countrya);
            uidGenerator++;
            firestore.collection("User").document(UserId).set(newUser);
        }
        if(selectedRole.equals("Regional")){
            String regiona = String.valueOf(regionField.getText());
            Regional newUser = new Regional(nameString, emailString, "Regional", UserId, regiona);
            uidGenerator++;
            firestore.collection("User").document(UserId).set(newUser);
        }
        if(selectedRole.equals("Global")){
            Global newUser = new Global(nameString, emailString, "Global", UserId);
            uidGenerator++;
            firestore.collection("User").document(UserId).set(newUser);
        }

    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }

    }
}