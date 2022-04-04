package cl.threeit.api.business.service;

import cl.threeit.api.backend.domain.Genre;
import cl.threeit.api.backend.domain.Survey;
import cl.threeit.api.backend.repository.IGenresRepository;
import cl.threeit.api.backend.repository.ISurveyRepository;
import cl.threeit.api.business.utils.ResponseUtils;
import cl.threeit.api.entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SurveyService {

    private final IGenresRepository genresRepository;

    private final ISurveyRepository surveyRepository;

    public SurveyService(ISurveyRepository surveyRepository, IGenresRepository genresRepository) {
        this.surveyRepository = surveyRepository;
        this.genresRepository = genresRepository;
    }

    public CreateSurveyResponse createRecord(CreateSurveyRequest request){
        Survey survey = surveyRepository.findByEmail(request.getEmail().trim());

        if(survey != null){
            throw new InvalidParameterException("Email already exist");
        }

        Genre genre = genresRepository.findByCode(request.getGenreCode());

        if(genre == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Genre not exist");
        }

        Survey newSurvey = new Survey();
        newSurvey.setEmail(request.getEmail());
        newSurvey.setGenre(genre);

        SurveyEntity surveyEntity = mapToEntity(surveyRepository.save(newSurvey),genre);

        CreateSurveyResponse response = new CreateSurveyResponse();
        response.setSurveyEntity(surveyEntity);
        response.setCode(ResponseUtils.RESPONSE_CODE_OK);
        response.setDescription(ResponseUtils.RESPONSE_MESSAGE_OK);

        return response;
    }

    public GetSurveyResumeResponse getResume(){
        List<Object[]> resume = surveyRepository.getResume();

        if(resume.size() < 1){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resume not found");
        }

        List<SurveyResumeEntity> entityList = new ArrayList<>();

        for(Object[] obj : resume){
            SurveyResumeEntity entity = new SurveyResumeEntity();
            entity.setCount(Integer.valueOf(String.valueOf(obj[1])));
            entity.setGenreName(String.valueOf(obj[0]));
            entityList.add(entity);
        }

        GetSurveyResumeResponse response = new GetSurveyResumeResponse();
        response.setResume(entityList);
        response.setCode(ResponseUtils.RESPONSE_CODE_OK);
        response.setDescription(ResponseUtils.RESPONSE_MESSAGE_OK);

        return response;
    }

    private SurveyEntity mapToEntity(Survey survey, Genre genre){

        SurveyEntity surveyEntity = new SurveyEntity();

        surveyEntity.setMusicGenreCode(genre.getCode());
        surveyEntity.setEmail(survey.getEmail());
        surveyEntity.setId(survey.getId());

        return surveyEntity;
    }
}
