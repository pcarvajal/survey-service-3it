package cl.threeit.api.controllers;

import cl.threeit.api.business.service.GenreService;
import cl.threeit.api.entity.CreateGenreRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;


@Controller
@Validated
public class GenreController implements IGenreController{

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(genreService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> create(CreateGenreRequest request) {
        return new ResponseEntity<>(genreService.create(request), HttpStatus.CREATED);
    }
}
