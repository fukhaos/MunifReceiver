package br.com.viniciusoliveira.munifreceiver.models;

import com.orm.SugarRecord;

/**
 * Created by user on 25/07/15.
 */
public class MensagemSms extends SugarRecord<MensagemSms> {
    private String numero;
    private String texto;

    public MensagemSms() {
    }

    public MensagemSms(String numero, String texto) {

        this.numero = numero;
        this.texto = texto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
