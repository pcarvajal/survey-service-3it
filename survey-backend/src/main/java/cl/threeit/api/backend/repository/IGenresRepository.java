package cl.threeit.api.backend.repository;

import cl.threeit.api.backend.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IGenresRepository extends JpaRepository<Genre, Long> {

    Genre findByCode(String code);

}
