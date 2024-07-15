package br.com.syonet.newsletters.services.exceptions;

public class ListaVaziaException extends RuntimeException{
    public ListaVaziaException(String msg){
        super(msg);
    }
}
