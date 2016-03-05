package com.soft.okj.volleygoogle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by okj on 05/12/2015.
 */



public class listAdapter extends ArrayAdapter<locais> {

    private ArrayList<locais> lista;
    private Context c;
    int posicao;
    ImageButton btnVerMapa;


    public listAdapter(Context context, ArrayList<locais> lista) {
        super(context, 0, lista);
        this.c = context;
        this.lista = lista;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final locais localPosicao = this.lista.get(position);
        convertView = LayoutInflater.from(this.c).inflate(R.layout.listviewmodelo1, null);

        TextView txtIdlist = (TextView) convertView.findViewById(R.id.tvlistaEndereco);
        txtIdlist.setText("Endereço: " + String.valueOf(localPosicao.getNomeLocal()));

        TextView txtNomeList = (TextView) convertView.findViewById(R.id.tvlistaLatitude);
        txtNomeList.setText("Latitude: " + localPosicao.getLatitudeLocal());

        TextView txtValorList = (TextView) convertView.findViewById(R.id.tvlistaLongitude);
        txtValorList.setText("Longitude: " + localPosicao.getLongitudeLocal().toString());

        btnVerMapa = ((ImageButton)convertView.findViewById(R.id.btnLitaVerMapa));
        btnVerMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putDouble("latitude", localPosicao.getLatitudeLocal());
                b.putDouble("longitude", localPosicao.getLongitudeLocal());
                b.putString("nomeLocal", localPosicao.getNomeLocal());

                Intent intent = new Intent(c, mapa.class);
                intent.putExtras(b).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);


            }
        });



        return convertView;
    }




}
