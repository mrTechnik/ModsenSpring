package library.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import library.app.domain.Usr;

@RepositoryRestResource
public interface UsrRepo extends JpaRepository<Usr, Integer>{
    @Query(value = "select b.* from usr b where b.login = :login limit 1",
    nativeQuery = true)
    Usr findByLogin(String login);
}
