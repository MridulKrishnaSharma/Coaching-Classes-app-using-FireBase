package com.knowledge.coachingclasses.firebaseDao;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.knowledge.coachingclasses.modelClasses.user;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class userDao {

    // firebase firestore data base reference
    private FirebaseFirestore db;
    boolean result;

    //thread pool
    private static final int number_of_threads = 2;
    // we will define ExecitorService to execute th i\o function of database at background thread
    static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(number_of_threads);

    //Collection name
    private final static String usersCollection = "users";


    public userDao() {

        this.db = FirebaseFirestore.getInstance();
        this.result = false;

    }

    // function to add new user data on firestore
//db.collection("cities").document("new-city-id").set(data);

    public boolean addUser(user currentUser){
        if (currentUser != null){
            //on new thread
            databaseWriterExecutor.execute(() -> {

            this.db.collection(usersCollection)
                    .document(currentUser.getUserId())
                    .set(currentUser).addOnSuccessListener(new OnSuccessListener<Void>() {

                @Override
                public void onSuccess(Void unused) {

                    // return true on successfull addition
                    result = true;
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // return false on faliure
                    result = false;
                }
            });
                });
        }
        databaseWriterExecutor.shutdown();
   return result;
    }

    // to read user form data base we need documentReference of the user
    public DocumentReference getUserById(String uId){
        return this.db.collection(usersCollection).document(uId);
    }




}
