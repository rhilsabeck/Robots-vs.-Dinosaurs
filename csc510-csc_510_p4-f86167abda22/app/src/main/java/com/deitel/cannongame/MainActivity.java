// CannonGame.java
// MainActivity displays the JetGameFragment
package com.deitel.cannongame;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import edu.noctrl.craig.generic.World;

public class MainActivity extends FragmentActivity implements
        AboutFragment.OnFragmentInteractionListener, HighScoreFragment.OnFragmentInteractionListener {


    //private SensorManagerSimulator mSensorManager;
    //private Sensor mSensor;
    // called when the app first launches
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call super's onCreate method
        setContentView(R.layout.activity_main); // inflate the layout

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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

        switch(item.getItemId())
        {
            case R.id.stage_1:
                World.stage = 1;
                JetGameView.nextStageStatic();
                return true;
            case R.id.stage_2:
                World.stage = 2;
                JetGameView.nextStageStatic();
                return true;
            case R.id.stage_3:
                World.stage = 3;
                JetGameView.nextStageStatic();
                return true;
            case R.id.high_scores:
                HighScoreFragment scoreFrag = new HighScoreFragment();
                FragmentManager fMan = getSupportFragmentManager();
                scoreFrag.show(fMan,"sfrag");
                return true;
            case R.id.about:
                AboutFragment aboutFrag = new AboutFragment();
                FragmentManager fMan1 = getSupportFragmentManager();
                aboutFrag.show(fMan1,"afrag");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    protected void onStop() {
        //mSensorManager.unregisterListener(this);
        super.onStop();
    }

 /*   @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }*/


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
} // end class MainActivity

/*********************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and * Pearson Education, *
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this   *
 * book have used their * best efforts in preparing the book. These efforts      *
 * include the * development, research, and testing of the theories and programs *
 * * to determine their effectiveness. The authors and publisher make * no       *
 * warranty of any kind, expressed or implied, with regard to these * programs   *
 * or to the documentation contained in these books. The authors * and publisher *
 * shall not be liable in any event for incidental or * consequential damages in *
 * connection with, or arising out of, the * furnishing, performance, or use of  *
 * these programs.                                                               *
 *********************************************************************************/
