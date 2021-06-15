package com.knowledge.coachingclasses.mainFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knowledge.coachingclasses.R;
import com.knowledge.coachingclasses.adapters.teachers_detail_adapter;
import com.knowledge.coachingclasses.modelClasses.teachers_details;

import java.util.ArrayList;


public class Teachers_Detail_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private teachers_detail_adapter adapter;
    private ArrayList<teachers_details> teachersArray = new ArrayList<>();

    public Teachers_Detail_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teachers__detail, container, false);
        //Initialization & findViewbyid
        initializaion(view);

        //recycleView Setting
        recyclerViewSetting();

        return view;
    }

    private void recyclerViewSetting() {

        //setting adapter
        adapter = new teachers_detail_adapter(teachersArray);

        //setting adapter
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initializaion(View view) {
        recyclerView = view.findViewById(R.id.teachers_recyclerView);

        // adding data to Array of teachers
       teachersArray.add(new teachers_details(R.drawable.teachers_image,getString(R.string.teachers_name),getString(R.string.Teachers_Bio)));

        teachersArray.add(new teachers_details(R.drawable.teachers_image,getString(R.string.teachers_name),getString(R.string.Teachers_Bio)));

        teachersArray.add(new teachers_details(R.drawable.teachers_image,getString(R.string.teachers_name),getString(R.string.Teachers_Bio)));

        teachersArray.add(new teachers_details(R.drawable.teachers_image,getString(R.string.teachers_name),getString(R.string.Teachers_Bio)));

        teachersArray.add(new teachers_details(R.drawable.teachers_image,getString(R.string.teachers_name),getString(R.string.Teachers_Bio)));

    }
}