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
    List<Note> findAllByUserIdOrderByDateTimeDesc(Integer userId);

    @Query(value = "select * from notes n where n.user_id = ?1 and ( n.note_title like %?2% or n.note_description like %?2% ) order by n.date_time desc", nativeQuery = true)
    List<Note> searchNotes(Integer userId, String searchKeyword);

    Note getNoteByNoteId(Integer noteId);
}
