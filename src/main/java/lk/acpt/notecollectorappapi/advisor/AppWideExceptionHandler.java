package lk.acpt.notecollectorappapi.advisor;

import lk.acpt.notecollectorappapi.exception.DuplicateException;
import lk.acpt.notecollectorappapi.exception.NotFoundException;
import lk.acpt.notecollectorappapi.exception.UnauthorizedException;
import lk.acpt.notecollectorappapi.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<>(
                new StandardResponse(404, e.getMessage(), false),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<StandardResponse> handleDuplicateException(DuplicateException e){
        return new ResponseEntity<>(
                new StandardResponse(409,e.getMessage(),false),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<StandardResponse> handleUnauthorizedException(UnauthorizedException e){
        return new ResponseEntity<>(
                new StandardResponse(401, e.getMessage(), false),
                HttpStatus.UNAUTHORIZED
        );
    }
}
