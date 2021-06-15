package com.knowledge.coachingclasses.firebaseDao;



import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;



public class notificationDao {


    // objecr of firebase store work as database
    private FirebaseFirestore db;



    public notificationDao() {
        this.db = FirebaseFirestore.getInstance();
    }

   /* public void addPost(String text) {

    }*/

    // to get postby ID
    private DocumentReference getNotificationById(String s) {
        return this.db.collection("notificationCollection").document(s);
    }



}

