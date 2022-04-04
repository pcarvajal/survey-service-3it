package cl.threeit.api.entity;

import cl.threeit.api.entity.generics.GenericResponse;

public class CreateSurveyResponse extends GenericResponse {
    private SurveyEntity surveyEntity;

    public void setSurveyEntity(SurveyEntity surveyEntity) {
        this.surveyEntity = surveyEntity;
    }

    public SurveyEntity getSurveyEntity() {
        return surveyEntity;
    }
}
