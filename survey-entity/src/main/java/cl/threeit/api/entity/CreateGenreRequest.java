package cl.threeit.api.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateGenreRequest {

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 20)
    private String code;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 32)
    private String Description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
