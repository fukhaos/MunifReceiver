package br.com.viniciusoliveira.munifreceiver;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.viniciusoliveira.munifreceiver.models.MensagemSms;


public class RecebidoActivity extends ActionBarActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recebido);

        lista = (ListView) findViewById(R.id.lista);


        List<MensagemSms> msgs = MensagemSms.listAll(MensagemSms.class);


        ArrayList<String> opcoes = new ArrayList<String>();


        for (MensagemSms msg : msgs){
            opcoes.add(msg.getNumero() + " - " + msg.getTexto());
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);
        lista.setAdapter(adaptador);


    }



}
