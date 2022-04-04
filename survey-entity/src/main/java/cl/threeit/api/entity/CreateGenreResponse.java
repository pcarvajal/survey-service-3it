package cl.threeit.api.entity;

import cl.threeit.api.entity.generics.GenericResponse;

public class CreateGenreResponse extends GenericResponse {
    private GenreEntity genre;

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }
}
