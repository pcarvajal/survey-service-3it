package cl.threeit.api;


import cl.threeit.api.backend.domain.Genre;
import cl.threeit.api.backend.repository.IGenresRepository;
import cl.threeit.api.business.service.GenreService;
import cl.threeit.api.business.utils.ResponseUtils;
import cl.threeit.api.entity.CreateGenreResponse;
import cl.threeit.api.entity.GetAllGenresResponse;
import cl.threeit.api.util.GenreServiceTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
public class GenreServiceTest {

    @Mock
    private IGenresRepository genresRepository;

    @InjectMocks
    private GenreService genreService;


    @DisplayName("Should throw InvalidParameterException when genre code exist")
    @Test
    void shouldThrowInvalidParameterExceptionWhenGenreCodeExist(){

        Mockito.when(genresRepository.findByCode(ArgumentMatchers.anyString())).thenReturn(GenreServiceTestUtil.getGenre());

        InvalidParameterException exception = Assertions.assertThrows(InvalidParameterException.class,
                () -> genreService.create(GenreServiceTestUtil.genreRequest()));

        Assertions.assertEquals("Code already exist", exception.getMessage());
    }

    @DisplayName("Should return valid response when genre code request send")
    @Test
    void shouldReturnValidResponseWhenGenreCodeRequestSend(){
        Mockito.when(genresRepository.findByCode(ArgumentMatchers.anyString())).thenReturn(null);
        Mockito.when(genresRepository.save(ArgumentMatchers.any())).thenReturn(GenreServiceTestUtil.getGenre());

        CreateGenreResponse response = genreService.create(GenreServiceTestUtil.genreRequest());

        Assertions.assertEquals(response.getCode(), ResponseUtils.RESPONSE_CODE_OK);
        Assertions.assertEquals(CreateGenreResponse.class, response.getClass());
    }

    @DisplayName("Should return valid response when genre code request send")
    @Test
    void shouldReturnEntityNotFoundExceptionWhenNotFindGenres(){
        Mockito.when(genresRepository.findAll()).thenReturn(new ArrayList<Genre>());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                genreService.getAll() );

        Assertions.assertEquals("Genres not found", exception.getMessage());
    }
    @DisplayName("Should return valid response where request all genres")
    @Test
    void shouldReturnValidResponseWhenRequestAllGenres(){
        Mockito.when(genresRepository.findAll()).thenReturn(GenreServiceTestUtil.getGenreList());

        GetAllGenresResponse response = genreService.getAll();

        Assertions.assertEquals(response.getCode(), ResponseUtils.RESPONSE_CODE_OK);
        Assertions.assertEquals(GetAllGenresResponse.class, response.getClass());
        Assertions.assertEquals(response.getGenres().size(), 2);

    }
}
