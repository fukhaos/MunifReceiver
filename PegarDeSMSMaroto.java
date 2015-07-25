package br.com.viniciusoliveira.munifreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.net.URLEncoder;

import br.com.viniciusoliveira.munifreceiver.models.MensagemSms;

/**
 * Created by user on 25/07/15.
 */
public class PegarDeSMSMaroto extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(final Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        final Context contextLocal = context;

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Toast.makeText(context, "senderNum: " + senderNum + ", message: " + message, Toast.LENGTH_SHORT).show();

                    String texto = String.format("http://syswebhost.net/sms/getJson/getJson.php?numero=%s&texto=%s", senderNum, URLEncoder.encode(message));

                    Log.i("URL", texto);

                    //salvando local
                    try {
                        MensagemSms nova = new MensagemSms(senderNum, message);
                        nova.save();
                    } catch (Exception e) {

                    }


                    //salvando online
                    Ion.with(context)
                            .load(texto)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    if (e == null) {
                                        Toast.makeText(contextLocal, "Cadastro online", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(contextLocal, "Cadastro online ERROR", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }
            }

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);

        }
    }
}
