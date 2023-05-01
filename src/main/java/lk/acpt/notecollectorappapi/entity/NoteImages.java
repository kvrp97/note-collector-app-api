package lk.acpt.notecollectorappapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "note_image")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteImages {
    @Id
    @Column(name = "note_image_id", length = 25)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer noteImageId;

    private String imageName;

    @ManyToOne
    @JoinColumn(name="note_id", nullable=false)
    private Note note;
}
