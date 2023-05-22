package lk.acpt.notecollectorappapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "note_images")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteImage {
    @Id
    @Column(name = "note_image_id", length = 25)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer noteImageId;

    @Column(name = "image_name")
    private String imageName;

}
