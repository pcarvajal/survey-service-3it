package cl.threeit.api.util;

import cl.threeit.api.backend.domain.Genre;
import cl.threeit.api.entity.CreateGenreRequest;

import java.util.ArrayList;
import java.util.List;

public class GenreServiceTestUtil {

    public static Genre getGenre (){
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setCode("ROCK");
        genre.setDescription("Rock");
        return genre;
    }

    public static List<Genre> getGenreList (){
        List<Genre> list = new ArrayList<>();

        Genre rock = new Genre();
        rock.setCode("ROCK");
        rock.setDescription("Rock");
        rock.setId(1L);
        list.add(rock);

        Genre pop = new Genre();
        pop.setCode("POP");
        pop.setDescription("Pop");
        pop.setId(2L);
        list.add(pop);

        return list;
    }

    public static CreateGenreRequest genreRequest () {
        CreateGenreRequest request = new CreateGenreRequest();
        request.setCode("ROCK");
        request.setDescription("Rock");
        return request;
    }

}
