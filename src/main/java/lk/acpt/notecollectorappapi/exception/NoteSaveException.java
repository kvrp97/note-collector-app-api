package lk.acpt.notecollectorappapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class NoteSaveException extends IOException {
    public NoteSaveException(String message){
        super(message);
    }
}
