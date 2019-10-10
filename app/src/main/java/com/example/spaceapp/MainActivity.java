package com.example.spaceapp;

import android.os.Bundle;

import com.example.spaceapp.spaceitems.Empire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.spaceapp.spaceitems.Planet;
import com.example.spaceapp.spaceitems.Resource;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Empire empire = new Empire("Awesome Empire");

        Map<String, Resource> resources = empire.getResources();
        Resource empireWood = resources.get("Wood");
        Resource empireStone = resources.get("Stone");
        Resource empireWater = resources.get("Water");

        final Planet Earth = new Planet("Earth");
        final Planet Mars = new Planet("Mars");
        final Planet Neptune = new Planet("Neptune");

        LayoutInflater inflater = getLayoutInflater();
        View planetView = inflater.inflate(R.layout.planet_layout, null);
        View empireView = inflater.inflate(R.layout.empire_layout, null);

        LinearLayout l1 = findViewById(R.id.planetInfo);
        l1.addView(planetView);
        LinearLayout l2 = findViewById(R.id.empireInfo);
        l2.addView(empireView);

        TextView empireName = findViewById(R.id.empireText);
        empireName.setText(empire.getName());

        TextView empireWoodText = findViewById(R.id.empireWoodText);
        empireWoodText.setText(Integer.toString(empireWood.getAmount()));
        TextView empireStoneText = findViewById(R.id.empireStoneText);
        empireStoneText.setText(Integer.toString(empireStone.getAmount()));
        TextView empireWaterText = findViewById(R.id.empireWaterText);
        empireWaterText.setText(Integer.toString(empireWater.getAmount()));

        ImageView earthView = findViewById(R.id.imageEarth);
        earthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(Earth.getName());
            }
        });
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
