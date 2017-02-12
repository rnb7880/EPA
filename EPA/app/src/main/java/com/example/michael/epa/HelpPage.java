package com.example.michael.epa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HelpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);





        

        /* ***************************************************
                NAV BUTTONS
        ******************************************************* */
        final Button about = (Button) findViewById(R.id.about);
        about.setText("About");
        about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HelpPage.this, AboutPage.class);
                startActivity(intent);
            }
        });

        final Button main_menu = (Button) findViewById(R.id.main_menu);
        main_menu.setText("Main Menu");
        main_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HelpPage.this, MainMenu.class);
                startActivity(intent);
            }
        });

        final ImageButton logo = (ImageButton) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HelpPage.this, MainMenu.class);
                startActivity(intent);
            }
        });

    }
}
