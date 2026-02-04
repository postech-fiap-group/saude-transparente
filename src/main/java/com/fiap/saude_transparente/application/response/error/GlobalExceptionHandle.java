package com.fiap.saude_transparente.application.response.error;


import com.fiap.saude_transparente.domain.exceptions.InvalidFieldsException;
import com.fiap.saude_transparente.domain.exceptions.MedicoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {


    @ExceptionHandler(InvalidFieldsException.class)
    public ResponseEntity<ErrorMessage> handleCamposInvalidosException(InvalidFieldsException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(MedicoNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> handleMedicoNaoEncontrado(MedicoNaoEncontradoException exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    // Fallback para qualquer erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception exception) {
        ErrorMessage threatResponse = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatResponse);
    }

}
