package cl.threeit.api.entity;

public class SurveyResumeEntity {
    private String genreName;
    private Integer count;

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return genreName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
