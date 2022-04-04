package cl.threeit.api.entity;

public class SurveyEntity {
    private Long id;
    private String email;
    private String musicGenreCode;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMusicGenreCode(String musicGenreCode) {
        this.musicGenreCode = musicGenreCode;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getMusicGenreCode() {
        return musicGenreCode;
    }
}
