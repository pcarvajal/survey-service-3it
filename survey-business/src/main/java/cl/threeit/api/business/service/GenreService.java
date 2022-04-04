package cl.threeit.api.business.service;

import cl.threeit.api.backend.domain.Genre;
import cl.threeit.api.backend.repository.IGenresRepository;
import cl.threeit.api.business.utils.ResponseUtils;
import cl.threeit.api.entity.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {
    final IGenresRepository genresRepository;

    public GenreService(IGenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public CreateGenreResponse create(CreateGenreRequest request){

        Genre genreCode = genresRepository.findByCode(request.getCode());

        if(genreCode != null){
            throw new InvalidParameterException("Code already exist");
        }

        Genre newGenre = new Genre();
        newGenre.setCode(request.getCode());
        newGenre.setDescription(request.getDescription());

        Genre savedGenre = genresRepository.save(newGenre);

        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setDescription(savedGenre.getDescription());
        genreEntity.setId(savedGenre.getId().intValue());
        genreEntity.setCode(savedGenre.getCode());

        CreateGenreResponse response = new CreateGenreResponse();
        response.setCode(ResponseUtils.RESPONSE_CODE_OK);
        response.setDescription(ResponseUtils.RESPONSE_MESSAGE_OK);
        response.setGenre(genreEntity);

        return response;
    }

    public GetAllGenresResponse getAll(){
        List<Genre> genres = genresRepository.findAll();

        if(genres.size() == 0){
            throw new EntityNotFoundException("Genres not found");
        }

        List<GenreEntity> listGenresEntity = new ArrayList<>();

        for(Genre item: genres){
            listGenresEntity.add(mapToEntity(item));
        }

        GetAllGenresResponse response = new GetAllGenresResponse();

        response.setGenres(listGenresEntity);
        response.setCode(0);
        response.setDescription("Success");

        return response;
    }

    private GenreEntity mapToEntity(Genre genre){
        GenreEntity entity = new GenreEntity();
        entity.setCode(genre.getCode());
        entity.setId(genre.getId().intValue());
        entity.setDescription(genre.getDescription());

        return entity;
    }
}
