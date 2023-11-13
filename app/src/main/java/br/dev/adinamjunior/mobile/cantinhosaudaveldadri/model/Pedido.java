package br.dev.adinamjunior.mobile.cantinhosaudaveldadri.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Pedido {
    //atributos
    private int id;
    private int codMarmita;
    private int quantidade;


    public void Pedido (){
        this.setId(0);
        this.setCodMarmita(0);
        this.setQuantidade(0);

    }

    public void marmitaJS (JSONObject jp){
        try {
            this.setId((int) jp.get("id"));
            this.setCodMarmita((int) jp.get("Marmita_idMarmita"));
            this.setQuantidade((int) jp.get("quantidade"));
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", this.id);
            json.put("Marmita_idMarmita", this.codMarmita + 1);
            json.put("Carrinho_idCarrinho", 1);
            json.put("quantidade", this.quantidade);
            Log.d("Json: ", json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCodMarmita (int cm) {
        this.codMarmita = cm;
    }
    public int getCodMarmita () {
        return this.codMarmita;
    }
    public void setQuantidade(int cq) {
        if (cq >= 0 && cq <= 10 )
            this.quantidade = cq;
    }

    public int getQuantidade() {
        return quantidade;
    }


}
