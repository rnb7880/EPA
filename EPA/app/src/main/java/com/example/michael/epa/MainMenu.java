package com.example.michael.epa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);



        /* ***************************************************
                NAV BUTTONS
        ******************************************************* */
        final Button find_spot = (Button) findViewById(R.id.find_spot);
        find_spot.setText("Find a Spot");
        find_spot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, SelectionScreen.class);
                startActivity(intent);
            }
        });
        final Button help = (Button) findViewById(R.id.help);
        help.setText("Help");
        help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, HelpPage.class);
                startActivity(intent);
            }
        });

        final Button about = (Button) findViewById(R.id.about);
        about.setText("About");
        about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AboutPage.class);
                startActivity(intent);
            }
        });
    }
}
