package com.example.ragenmah99.msrsnproject;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Contact extends AppCompatActivity {
    ListView list;
//    private int[] images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_item_layout);
//        int[] images = new int[]{R.drawable.milan, R.drawable.sahas,
//                R.drawable.ragen, R.drawable.rajesh, R.drawable.milan, R.drawable.milan,R.drawable.milan};

        // Showing and Enabling clicks on the Home/Up button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        list= (ListView)findViewById(R.id.recycler_view);
//
//        ArrayList<ContactsContract.Contacts.Data> data= new ArrayList<>();

//        data.add(new ContactsContract.Contacts.Data( R.drawable.milan, "Image 1"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.sahas, "Image 2"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.ragen, "Image 3"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.rajesh, "Image 1"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.rajesh, "Image 2"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.milan, "Image 3"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.milan, "Image 1"));
////        data.add(new ContactsContract.Contacts.Data( R.drawable.bg, "Image 2"));
////        data.add(new ContactsContract.Contacts.Data( R.drawable.bg, "Image 3"));

        list.setAdapter(new VivzAdapter(this));
    }
//    public List<ContactsContract.Contacts.Data> fill_with_data() {
//
//        List<ContactsContract.Contacts.Data> data = new ArrayList<>();
//
//        data.add(new ContactsContract.Contacts.Data( R.drawable.milan, "Image 1"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.sahas, "Image 2"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.ragen, "Image 3"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.rajesh, "Image 1"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.rajesh, "Image 2"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.milan, "Image 3"));
//        data.add(new ContactsContract.Contacts.Data( R.drawable.milan, "Image 1"));
//
//
//        return data;
//    }
    public class SingleRow{
        String memberName;
        String emailad;
        String images;
        String post;
        //        String contact;
        SingleRow(String memberName, String emailad, String images,String post){

//        }, String contact){
            this.memberName= memberName;
            this.emailad= emailad;
//            this.contact= contact;
            this.images = images;
            this.post=post;
        }
//        public int getImageId(){return images;}
//        public void setImageId(int images){
//            this.images=images;
//        }
    }
    class VivzAdapter extends BaseAdapter {
        ArrayList<SingleRow> list;
        Context context;
        VivzAdapter(Context c){
            context = c;
            list = new ArrayList<SingleRow>();
            Resources res = c.getResources();
            String[] memberName= res.getStringArray(R.array.team_name);
            String[] email = res.getStringArray(R.array.email);
            String[] image= res.getStringArray(R.array.image_array);
            String[] post= res.getStringArray(R.array.post_name);

            for(int i=0;i<7;i++){
                list.add(new SingleRow(memberName[i], email[i],image[i],post[i]));
//                , contact[i]));
            }
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.contact_item_row, parent, false);
            // SingleRow singleRow = new SingleRow(title[i],);
            ImageView place = (ImageView)row.findViewById(R.id.images);
            TextView title = (TextView) row.findViewById(R.id.team_member);
            TextView location = (TextView) row.findViewById(R.id.email);
            TextView post=(TextView) row.findViewById(R.id.post);
//            Button contact = (Button) row.findViewById(R.id.contact);
            final SingleRow temp = list.get(i);
            title.setText(temp.memberName);
            location.setText(temp.emailad);
            post.setText(temp.post);

//            place.setImageResource(temp.images);
//            contact.setText(temp.contact);

//            contact.setClickable(true);
//            contact.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i;
//                    i = new Intent(Intent.ACTION_DIAL);
//                    i.setData(Uri.parse("tel:"+temp.contact));
//                    startActivity(i);
//                }
//            });
//kitpro
//creativeteam
            return  row;
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    }