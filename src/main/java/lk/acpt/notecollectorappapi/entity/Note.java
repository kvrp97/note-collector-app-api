package lk.acpt.notecollectorappapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
    @Id
    @Column(name = "note_id", length = 25)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer noteId;

    @Column(name = "note_title", length = 110)
    private String title;

    @Column(name = "note_description", length = 455)
    private String description;

    @Column(name = "date_time", length = 30, nullable = false)
    private String dateTime;

    @OneToMany(mappedBy = "note")
    private Set<NoteImages> noteImages;
}
