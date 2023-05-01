package lk.acpt.notecollectorappapi.repo;

import lk.acpt.notecollectorappapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    boolean existsByEmailAddress(String emailAddress);

    User getUserByEmailAddress(String emailAddress);
}
