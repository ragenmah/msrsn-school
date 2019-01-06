package com.example.ragenmah99.msrsnproject;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;


public class Question_PDFActivity extends AppCompatActivity implements OnLoadCompleteListener, OnPageErrorListener {
    Button button;
    Long queueId;
    DownloadManager downloadManager;
    // Progress Dialog
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;


    ProgressBar pdfViewProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_pdf);
        button=(Button)findViewById(R.id.dwnloaddBtn);

        // Showing and Enabling clicks on the Home/Up button
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final PDFView pdfView= findViewById(R.id.pdfView);
        pdfViewProgressBar=findViewById(R.id.pdfViewProgressBar);

        pdfViewProgressBar.setVisibility(View.VISIBLE);

        //UNPACK OUR DATA FROM INTENT
        Intent i=this.getIntent();
        final String path=i.getExtras().getString("PATH");
        final String filename=i.getExtras().getString("file_name");

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
//                 Uri uri=Uri.parse("http://msrsn.com/Questions/8Comp2018.pdf");
                Uri uri=Uri.parse(path);
                DownloadManager.Request request=new DownloadManager.Request(uri);
                //Setting title of request
                request.setTitle(filename);

                //Setting description of request
//                 request.setDescription("Android Data download using DownloadManager.");
                request.setDescription("Your Data is downloading.");

                //Set the local destination for the downloaded file to a path within the application's external files directory

                request.setDestinationInExternalFilesDir(Question_PDFActivity.this, Environment.DIRECTORY_DOWNLOADS,filename+" QUESTIONS.pdf");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                queueId=downloadManager.enqueue(request);
                Toast toast = Toast.makeText(Question_PDFActivity.this,
                        "Download Started", Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
//                return queueId;
//                 new DownloadFileFromURL().execute("http://msrsn.com/Questions/8Comp2018.pdf");



            }
        });


        FileLoader.with(this)
                .load(path,false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory("My_PDFs", FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        pdfViewProgressBar.setVisibility(View.GONE);
                        File pdfFile = response.getBody();
                        try {
                            pdfView.fromFile(pdfFile)
                                    .defaultPage(1)
                                    .enableAnnotationRendering(true)
                                    .onLoad(Question_PDFActivity.this)
                                    .scrollHandle(new DefaultScrollHandle(Question_PDFActivity.this))
                                    .spacing(10) // in dp
                                    .onPageError(Question_PDFActivity.this)
                                    .load();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        pdfViewProgressBar.setVisibility(View.GONE);
                        Toast.makeText(Question_PDFActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
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
    @Override
    public void loadComplete(int nbPages) {
        pdfViewProgressBar.setVisibility(View.GONE);
//        Toast.makeText(PDFActivity.this, String.valueOf(nbPages), Toast.LENGTH_LONG).show();
    }
    @Override
    public void onPageError(int page, Throwable t) {
        pdfViewProgressBar.setVisibility(View.GONE);
        Toast.makeText(Question_PDFActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
    }
    /**
     * Showing Dialog
     * */

//    @Override
//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//            case progress_bar_type: // we set this to 0
//                pDialog = new ProgressDialog(this);
//                pDialog.setMessage("Downloading file. Please wait...");
//                pDialog.setIndeterminate(false);
//                pDialog.setMax(100);
//                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                pDialog.setCancelable(true);
//                pDialog.show();
//                return pDialog;
//            default:
//                return null;
//        }
//    }
//
//    /**
//     * Background Async Task to download file
//     * */
//    class DownloadFileFromURL extends AsyncTask<String, String, String> {
//
//        /**
//         * Before starting background thread Show Progress Bar Dialog
//         * */
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            showDialog(progress_bar_type);
//        }
//
//        /**
//         * Downloading file in background thread
//         * */
//        @Override
//        protected String doInBackground(String... f_url) {
//            int count;
//            try {
//                URL url = new URL(f_url[0]);
//                URLConnection conection = url.openConnection();
//                conection.connect();
//
//                // this will be useful so that you can show a tipical 0-100%
//                // progress bar
//                int lenghtOfFile = conection.getContentLength();
//
//                // download the file
//                InputStream input = new BufferedInputStream(url.openStream(),
//                        8192);
//
//                // Output stream
//                OutputStream output = new FileOutputStream(Environment
//                        .getExternalStorageDirectory().toString()
//                        + "/8Comp2018.pdf");
//
//                byte data[] = new byte[1024];
//
//                long total = 0;
//
//                while ((count = input.read(data)) != -1) {
//                    total += count;
//                    // publishing the progress....
//                    // After this onProgressUpdate will be called
//                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
//
//                    // writing data to file
//                    output.write(data, 0, count);
//                }
//
//                // flushing output
//                output.flush();
//
//                // closing streams
//                output.close();
//                input.close();
//
//            } catch (Exception e) {
//                Log.e("Error: ", e.getMessage());
//            }
//
//            return null;
//        }
//
//        /**
//         * Updating progress bar
//         * */
//        protected void onProgressUpdate(String... progress) {
//            // setting progress percentage
//            pDialog.setProgress(Integer.parseInt(progress[0]));
//        }
//
//        /**
//         * After completing background task Dismiss the progress dialog
//         * **/
//        @Override
//        protected void onPostExecute(String file_url) {
//            // dismiss the dialog after the file was downloaded
//            dismissDialog(progress_bar_type);
//
//        }
//
//    }
}

