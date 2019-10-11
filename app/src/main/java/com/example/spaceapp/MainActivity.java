package com.example.spaceapp;

import android.os.Bundle;

import com.example.spaceapp.spaceitems.Empire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.spaceapp.spaceitems.Planet;
import com.example.spaceapp.spaceitems.Resource;
import com.example.spaceapp.spaceitems.Spaceship;

import java.util.Map;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    private Empire empire;
    private Map<String, Resource> resources;
    // planet info
    private TextView planetName;
    private TextView woodText;
    private TextView waterText;
    private TextView stoneText;
    // empire info
    private TextView empireName;
    private TextView empireWoodText;
    private TextView empireStoneText;
    private TextView empireWaterText;
    // planets view
    private ImageView earthView;
    private ImageView marsView;
    private ImageView neptuneView;
    // spaceship info
    private Button captureButton;
    private Spaceship spaceship;
    private TextView spaceshipText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.empire = new Empire("Awesome Empire");

        this.resources = this.empire.getResources();
        Resource empireWood = this.resources.get("Wood");
        Resource empireStone = this.resources.get("Stone");
        Resource empireWater = this.resources.get("Water");

        final Planet Earth = new Planet("Earth");
        final Planet Mars = new Planet("Mars");
        final Planet Neptune = new Planet("Neptune");

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout l1 = findViewById(R.id.planetInfo);
        LinearLayout l2 = findViewById(R.id.empireInfo);

        View planetView = inflater.inflate(R.layout.planet_layout, null);
        View empireView = inflater.inflate(R.layout.empire_layout, null);

        l1.addView(planetView);
        l2.addView(empireView);

        this.spaceship = new Spaceship();
        this.spaceshipText = findViewById(R.id.spaceshipText);

        this.captureButton = findViewById(R.id.captureButton);
        this.captureButton.setVisibility(GONE);

        this.empireName = findViewById(R.id.empireText);
        this.empireName.setText(this.empire.getName());

        this.planetName = findViewById(R.id.planetName);
        this.woodText = findViewById(R.id.woodText);
        this.waterText = findViewById(R.id.waterText);
        this.stoneText = findViewById(R.id.stoneText);

        this.empireWoodText = findViewById(R.id.empireWoodText);
        this.empireStoneText = findViewById(R.id.empireStoneText);
        this.empireWaterText = findViewById(R.id.empireWaterText);

        this.empireWoodText.setText(Integer.toString(empireWood.getAmount()));
        this.empireStoneText.setText(Integer.toString(empireStone.getAmount()));
        this.empireWaterText.setText(Integer.toString(empireWater.getAmount()));

        this.earthView = findViewById(R.id.imageEarth);
        this.marsView = findViewById(R.id.imageMars);
        this.neptuneView = findViewById(R.id.imageNeptune);

        this.earthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlanetInfo(Earth);
            }
        });
        this.marsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlanetInfo(Mars);
            }
        });
        this.neptuneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlanetInfo(Neptune);
            }
        });
    }

    public void setPlanetInfo(final Planet planet){
        this.planetName.setText(planet.getName());
        this.woodText.setText(Integer.toString(planet.getResources().get("Wood").getAmount()));
        this.stoneText.setText(Integer.toString(planet.getResources().get("Stone").getAmount()));
        this.waterText.setText(Integer.toString(planet.getResources().get("Water").getAmount()));

        if (!planet.isCaptured() && this.spaceship.isReady()){
            this.captureButton.setVisibility(VISIBLE);
            this.captureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spaceship.capture(planet);
                    planet.capture();

                    // TODO: add async
                    Thread t = new Thread(new Runnable() { public void run() {
                        while(!spaceship.isReady()){
                            spaceship.tick();
                            try {
                                Thread.sleep(1000);
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                            System.out.println(spaceship.getTimeLeft());
                            MainActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    spaceshipText.setText(Integer.toString(spaceship.getTimeLeft()));
                                }
                            });

                        }
                    }});

                    t.run();
                }
            });
        } else {
            this.captureButton.setVisibility(GONE);
        }
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
