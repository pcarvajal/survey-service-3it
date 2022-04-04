package cl.threeit.api;

import cl.threeit.api.backend.repository.IGenresRepository;
import cl.threeit.api.backend.repository.ISurveyRepository;
import cl.threeit.api.business.service.SurveyService;
import cl.threeit.api.business.utils.ResponseUtils;
import cl.threeit.api.entity.CreateSurveyResponse;
import cl.threeit.api.entity.GetSurveyResumeResponse;
import cl.threeit.api.entity.SurveyEntity;
import cl.threeit.api.entity.SurveyResumeEntity;
import cl.threeit.api.util.GenreServiceTestUtil;
import cl.threeit.api.util.SurveyServiceTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class SurveyServiceTest {

    @Mock
    private ISurveyRepository iSurveyRepository;

    @Mock
    private IGenresRepository iGenresRepository;

    @InjectMocks
    private SurveyService surveyService;

    @DisplayName("Should throw invalidParameterException when email already exist")
    @Test
    void shouldThrowInvalidParameterExceptionWhenEmailAlreadyExist(){
        Mockito.when(iSurveyRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(SurveyServiceTestUtil.getSurvey());

        InvalidParameterException exception = Assertions.assertThrows(InvalidParameterException.class,
                () -> surveyService.createRecord(SurveyServiceTestUtil.getSurveyRequest()));

        Assertions.assertEquals("Email already exist", exception.getMessage());
    }

    @DisplayName("Should throw ResponseStatusException when genre not exist")
    @Test
    void shouldThrowResponseStatusExceptionWhenGenreNotExist(){
        Mockito.when(iSurveyRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(null);
        Mockito.when(iGenresRepository.findByCode(ArgumentMatchers.anyString())).thenReturn(null);

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                () -> surveyService.createRecord(SurveyServiceTestUtil.getSurveyRequest()));

        Assertions.assertEquals("Genre not exist", exception.getReason());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @DisplayName("Should return valid response when create a record")
    @Test
    void shouldReturnValidResponseWhenCreateARecord(){
        Mockito.when(iSurveyRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(null);
        Mockito.when(iGenresRepository.findByCode(ArgumentMatchers.anyString())).thenReturn(GenreServiceTestUtil.getGenre());
        Mockito.when(iSurveyRepository.save(ArgumentMatchers.any())).thenReturn(SurveyServiceTestUtil.getSurvey());


        CreateSurveyResponse response = surveyService.createRecord(SurveyServiceTestUtil.getSurveyRequest());

        Assertions.assertEquals(response.getCode(), ResponseUtils.RESPONSE_CODE_OK);
    }

    @DisplayName("Should throw response status exception when get resume and no records found")
    @Test
    void shouldThrowResponseStatusExceptionWhenGetResumeAndNoRecordsFound(){
        Mockito.when(iSurveyRepository.getResume()).thenReturn(new ArrayList<>());

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                () -> surveyService.getResume());

        Assertions.assertEquals("Resume not found", exception.getReason());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @DisplayName("Should return valid response when get resume")
    @Test
    void shouldReturnValidResponseWhenGetResume(){
        Mockito.when(iSurveyRepository.getResume()).thenReturn(SurveyServiceTestUtil.getResume());

        GetSurveyResumeResponse response = surveyService.getResume();

        Assertions.assertEquals(ResponseUtils.RESPONSE_CODE_OK, response.getCode());
        Assertions.assertEquals(GetSurveyResumeResponse.class, response.getClass());

    }

}
