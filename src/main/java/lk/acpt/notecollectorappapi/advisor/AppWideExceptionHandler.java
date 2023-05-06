package lk.acpt.notecollectorappapi.advisor;

import lk.acpt.notecollectorappapi.exception.*;
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

    @ExceptionHandler(NoteSaveException.class)
    public ResponseEntity<StandardResponse> handleFileUploadException(NoteSaveException e){
        return new ResponseEntity<>(
                new StandardResponse(501, e.getMessage(), false),
                HttpStatus.NOT_IMPLEMENTED
        );
    }

    @ExceptionHandler(NoteUpdateException.class)
    public ResponseEntity<StandardResponse> handleNoteUpdateException(NoteUpdateException e){
        return new ResponseEntity<>(
                new StandardResponse(501, e.getMessage(), false),
                HttpStatus.NOT_IMPLEMENTED
        );
    }

    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<StandardResponse> handleImageUploadException(ImageUploadException e){
        return new ResponseEntity<>(
                new StandardResponse(501, e.getMessage(), false),
                HttpStatus.NOT_IMPLEMENTED
        );
    }

    @ExceptionHandler(ImageRemoveException.class)
    public ResponseEntity<StandardResponse> handleImageRemoveException(ImageRemoveException e){
        return new ResponseEntity<>(
                new StandardResponse(417, e.getMessage(), false),
                HttpStatus.EXPECTATION_FAILED
        );
    }

    @ExceptionHandler(NoteDeleteException.class)
    public ResponseEntity<StandardResponse> handleNoteDeleteException(NoteDeleteException e){
        return new ResponseEntity<>(
                new StandardResponse(417, e.getMessage(), false),
                HttpStatus.EXPECTATION_FAILED
        );
    }
}
