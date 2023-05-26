package lk.acpt.notecollectorappapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @Column(name = "user_id", length = 25)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(name = "first_name", length = 150, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 150, nullable = false)
    private String lastName;

    @Column(name = "email", length = 150, nullable = false)
    private String emailAddress;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<Note> noteList;
}
