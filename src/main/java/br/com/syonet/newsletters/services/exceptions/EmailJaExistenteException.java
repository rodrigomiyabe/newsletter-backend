package br.com.syonet.newsletters.services.exceptions;

public class EmailJaExistenteException extends RuntimeException{
    public EmailJaExistenteException(String msg) {
        super(msg);
    }
}
