package br.com.viniciusoliveira.munifreceiver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private EditText numero;
    private EditText mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = (EditText) findViewById(R.id.numero);
        mensagem = (EditText) findViewById(R.id.mensagem);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_recebido) {
            Intent intencao = new Intent(this, RecebidoActivity.class);
            startActivity(intencao);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void enviarSMS(View view) {

        String to = numero.getText().toString();
        String message = mensagem.getText().toString();

        SmsManager.getDefault()
                .sendTextMessage(to, null,
                        message,
                        null, null);

        Toast.makeText(this, "Mensagem enviada com sucesso", Toast.LENGTH_SHORT).show();

        numero.setText("");
        mensagem.setText("");

    }
}
