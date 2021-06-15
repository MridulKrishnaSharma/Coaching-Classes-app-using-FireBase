package com.knowledge.coachingclasses;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.gson.Gson;
import com.knowledge.coachingclasses.firebaseDao.userDao;
import com.knowledge.coachingclasses.modelClasses.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignIn extends AppCompatActivity {

    private static final int RC_SIGN_IN = 2021;

    // we will pass this in thread pool
    //thread pool
    private static final int number_of_threads = 2;
    // we will define ExecitorService to execute th i\o function of database at background thread
    static final ExecutorService threadPool = Executors.newFixedThreadPool(number_of_threads);


    private EditText enterName;
    private EditText enterClass;
    private EditText enterPhoneNumber;
    private CheckBox checkTandC;
    private TextView termsAndConditions;
    private Button getStarted;
    private ProgressBar progressBar;

    //firebase Auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Initialization & findViewbyid
        initializaion();

        //all OnClick Listiner
        allOnClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //checker user is login or not
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //updateUI
        updateUI(user);

    }

    private void allOnClick() {

        //checking if the button is checked or not
        checkTandC.setOnClickListener(l -> {
            //checking if value is entered or not
            boolean valueResult = true;
//            if (enterName.getText().toString().isEmpty()||enterClass.getText().toString().isEmpty()||enterPhoneNumber.getText().toString().isEmpty())
            if (enterName.getText().toString().trim().isEmpty()
                    || enterClass.getText().toString().trim().isEmpty()
                    || enterPhoneNumber.getText().toString().trim().isEmpty()) {
                valueResult = false;
                Toast.makeText(this, "Please fill all Fields ", Toast.LENGTH_SHORT).show();
                checkTandC.setChecked(false);
            }

            if (checkTandC.isChecked() && valueResult) {
                getStarted.setEnabled(true);
            } else {

                getStarted.setEnabled(false);
            }
        });

        // calling AuthUI
        getStarted.setOnClickListener(l -> {

            singin();
        });
    }

    private void singin() {
        // using AuthUI for singin
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

// Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if(resultCode == RESULT_OK){
                //successfully signed in
                FirebaseUser fireBaseUser = mAuth.getCurrentUser();
                // Array list foe demo class
                ArrayList<String> classCode = new ArrayList<>();
                classCode.add("democlass2021");
                user currentUser = new user (fireBaseUser.getUid(),enterName.getText().toString(), fireBaseUser.getPhotoUrl().toString(),
                        enterPhoneNumber.getText().toString(), enterClass.getText().toString(), classCode, 0) ;

                //user dao to add user to data base
                userDao userdao = new userDao();
// check user saved in data base or not
                    userdao.addUser(currentUser);

                //save customer data into Shared preference
                saveDataintoSharedPreference(currentUser);
                updateUI(fireBaseUser);

            } else {
                // signed failed
//                Toast.makeText(this, response.getError().getMessage(), Toast.LENGTH_SHORT).show();

                updateUI(null);
            }
        }

    }

    private void initializaion() {
        enterName = (EditText) findViewById(R.id.enterName);
        enterClass = (EditText) findViewById(R.id.enterClass);
        enterPhoneNumber = (EditText) findViewById(R.id.enterPhoneNumber);
        checkTandC = (CheckBox) findViewById(R.id.checkTermsConditions);
        termsAndConditions = (TextView) findViewById(R.id.termsConditions);
        getStarted = (Button) findViewById(R.id.getStarted);
        getStarted.setEnabled(false); // disabling get started Button
        this.mAuth =  FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.sign_progressBar);
        progressBar.setVisibility(View.GONE);

    }

    //save customer data into Shared preference
    private void saveDataintoSharedPreference(user u){
        progressBar.setVisibility(View.VISIBLE);

        // creating thread pool
        threadPool.execute(() ->{
            userDao dao = new userDao();

// getting user data from fireBase and stored in shared preference
        dao.getUserById(u.getUserId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                 user currentUser = documentSnapshot.toObject(user.class);

                 // saving data in shared preference

                SharedPreferences sp = getSharedPreferences(getString(R.string.user_Details_sp),MODE_PRIVATE );
                SharedPreferences.Editor sp_editor = sp.edit();

                //user details in shared preference
                sp_editor.putString("userId", currentUser.getUserId());
                sp_editor.putString("displayName", currentUser.getDisplayName());
                sp_editor.putString("imageUrl",currentUser.getImageUrl() );
                sp_editor.putString("phoneNumber", currentUser.getPhoneNumber());
                sp_editor.putString("classes", currentUser.getClasses());
                sp_editor.putInt("points", currentUser.getPoints());

                //storing classCode arrayList in share preference by converting it into Json text
                // creating a new variable for gson.
                Gson gson = new Gson();

                // getting data from gson and storing it in a string.
                String json = gson.toJson(currentUser.getClassCodes());

                // below line is to save data in shared
                // prefs in the form of string.
                sp_editor.putString("classCodes", json);

                // below line is to apply changes
                // and save data in shared prefs.
                sp_editor.commit();
                sp_editor.apply();
            }
         });
        });
        //clossing the thread pool
threadPool.shutdown();

        progressBar.setVisibility(View.GONE);
    }




    // updating Ui if User is login
    private void updateUI(FirebaseUser firebaseUser) {

        if (firebaseUser != null) {

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();

        } else {
            Toast.makeText(this, "Login to Get Started", Toast.LENGTH_SHORT).show();

        }

    }
}