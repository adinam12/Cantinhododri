package br.dev.adinamjunior.mobile.cantinhosaudaveldadri.ui.alimento;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.dev.adinamjunior.mobile.cantinhosaudaveldadri.R;
import br.dev.adinamjunior.mobile.cantinhosaudaveldadri.model.Pedido;

public class CadAlimentoFragment extends Fragment implements View.OnClickListener{
    private View view;
    private EditText etQtd;
    private Spinner spCodMarmita;
    private CalendarView cvData;
    private Button btSalvar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar()
                .setDisplayShowCustomEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_cad_alimento, container, false);

        this.etQtd = (EditText) view.findViewById(R.id.etQuantidade);
        this.spCodMarmita = (Spinner) view.findViewById(R.id.spMenu);
        this.cvData = (CalendarView) view.findViewById(R.id.cvDataPedido);
        this.btSalvar = (Button) view.findViewById(R.id.btSalvar);
        this.btSalvar.setOnClickListener(this);

        return this.view;
    }

    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.btSalvar:
                Pedido u = new Pedido();

                u.setCodMarmita( this.spCodMarmita.getSelectedItemPosition());
                u.setQuantidade(Integer.valueOf(this.etQtd.getText().toString()));
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataSelecionada = sdf.format(new
                        Date(cvData.getDate()));
                u.setDatadopedido(dataSelecionada);

                //mensagem de sucesso
                Context context = view.getContext();
                CharSequence text = "salvo com sucesso!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText
                        (context, text, duration);
                toast.show();
                break;
        }

    }
    }