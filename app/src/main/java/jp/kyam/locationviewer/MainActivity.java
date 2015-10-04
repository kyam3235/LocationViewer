package jp.kyam.locationviewer;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements LocationListener, View.OnClickListener{
    private LocationManager mLocationManager;
    private TextView mTvLocation;
    private Button mBtnStartGps;
    private Button mBtnStopGps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean gpsFlag = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d("GPS Enabled", gpsFlag?"true":"false");

        mTvLocation = (TextView)findViewById(R.id.tv_location);
        mBtnStartGps = (Button)findViewById(R.id.btn_start_gps);
        mBtnStartGps.setOnClickListener(this);
        mBtnStopGps = (Button)findViewById(R.id.btn_stop_gps);
        mBtnStopGps.setOnClickListener(this);
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

    @Override
    public void onPause(){
        mLocationManager.removeUpdates(this);
        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {
        mTvLocation.setText("Lat:" + String.valueOf(location.getLatitude()) + " Lon:" + String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start_gps:
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
                break;
            case R.id.btn_stop_gps:
                mLocationManager.removeUpdates(this);
                break;
            default:
                break;
        }
    }
}
