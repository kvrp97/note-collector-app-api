package lk.acpt.notecollectorappapi.repo;

import lk.acpt.notecollectorappapi.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface NoteRepo extends JpaRepository<Note,Integer> {
}
