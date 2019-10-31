package com.example.spaceapp;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.spaceapp.spaceitems.Building;
import com.example.spaceapp.spaceitems.Empire;

import androidx.appcompat.app.AlertDialog;
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
import android.widget.Toast;

import com.example.spaceapp.spaceitems.Planet;
import com.example.spaceapp.spaceitems.Resource;
import com.example.spaceapp.spaceitems.Spaceship;
import com.example.spaceapp.spaceitems.types.PlanetTypes;
import com.example.spaceapp.spaceitems.types.ResourceTypes;
import com.example.spaceapp.spaceitems.utils.IdGenerator;

import java.util.Map;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {
    private Empire empire;
    private Map<String, Resource> resources;
    private ProduceAsyncTask produceAsync;
    private Planet selectedPlanet;
    // planet info
    private TextView planetName;
    private TextView woodText;
    private TextView waterText;
    private TextView stoneText;
    private TextView woodBuildingsNum;
    private TextView waterBuildingsNum;
    private TextView stoneBuildingsNum;
    private Button addWoodBuilding;
    private Button addStoneBuilding;
    private Button addWaterBuilding;
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

        IdGenerator planetIdGenerator = new IdGenerator();

        this.empire = new Empire("Awesome Empire");

        this.resources = this.empire.getResources();
        Resource empireWood = this.resources.get(ResourceTypes.WOOD.getValue());
        Resource empireStone = this.resources.get(ResourceTypes.STONE.getValue());
        Resource empireWater = this.resources.get(ResourceTypes.WATER.getValue());

        final Planet Earth = new Planet(PlanetTypes.EARTH.getValue(), planetIdGenerator.giveNextID());
        final Planet Mars = new Planet(PlanetTypes.MARS.getValue(), planetIdGenerator.giveNextID());
        final Planet Neptune = new Planet(PlanetTypes.NEPTUNE.getValue(), planetIdGenerator.giveNextID());
        this.selectedPlanet = Earth;

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
        this.woodBuildingsNum = findViewById(R.id.woodBuildingsNum);
        this.waterBuildingsNum = findViewById(R.id.waterBuildingsNum);
        this.stoneBuildingsNum = findViewById(R.id.stoneBuildingsNum);

        this.addStoneBuilding = findViewById(R.id.stoneButton);
        this.addWaterBuilding = findViewById(R.id.waterButton);
        this.addWoodBuilding = findViewById(R.id.woodButton);

        this.empireWoodText = findViewById(R.id.empireWoodText);
        this.empireStoneText = findViewById(R.id.empireStoneText);
        this.empireWaterText = findViewById(R.id.empireWaterText);

        this.empireWoodText.setText(Integer.toString(empireWood.getAmount()));
        this.empireStoneText.setText(Integer.toString(empireStone.getAmount()));
        this.empireWaterText.setText(Integer.toString(empireWater.getAmount()));

        this.earthView = findViewById(R.id.imageEarth);
        this.marsView = findViewById(R.id.imageMars);
        this.neptuneView = findViewById(R.id.imageNeptune);

        hidePlanetInfo();

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

        this.addStoneBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { alertBuildingDialog(ResourceTypes.STONE.getValue());
            }
        });
        this.addWaterBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { alertBuildingDialog(ResourceTypes.WATER.getValue());
            }
        });
        this.addWoodBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertBuildingDialog(ResourceTypes.WOOD.getValue());
            }
        });

        this.produceAsync = new ProduceAsyncTask();
        this.produceAsync.execute();
    }

    public void setPlanetInfo(final Planet planet){
        this.planetName.setText(planet.getName());
        this.selectedPlanet = planet;
        this.woodText.setText(Integer.toString(planet.getResources().get(ResourceTypes.WOOD.getValue()).getAmount()));
        this.stoneText.setText(Integer.toString(planet.getResources().get(ResourceTypes.STONE.getValue()).getAmount()));
        this.waterText.setText(Integer.toString(planet.getResources().get(ResourceTypes.WATER.getValue()).getAmount()));

        if (!planet.isCaptured() && this.spaceship.isReady()){
            this.captureButton.setVisibility(VISIBLE);
            this.captureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertCaptureDialog();
                }
            });
        } else {
            this.captureButton.setVisibility(GONE);
        }

        if (!planet.isCaptured()){
            this.hidePlanetInfo();
        } else {
            this.showPlanetInfo();
        }
    }

    private class ProduceAsyncTask extends AsyncTask<String, Integer, Integer> {
        @Override
        protected Integer doInBackground(String... parameter) {
            while(true) {
                int myProgress = 1;
                // for planet in planets
                for (Planet planet : empire.getPlanets().values()){
                    // for building in planet
                    for (Building b : planet.getBuildings().values()){
                        // produce
                        b.tick();
                        // add to Empire and clear storage
                        Resource tempRes = b.getTempStorage();
                        empire.getResources().get(tempRes.getType()).increase(tempRes.getAmount());
                        tempRes.decrease(tempRes.getAmount());
                    }
                }
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                publishProgress(myProgress);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            empireStoneText.setText(String.valueOf(empire.getResources().get(ResourceTypes.STONE.getValue()).getAmount()));
            empireWaterText.setText(String.valueOf(empire.getResources().get(ResourceTypes.WATER.getValue()).getAmount()));
            empireWoodText.setText(String.valueOf(empire.getResources().get(ResourceTypes.WOOD.getValue()).getAmount()));

            waterText.setText(String.valueOf(selectedPlanet.getResources().get(ResourceTypes.WATER.getValue()).getAmount()));
            woodText.setText(String.valueOf(selectedPlanet.getResources().get(ResourceTypes.WOOD.getValue()).getAmount()));
            stoneText.setText(String.valueOf(selectedPlanet.getResources().get(ResourceTypes.STONE.getValue()).getAmount()));

            waterBuildingsNum.setText(String.valueOf(selectedPlanet.getBuildings().get(ResourceTypes.WATER.getValue()).getLevel()));
            woodBuildingsNum.setText(String.valueOf(selectedPlanet.getBuildings().get(ResourceTypes.WOOD.getValue()).getLevel()));
            stoneBuildingsNum.setText(String.valueOf(selectedPlanet.getBuildings().get(ResourceTypes.STONE.getValue()).getLevel()));
        }
    }

    private void capturePlanet(Planet planet){
        spaceship.capture(planet);

        captureButton.setVisibility(GONE);

        Thread spaceshipThread = new Thread() {
            public void run() {
                while(!spaceship.isReady()) {
                    int myProgress = spaceship.getTimeLeft();
                    spaceship.tick();
                    try {
                        Thread.sleep(1000);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    final String text;
                    if (spaceship.getTimeLeft() != 0) {
                        text = Integer.toString(myProgress) +
                                " (" + spaceship.getTargetPlanet().getName() + ")";
                    } else{
                        text ="Ready";
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            spaceshipText.setText(text);
                        }
                    });
                }
                empire.addPlanet(spaceship.getTargetPlanet());
            }
        };

        spaceshipThread.start();
    }

    private void alertCaptureDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Capture?")
                .setMessage("You wanna fly to " + selectedPlanet.getName() +
                        " for " + selectedPlanet.getDistance() + " seconds. Continue?")
                .setCancelable(false)
                .setPositiveButton("Yos",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                System.out.println("1");
                                capturePlanet(selectedPlanet);
                            }
                        })
                .setNegativeButton("No, go back",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                System.out.println("0");
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void alertBuildingDialog(final String type){
        Building selectedBuilding = this.selectedPlanet.getBuildings().get(type);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Upgrade?")
                .setMessage(selectedBuilding.getType() + " building upgrade will cost " +
                        selectedBuilding.getCost() + " of " +
                        selectedBuilding.getType().toLowerCase() + ". Continue?")
                .setCancelable(false)
                .setPositiveButton("Yep",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                System.out.println("1");
                                upgradeBuilding(type);
                            }
                        })
                .setNegativeButton("Nope",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                System.out.println("0");
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void upgradeBuilding(String type){
        Building selectedBuilding = this.selectedPlanet.getBuildings().get(type);
        int cost = selectedBuilding.getCost();
        Resource res = empire.getResources().get(type);
        if (res.getAmount() >= cost) {
            res.decrease(cost);
            selectedBuilding.levelUp();
            if (selectedBuilding.getLevel() == 1) selectedBuilding.produce();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Недостаточно ресурсов!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void hidePlanetInfo(){
        this.addStoneBuilding.setVisibility(GONE);
        this.addWaterBuilding.setVisibility(GONE);
        this.addWoodBuilding.setVisibility(GONE);

        this.stoneBuildingsNum.setVisibility(GONE);
        this.waterBuildingsNum.setVisibility(GONE);
        this.woodBuildingsNum.setVisibility(GONE);
    }

    private void showPlanetInfo(){
        this.addStoneBuilding.setVisibility(VISIBLE);
        this.addWaterBuilding.setVisibility(VISIBLE);
        this.addWoodBuilding.setVisibility(VISIBLE);

        this.stoneBuildingsNum.setVisibility(VISIBLE);
        this.waterBuildingsNum.setVisibility(VISIBLE);
        this.woodBuildingsNum.setVisibility(VISIBLE);
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
