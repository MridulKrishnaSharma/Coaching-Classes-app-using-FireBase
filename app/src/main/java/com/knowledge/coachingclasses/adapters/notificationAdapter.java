package com.knowledge.coachingclasses.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
  import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.firebase.firestore.DocumentSnapshot;
import com.knowledge.coachingclasses.R;
import com.knowledge.coachingclasses.modelClasses.notification;

import org.jetbrains.annotations.NotNull;

public class notificationAdapter extends FirestoreRecyclerAdapter<notification, RecyclerView.ViewHolder>  {

    private static  int text_viewholder ;
    private static  int image_viewholder ;
    //listiner
    notificationAdapter.Inotification_Adapter listener;



    public notificationAdapter(@NonNull FirestoreRecyclerOptions<notification> options, Inotification_Adapter listener) {
          super(options);
          this.listener = listener;
        text_viewholder = 0;
        image_viewholder = 1;
      }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position, @NonNull @NotNull notification model) {

        //checking for view type
        if(getItemViewType(position) == text_viewholder){
            ((text_notification_ViewHolder)holder).header1.setText(model.getHeader());
            ((text_notification_ViewHolder)holder).text_notice1.setText(model.getNotice());
            ((text_notification_ViewHolder)holder).createdAt1.setText(model.getCreatedAt().toString());
        } else if(getItemViewType(position) == image_viewholder){

            ((image_notification_ViewHolder)holder).header2.setText(model.getHeader());

            Glide.with(((image_notification_ViewHolder)holder).image_notice2.getContext()).load(model.getUrl()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    ((image_notification_ViewHolder)holder).progressBar2.setVisibility(View.VISIBLE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    ((image_notification_ViewHolder)holder).progressBar2.setVisibility(View.GONE);
                    return false;
                }
            }).into(((image_notification_ViewHolder)holder).image_notice2);

            ((image_notification_ViewHolder)holder).createdAt2.setText(model.getCreatedAt().toString());

        }

    }


    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //checking for view type of view holder
        if (viewType == text_viewholder){
            System.out.println("777777777777777777777777777");

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_1, parent , false);
        text_notification_ViewHolder holder = new text_notification_ViewHolder(view);
        return holder;
        }
        else {
            System.out.println("88888888888888888888888888888888888888888888888888888888888");

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_2, parent , false);
            image_notification_ViewHolder holder = new image_notification_ViewHolder(view);

            // setting on click listiner for image view expreiment
            holder.image_notice2.setOnClickListener(v ->{
                DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
                String image_url = snapshot.getString("url");

                listener.onImageClicked(image_url);
            });


            return holder;
        }
        }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position).getUrl().equals("null")){
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(getItem(position).getUrl());
            int text_viewholder = notificationAdapter.text_viewholder;
            return text_viewholder;
        } else {
            System.out.println("////////////////////////////////////////////////////////");

            System.out.println(getItem(position).getUrl());

            int image_viewholder = notificationAdapter.image_viewholder;
            return image_viewholder;
        }

    }


    // view holder to show text notification
    class text_notification_ViewHolder extends RecyclerView.ViewHolder{

        public text_notification_ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public TextView header1 = (TextView) itemView.findViewById(R.id.header1);
        public TextView text_notice1 = (TextView) itemView.findViewById(R.id.text_notice1);
        public TextView createdAt1 = (TextView) itemView.findViewById(R.id.createdAt1);

    }

    //view holder to show Image notification
    class image_notification_ViewHolder extends RecyclerView.ViewHolder{

        public image_notification_ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public TextView header2 = (TextView) itemView.findViewById(R.id.header2);
        public ImageView image_notice2 = (ImageView) itemView.findViewById(R.id.image_notice2);
        public TextView createdAt2 = (TextView) itemView.findViewById(R.id.createdAt2);
        public ProgressBar progressBar2 = (ProgressBar) itemView.findViewById(R.id.image_progressbar2);

    }

    //interface listiner
    public interface Inotification_Adapter{
        void onImageClicked(String image_url);
    }

}
