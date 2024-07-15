package br.com.syonet.newsletters.resources.exceptions;

import br.com.syonet.newsletters.services.exceptions.EmailJaExistenteException;
import br.com.syonet.newsletters.services.exceptions.ListaVaziaException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EmailJaExistenteException.class)
    public ResponseEntity<StandardError>emailExistente(EmailJaExistenteException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(StandardError
                        .builder()
                        .timestamp(Instant.now())
                        .error(HttpStatus.CONFLICT.name())
                        .message(List.of(e.getMessage())
                        )
                        .path(request.getRequestURI())
                        .build()
                );
    }

    public ResponseEntity<StandardError>ListaVazia(ListaVaziaException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(StandardError
                        .builder()
                        .timestamp(Instant.now())
                        .error(HttpStatus.CONFLICT.name())
                        .message(List.of(e.getMessage())
                        )
                        .path(request.getRequestURI())
                        .build()
                );
    }
}
