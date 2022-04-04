package cl.threeit.api.entity;

import cl.threeit.api.entity.generics.GenericResponse;

import java.util.List;

public class GetSurveyResumeResponse extends GenericResponse {
    private List<SurveyResumeEntity> resume;

    public List<SurveyResumeEntity> getResume() {
        return resume;
    }

    public void setResume(List<SurveyResumeEntity> resume) {
        this.resume = resume;
    }
}
