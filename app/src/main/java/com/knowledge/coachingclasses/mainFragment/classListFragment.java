package com.knowledge.coachingclasses.mainFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.knowledge.coachingclasses.ClassRoom;
import com.knowledge.coachingclasses.R;
import com.knowledge.coachingclasses.adapters.class_list_Adapter;
import com.knowledge.coachingclasses.modelClasses.class_details;

import java.util.Arrays;


public class classListFragment extends Fragment implements class_list_Adapter.Iclass_list_Adapter {

    private RecyclerView recyclerView;

    private class_list_Adapter adapter;


    public classListFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class_list, container, false);
        //Initialization & findViewbyid
        initializaion(view);

//        recycleView Setting
        recyclerViewSetting();

        return view;
    }


//Recycler View
    private void recyclerViewSetting() {

//        String[] demo = {};
//        demo.add("democlass2021");
        Query query = FirebaseFirestore.getInstance().collection("classes").whereIn("class_code",Arrays.asList("democlass2021") );
//        Query query = FirebaseFirestore.getInstance().collection("classes");

        //setting firestore option
        FirestoreRecyclerOptions<class_details> option = new FirestoreRecyclerOptions.Builder<class_details>().setQuery(query,class_details.class).build();
        //setting adapter to Recyclerview

        adapter = new class_list_Adapter(option, this);

        //setting adapter
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


//find view by id
    private void initializaion(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.class_list_RecyclerView);
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
    public void onClassClicked(String class_code, String class_subject, String teacher_name) {
        Intent i = new Intent(getContext(), ClassRoom.class);
        i.putExtra("class_code", class_code);
        i.putExtra("class_subject", class_subject);
        i.putExtra("teacher_name", teacher_name);

        startActivity(i);
    }
}