package lk.acpt.notecollectorappapi.repo;

import lk.acpt.notecollectorappapi.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface NoteRepo extends JpaRepository<Note,Integer> {
    List<Note> findAllByOrderByDateTimeDesc();

    @Query(value = "select n from Note n where n.title like %?1% or n.description like %?1% order by n.dateTime desc")
    List<Note> searchNotes(String searchKeyword);

    Note getNoteByNoteId(Integer noteId);
}
