package com.soft.okj.volleygoogle;

/**
 * Created by okj on 05/12/2015.
 */
public class locais {

    private String nomeLocal;
    private Double latitudeLocal;
    private Double longitudeLocal;


    public locais(String nomeLocal, Double latitudeLocal, Double logitudeLocal){

        this.nomeLocal = nomeLocal;
        this.latitudeLocal = latitudeLocal;
        this.longitudeLocal = logitudeLocal;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public Double getLatitudeLocal() {
        return latitudeLocal;
    }

    public void setLatitudeLocal(Double latitudeLocal) {
        this.latitudeLocal = latitudeLocal;
    }

    public Double getLongitudeLocal() {
        return longitudeLocal;
    }

    public void setLongitudeLocal(Double longitudeLocal) {
        this.longitudeLocal = longitudeLocal;
    }
}
