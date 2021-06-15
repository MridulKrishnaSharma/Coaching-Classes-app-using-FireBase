package com.knowledge.coachingclasses.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knowledge.coachingclasses.R;
import com.knowledge.coachingclasses.modelClasses.teachers_details;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class teachers_detail_adapter extends RecyclerView.Adapter<teachers_detail_adapter.teacherDeatilsViewHolder>{

    private ArrayList<teachers_details> teachersArray = new ArrayList<>();

    public teachers_detail_adapter(ArrayList<teachers_details> teachersArray) {
        this.teachersArray = teachersArray;
    }

    @NotNull
    @Override
    public teacherDeatilsViewHolder onCreateViewHolder( @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teachers_details_item, parent,false);
        teacherDeatilsViewHolder holder = new teacherDeatilsViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder( @NotNull teacherDeatilsViewHolder holder, int position) {
        teachers_details currentTeacher = teachersArray.get(position);
        holder.name.setText(currentTeacher.getTeacherName());
        holder.bio.setText("'"+currentTeacher.getBio()+"'");
        //setting Image
        holder.image.setImageResource(currentTeacher.getDrawable_value());

    }

    @Override
    public int getItemCount() {
        return teachersArray.size();
    }


    //view holder class
    class teacherDeatilsViewHolder extends RecyclerView.ViewHolder{

        public teacherDeatilsViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public ImageView image = (ImageView) itemView.findViewById(R.id.teacher_image);
        public TextView name = (TextView) itemView.findViewById(R.id.teacher_name);
        public TextView bio = (TextView) itemView.findViewById(R.id.teacher_bio);

    }
}
