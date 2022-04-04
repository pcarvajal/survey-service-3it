package cl.threeit.api.util;

import cl.threeit.api.backend.domain.Survey;
import cl.threeit.api.business.utils.ResponseUtils;
import cl.threeit.api.entity.CreateSurveyRequest;
import cl.threeit.api.entity.GetSurveyResumeResponse;
import cl.threeit.api.entity.SurveyResumeEntity;

import java.util.ArrayList;
import java.util.List;

public class SurveyServiceTestUtil {

    public static Survey getSurvey(){
        Survey survey = new Survey();
        survey.setGenre(GenreServiceTestUtil.getGenre());
        survey.setEmail("test@test.com");
        survey.setId(1L);

        return survey;
    }

    public static CreateSurveyRequest getSurveyRequest(){
        CreateSurveyRequest request = new CreateSurveyRequest();
        request.setEmail("test@test.com");
        request.setGenreCode("ROCK");

        return request;
    }

    public static List<Object[]> getResume(){
        List<Object[]> resume = new ArrayList<>();

        Object[] resume1 = new Object[]{
                "Rock",
                1
        };
        resume.add(resume1);

        Object[] resume2 = new Object[]{
                "Pop",
                2
        };
        resume.add(resume2);

        return resume;
    }

    public static GetSurveyResumeResponse getSurveyResumeResponse(){
        List<Object[]> resume = getResume();
        GetSurveyResumeResponse response = new GetSurveyResumeResponse();
        List<SurveyResumeEntity> entityList = new ArrayList<>();

        for(Object[] obj : resume){
            SurveyResumeEntity entity = new SurveyResumeEntity();
            entity.setCount(Integer.valueOf(String.valueOf(obj[1])));
            entity.setGenreName(String.valueOf(obj[0]));
            entityList.add(entity);
        }

        response.setCode(ResponseUtils.RESPONSE_CODE_OK);
        response.setDescription(ResponseUtils.RESPONSE_MESSAGE_OK);
        response.setResume(entityList);

        return response;
    }
}
