package lk.acpt.notecollectorappapi.repo;

import lk.acpt.notecollectorappapi.entity.NoteImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface NoteImageRepo extends JpaRepository<NoteImage,Integer> {
}
