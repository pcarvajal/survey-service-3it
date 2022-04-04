package cl.threeit.api.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ErrorHandler {

    private final int statusCode = -1;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInformation> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder errorMessage = new StringBuilder();
        fieldErrors.forEach(f -> errorMessage.append(f.getField() + " " + f.getDefaultMessage() +  " "));

        ErrorInformation errorInformation = new ErrorInformation(statusCode, errorMessage.toString(), request.getRequestURI());
        return new ResponseEntity<>(errorInformation, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorInformation> entityNotFoundException(HttpServletRequest request, EntityNotFoundException e) {

        ErrorInformation errorInformation = new ErrorInformation(statusCode, e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorInformation, HttpStatus.NOT_FOUND);
    }

}
