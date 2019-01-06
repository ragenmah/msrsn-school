package com.example.ragenmah99.msrsnproject;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class MyIntro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("NOTES",
                "Engineering related notes are provded.",
                R.drawable.notebooks,
                Color.parseColor("#002171")));//#51e2b7

        addSlide(AppIntroFragment.newInstance("BOOKS",
                "Engineering related books are provided.",
                R.drawable.bookss,
                Color.parseColor("#004c40")));//#00bcd4

        addSlide(AppIntroFragment.newInstance("CONTACT US",
                "You can contact us.",
                R.drawable.contact,
                Color.parseColor("#560027")));//#8c50e3

        addSlide(AppIntroFragment.newInstance("OLD QUESTIONS",
                "Old questions are provided.",
                R.drawable.oldquestionpng,
                Color.parseColor("#bf360c")));//#4fd7ff


        addSlide(AppIntroFragment.newInstance("SYLLABUS",
                "Engineering related syllabus are provided.",
                R.drawable.syllabuss,
                Color.parseColor("#00bcd4")));//#00bcd4



        showStatusBar(false);
        setBarColor(Color.parseColor("#7f0000"));//#333639
        //setSeparatorColor(Color.parseColor("#2196F3"));
    }

    @Override
    public void onDonePressed()
    {
        startActivity(new Intent(this,Login.class));
        finish();

    }

    @Override
    public void onSkipPressed() {
        finish();
    }
}
