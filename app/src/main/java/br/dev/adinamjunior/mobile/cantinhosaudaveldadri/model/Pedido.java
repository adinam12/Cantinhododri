package br.dev.adinamjunior.mobile.cantinhosaudaveldadri.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedido {
    //atributos
    private int id;
    private int codMarmita;
    private int quantidade;
    private String datadopedido;

    public void Pedido (){
        this.setId(0);
        this.setCodMarmita(0);
        this.setQuantidade(0);
        this.setDatadopedido("1900-01-01");
    }

    public void marmitaJS (JSONObject jp){
        try {
            this.setId((int) jp.get("id"));
            this.setCodMarmita((int) jp.get("codMarmita"));
            this.setQuantidade((int) jp.get("quantidade"));
            this.setDatadopedido((String) jp.get("datadopedido"));
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", this.id);
            json.put("codMarmita", this.codMarmita);
            json.put("quantidade", this.quantidade);
            json.put("datadopedido", this.datadopedido);

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

    public String getDatadopedido() {
        return datadopedido;
    }

    public void setDatadopedido(String datadopedido) {
        SimpleDateFormat formato =
                new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date data = (Date) formato.parse(datadopedido);
//se chegar até aqui não deu erro no parser
            this.datadopedido = datadopedido;
        } catch (ParseException e) {
            this.datadopedido = "2023-08-08";
        }

    }
}
