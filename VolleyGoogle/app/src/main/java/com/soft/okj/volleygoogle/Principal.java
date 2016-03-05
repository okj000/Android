package com.soft.okj.volleygoogle;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    Context c = this;
    locais locais;
    ArrayList lista;
    ListView lv;
    //final ProgressDialog progresso = new ProgressDialog(c);
    private String url;
    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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

    public void clicouCapturar(View v){




        ///////recebendo endereço da tela do usuário e gerando a url /////////////////////
        String endereco= ((EditText)findViewById(R.id.edxPesquisa)).getText().toString();
        String query = null;

        try {
            query = URLEncoder.encode(endereco, "utf-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url= "https://maps.googleapis.com/maps/api/geocode/json?address=" + query;
        ////////////////////////////////////////////////////////////////////////////
        rq = Volley.newRequestQueue(Principal.this);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,"",

                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        /*coisas
                        progresso.setTitle("Por favor espere!");
                        progresso.setCancelable(false);
                        progresso.show();

                        progresso.hide();
                        */
                        try {
                            StringBuilder builder= new StringBuilder();
                            JSONArray result= response.getJSONArray("results");
                            lista = new ArrayList<locais>();
                            for (int i = 0; i < result.length(); i++) {

                                JSONObject endereco = result.getJSONObject(i);

                                //////recebendo formatted address do Json
                                String formatedAddress = endereco.getString("formatted_address");

                                //////recebendo latitude do Json
                                double latDouble = endereco.getJSONObject("geometry").getJSONObject("location")
                                        .getDouble("lat");

                                //////recebendo longitude do Json
                                double lngDouble = endereco.getJSONObject("geometry").getJSONObject("location")
                                        .getDouble("lng");

                                locais = new locais(formatedAddress, latDouble, lngDouble);
                                lista.add(locais);
                                //adapta a lista para a listview da tela
                                lv = (ListView) findViewById(R.id.lvExibe);
                                listAdapter adaptador = new listAdapter(c,lista);
                                lv.setAdapter(adaptador);
                                adaptador.notifyDataSetChanged();


                            }

                        } catch (Exception ex) {
                            Toast.makeText(Principal.this, ex.toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                //coisas
                Toast.makeText(Principal.this,"B.O: "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(jor);


    }
}
