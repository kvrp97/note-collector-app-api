package lk.acpt.notecollectorappapi.controller;

import lk.acpt.notecollectorappapi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/note")
@CrossOrigin
public class NoteController {
    @Autowired
    private NoteService noteService;


}
