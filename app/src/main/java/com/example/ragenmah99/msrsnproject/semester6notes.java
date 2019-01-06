package com.example.ragenmah99.msrsnproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class semester6notes  extends AppCompatActivity {
    String s1,s2,s3,s4,s5,s6,s7,s8;
    public class PDFDoc {
        int id;
        String name,category,pdfURL,pdfIconURL;
        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
        public String getAuthor() {return category;}
        public void setCategory(String category) {this.category = category;}
        public String getPdfURL() {return pdfURL;}
        public void setPdfURL(String pdfURL) {this.pdfURL = pdfURL;}
        public String getPdfIconURL() {return pdfIconURL;}
        public void setPdfIconURL(String pdfIconURL) {this.pdfIconURL = pdfIconURL;}
    }
    /*
     *  our customer adapter class
     * */

    public class GridViewAdapter extends BaseAdapter {
        Context c;
        ArrayList<PDFDoc> pdfDocuments;

        public GridViewAdapter(Context c, ArrayList<PDFDoc> pdfDocuments) {
            this.c = c;
            this.pdfDocuments = pdfDocuments;
        }
        @Override
        public int getCount() {return pdfDocuments.size();}
        @Override
        public Object getItem(int pos) {return pdfDocuments.get(pos);}
        @Override
        public long getItemId(int pos) {return pos;}
        @Override
        public View getView(int pos, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                view= LayoutInflater.from(c).inflate(R.layout.row_layout,viewGroup,false);
            }

            TextView txtName = view.findViewById(R.id.pdfNameTxt);
            TextView txtAuthor = view.findViewById(R.id.authorTxt);
            ImageView pdfIcon = view.findViewById(R.id.imageView);

            final PDFDoc pdf= (PDFDoc) this.getItem(pos);

            txtName.setText(pdf.getName());
            txtAuthor.setText(pdf.getAuthor());

            if(pdf.getPdfURL() != null && pdf.getPdfURL().length()>0)
            {
                Picasso.get().load(pdf.getPdfIconURL()).placeholder(R.drawable.pdf_img).into(pdfIcon);
            }else {
                Toast.makeText(c, "Empty Image URL", Toast.LENGTH_LONG).show();
                Picasso.get().load(R.drawable.pdf_img).into(pdfIcon);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(c, pdf.getName(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(c,Question_PDFActivity.class);
                    i.putExtra("PATH",pdf.getPdfURL());
                    i.putExtra("file_name",pdf.getName());
                    c.startActivity(i);
                }
            });

            return view;
        }
    }

    //    private void permission_check() {
//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
//                return;
//            }
//        }
//
//        initialize();
//    }
    /*
    Our HTTP Client
     */
    public class JSONDownloader {
        //        private  String s1= getIntent().getExtras().getString("s1");
        private static   final String PDF_SITE_URL="http://msrsn.com/android/notes_download.php?"+"s1=6";
        private final Context c;
        private GridViewAdapter adapter ;
        private static final String PDF_SITE="http://msrsn.com/";

        public JSONDownloader(Context c) {
            this.c = c;
        }
        /*
        DOWNLOAD PDFS FROM MYSQL
         */
        public void retrieve(final GridView gv, final ProgressBar myProgressBar)
        {
            final ArrayList<PDFDoc> pdfDocuments = new ArrayList<>();

            myProgressBar.setIndeterminate(true);
            myProgressBar.setVisibility(View.VISIBLE);

            AndroidNetworking.get(PDF_SITE_URL)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            JSONObject jo;
                            PDFDoc p;
                            try
                            {
                                for(int i=0;i<response.length();i++)
                                {
                                    jo=response.getJSONObject(i);

                                    int id=jo.getInt("id");
//                                    String name=jo.getString("sem_name");
                                    String name=jo.getString("subject_id");;
//                                    String category=jo.getString("category");
//                                    String description=jo.getString("description");
                                    String pdfURL=jo.getString("file");
//                                    String pdfIconURL=jo.getString("image");

                                    p=new PDFDoc();
                                    p.setId(id);
                                    p.setName(name);
//                                    p.setCategory(category);
//                                    p.setCategory(description);
                                    p.setPdfURL(PDF_SITE+pdfURL);
//                                    p.setPdfIconURL(PDF_SITE+pdfIconURL);

                                    pdfDocuments.add(p);
                                }
                                adapter =new GridViewAdapter(c,pdfDocuments);
                                gv.setAdapter(adapter);
                                myProgressBar.setVisibility(View.GONE);

                            }catch (JSONException e)
                            {
                                myProgressBar.setVisibility(View.GONE);
                                Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                        //ERROR
                        @Override
                        public void onError(ANError error) {
                            error.printStackTrace();
                            myProgressBar.setVisibility(View.GONE);
//                            Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+error.getMessage(), Toast.LENGTH_LONG).show();
                            Toast.makeText(c, "Couldnot respond. Please connect WiFi ", Toast.LENGTH_LONG).show();

                        }
                    });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

//        permission_check();
        final GridView myGridView= findViewById(R.id.myGridView);
//        Button btnRetrieve= findViewById(R.id.downloadBtn);
        final ProgressBar myProgressBar= findViewById(R.id.myProgressBar);
        // Showing and Enabling clicks on the Home/Up button


        new JSONDownloader(semester6notes.this).retrieve(myGridView,myProgressBar);

//        s1=getIntent().getExtras().getString("s1");;



        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

//        btnRetrieve.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                new JSONDownloader(Notes_main.this).retrieve(myGridView,myProgressBar);
//            }
//        });
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

