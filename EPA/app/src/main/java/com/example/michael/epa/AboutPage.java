package com.example.michael.epa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);



        /* ***************************************************
                NAV BUTTONS
        ******************************************************* */
        final Button help = (Button) findViewById(R.id.help);
        help.setText("Help");
        help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AboutPage.this, HelpPage.class);
                startActivity(intent);
                finish();
            }
        });

        final Button main_menu = (Button) findViewById(R.id.main_menu);
        main_menu.setText("Main Menu");
        main_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        final ImageButton logo = (ImageButton) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
