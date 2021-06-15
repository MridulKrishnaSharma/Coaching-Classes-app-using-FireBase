package com.knowledge.coachingclasses.mainFragment;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.knowledge.coachingclasses.R;
import com.knowledge.coachingclasses.adapters.notificationAdapter;
import com.knowledge.coachingclasses.modelClasses.notification;

import org.jetbrains.annotations.NotNull;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class NoticeBoardFragment extends Fragment implements notificationAdapter.Inotification_Adapter {

    private RecyclerView recyclerView;
    FirebaseStorage firebaseStorage;

    private notificationAdapter adapter;
//    private notificationDao dao;


    public NoticeBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice_board, container, false);
        //Initialization & findViewbyid
        initializaion(view);

        //recycleView Setting
        recyclerViewSetting();


        return view;
    }

    private void recyclerViewSetting() {

        //Query query=ref.child("Users").orderByChild("name").equalTo(name);
//        Query query = FirebaseFirestore.getInstance().collection(String.valueOf(R.string.notificationCollection)).orderBy("createdAt", Query.Direction.DESCENDING);
        Query query = FirebaseFirestore.getInstance().collection("notificationCollection");

        //setting firestore option
        FirestoreRecyclerOptions<notification> option = new FirestoreRecyclerOptions.Builder<notification>().setQuery(query,notification.class).build();
        //setting adapter to Recyclerview

        adapter = new notificationAdapter(option,this);

        //setting adapter
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initializaion(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.notification_RecyclerView);
//        dao = new notificationDao();
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.stopListening();
    }


    @Override
    public void onImageClicked(String image_url) {

        StorageReference storageReference = firebaseStorage.getInstance().getReference();

        StorageReference ref = storageReference.child("1200px-Om_symbol.svg.png");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                DownloadManager downloadManager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);

                DownloadManager.Request request = new DownloadManager.Request(uri);

                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalFilesDir(getContext(),DIRECTORY_DOWNLOADS,"1200px-Om_symbol.svg.png" );

                downloadManager.enqueue(request);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });




        Uri image_uri =  Uri.parse(image_url);
        Intent intent = new Intent(Intent.ACTION_VIEW, image_uri);
        startActivity(intent);

    }

}