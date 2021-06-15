package com.knowledge.coachingclasses.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.knowledge.coachingclasses.R;
import com.knowledge.coachingclasses.modelClasses.class_details;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class class_list_Adapter extends FirestoreRecyclerAdapter<class_details,class_list_Adapter.classesViewHolder> {

//Array list for card layout background
    private ArrayList<Integer> bg_drawable_values = new ArrayList<>();
    //listener for class clicked
    Iclass_list_Adapter listener;
    public class_list_Adapter(@NonNull @NotNull FirestoreRecyclerOptions<class_details> options, Iclass_list_Adapter listener) {
        super(options);
        this.listener = listener;
        bg_drawable_values.add(R.drawable.class_list_background_0);
        bg_drawable_values.add(R.drawable.class_list_background_1);
        bg_drawable_values.add(R.drawable.class_list_background_2);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull classesViewHolder holder, int position, @NonNull @NotNull class_details model) {
        holder.class_subject.setText(model.getClass_subject());
        holder.class_name.setText(model.getClass_name());
        holder.class_teacher.setText(model.getClass_teacher());

        int class_list_background_drawable_value = bg_drawable_values.get(2) ;

        for(int i = 0 ; i < bg_drawable_values.size(); i++){
            if (i == model.getBackGround_value()){
                 class_list_background_drawable_value = bg_drawable_values.get(i) ;
                              }
        }
//        // getting random BG for layout.
//        int index = (int)(Math.random() * bg_drawable_values.size());
        // setting backGround for drawable
        holder.class_background.setBackground(ContextCompat.getDrawable(holder.class_background.getContext(), class_list_background_drawable_value));

    }

    @NotNull
    @Override
    public classesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_list_item, parent , false);
        classesViewHolder holder =  new classesViewHolder(view);
        holder.class_background.setOnClickListener(v ->{
            DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
            String class_code = snapshot.getString("class_code");
            String class_subject = snapshot.getString("class_subject");
            String teacher_name = snapshot.getString("class_teacher") ;

            listener.onClassClicked(class_code, class_subject, teacher_name);
        });

        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    //view Holder class
    class classesViewHolder extends RecyclerView.ViewHolder{

        public classesViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public TextView class_subject = (TextView) itemView.findViewById(R.id.class_subject);
        public TextView class_name = (TextView) itemView.findViewById(R.id.class_name);
        public TextView class_teacher = (TextView) itemView.findViewById(R.id.class_teacher);
        public ConstraintLayout class_background = (ConstraintLayout) itemView.findViewById(R.id.class_background);

    }

    //interface listiner
    public interface Iclass_list_Adapter{
        void onClassClicked(String class_code, String class_subject, String teacher_name );
    }
}
