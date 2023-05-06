package lk.acpt.notecollectorappapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class NoteDeleteException extends RuntimeException{
    public NoteDeleteException(String message){
        super(message);
    }
}
