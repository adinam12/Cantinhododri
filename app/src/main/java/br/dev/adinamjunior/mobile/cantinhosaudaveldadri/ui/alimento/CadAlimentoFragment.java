package br.dev.adinamjunior.mobile.cantinhosaudaveldadri.ui.alimento;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import br.dev.adinamjunior.mobile.cantinhosaudaveldadri.R;
import br.dev.adinamjunior.mobile.cantinhosaudaveldadri.model.Pedido;

public class CadAlimentoFragment extends Fragment implements View.OnClickListener{
    private View view;
    private EditText etQtd;
    private Spinner spCodMarmita;
    private Button btSalvar;

    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectReq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_cad_alimento, container, false);

        this.etQtd = (EditText) view.findViewById(R.id.etQuantidade);
        this.spCodMarmita = (Spinner) view.findViewById(R.id.spMenu);
        this.btSalvar = (Button) view.findViewById(R.id.btSalvar);
        this.btSalvar.setOnClickListener(this);

        //instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());
//inicializando a fila de requests do SO
        this.requestQueue.start();

        return this.view;
    }

    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.btSalvar:
                Pedido u = new Pedido();

                u.setCodMarmita( this.spCodMarmita.getSelectedItemPosition());
                u.setQuantidade(Integer.valueOf(this.etQtd.getText().toString()));


                jsonObjectReq = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://10.0.2.2/cantinhododri/cadPedido.php",
                        u.toJsonObject(), this::onResponse, this::onErrorResponse);
                requestQueue.add(jsonObjectReq);
                break;
        }

        }


    public void onErrorResponse(VolleyError error) {
        Snackbar mensagem = Snackbar.make(view,
                "Ops! " +
                        error.toString(),Snackbar.LENGTH_LONG);
        mensagem.show();
        Log.d("Erro: ", error.toString());
    }


    public void onResponse(Object response) {
        try {
            //instanciando objeto para manejar o JSON que recebemos
            JSONObject jason = new JSONObject(response.toString());
            //context e text são para a mensagem na tela o Toast
            Context context = view.getContext();
            //pegando mensagem que veio do json
            CharSequence mensagem = jason.getString("message");
            //duração da mensagem na tela
            int duration = Toast.LENGTH_SHORT;
            //verificando se salvou sem erro para limpar campos da tela
            if (jason.getBoolean("success")){
                //campos texto
                this.etQtd.setText("");
                //selecionando primiro item dos spinners
                this.spCodMarmita.setSelection(0);
            }
            //mostrando a mensagem que veio do JSON
            Toast toast = Toast.makeText (context, mensagem, duration);
            toast.show();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        //mensagem de sucesso


    }
    }