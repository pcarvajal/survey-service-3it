package cl.threeit.api.entity;

import cl.threeit.api.entity.generics.GenericResponse;

import java.util.List;

public class GetAllGenresResponse extends GenericResponse {
    private List<GenreEntity> genres;

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }
}
