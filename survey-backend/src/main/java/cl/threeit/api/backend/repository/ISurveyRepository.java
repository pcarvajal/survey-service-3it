package cl.threeit.api.backend.repository;

import cl.threeit.api.backend.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ISurveyRepository extends JpaRepository<Survey, Long> {
    Survey findByEmail(String email);
    @Query(value = "SELECT c.genre.description, COUNT(c.genre.id) FROM Survey AS c GROUP BY c.genre.description")
    List<Object[]> getResume();
}

