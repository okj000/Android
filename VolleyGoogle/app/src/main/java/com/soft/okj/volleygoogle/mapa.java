package com.soft.okj.volleygoogle;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.LatLng;

public class mapa extends FragmentActivity implements OnStreetViewPanoramaReadyCallback {

    Double lat;
    Double lng;
    String nomeLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        Bundle b = getIntent().getExtras();
        lat = b.getDouble("latitude", 0);
        lng = b.getDouble("longitude", 0);
        nomeLocal = b.getString("nomeLocal","");


        try {
            StreetViewPanoramaFragment streetViewPanoramaFragment =
                    (StreetViewPanoramaFragment) getFragmentManager()
                            .findFragmentById(R.id.streetviewpanorama);
            streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

            TextView tvMapaNomeLocal = (TextView) findViewById(R.id.tvMapaNomeLocal);
            tvMapaNomeLocal.setText("Você está vendo: "+nomeLocal);
        }catch(Exception e){ Toast.makeText(this,"vvvv: "+e.toString(),Toast.LENGTH_LONG).show();}
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mapa, menu);
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
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
        try {
            panorama.setPosition(new LatLng(lat, lng));

        }catch(Exception e){
            Toast.makeText(this,"erorororw: "+e.toString(),Toast.LENGTH_LONG).show();}
    }
}
