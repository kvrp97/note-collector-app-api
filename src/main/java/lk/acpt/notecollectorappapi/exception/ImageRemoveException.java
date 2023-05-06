package lk.acpt.notecollectorappapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ImageRemoveException extends IOException {
    public ImageRemoveException(String message){
        super(message);
    }
}
