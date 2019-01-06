package com.example.ragenmah99.msrsnproject;


import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


    public class Contact_Adapter extends RecyclerView.Adapter<Contact_Adapter.ViewHolder> {

        private Context context;
        private List<Album> albumList;
        private LayoutInflater inflater;

        //ViewHolder class
        //TextView and ImageView holders are binded with relevant views in item of recyclerview.
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView team_member;
            public TextView post;
            public ImageView imagesId;
            public TextView email;
            public int position=0;

            public ViewHolder(View v) {
                super(v);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //When item view is clicked, trigger the itemclicklistener
                        //Because that itemclicklistener is indicated in MainActivity
//                        recyclerViewItemClickListener.onItemClick(v,position);
                    }
                });

                team_member=(TextView)v.findViewById(R.id.team_member);
                post=(TextView)v.findViewById(R.id.post);
                imagesId=(ImageView)v.findViewById(R.id.imagesId);
                email=(TextView)v.findViewById(R.id.email);

            }
        }





        //Constructor of RecyclerViewAdapter
        //It obtains model list coming from MainActivity here
        public Contact_Adapter(Context context,List<Album> albumList) {
            this.context=context;
            this.albumList=albumList;
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        //Adapter request a new item view
        //Create and return it.
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            View v = inflater.inflate(R.layout.contact_item_row, parent, false);
            return new ViewHolder(v);
        }

        //Last step before item is placed in recyclerview
        //TextViews and ImageView in viewholder which is attached to view is set with datas in model list
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.position=position;
            holder.team_member.setText(albumList.get(position).getMemberName());
            holder.post.setText(albumList.get(position).getPost());
            setImageViewBackgroundWithADrawable(holder.imagesId, albumList.get(position).getImages());
            holder.email.setText(albumList.get(position).getEmailId());

        }

        @Override
        public int getItemCount() {
            return albumList.size();
        }

        //setBackground method is different for some android versions.
        public void setImageViewBackgroundWithADrawable(ImageView image,int drawable){
            if(Build.VERSION.SDK_INT >=22){
                image.setBackground(context.getResources().getDrawable(drawable, null));
            }
            else if(Build.VERSION.SDK_INT >= 16){
                image.setBackground(context.getResources().getDrawable(drawable));
            }else{
                image.setBackgroundDrawable(context.getResources().getDrawable(drawable));
            }
        }
    }