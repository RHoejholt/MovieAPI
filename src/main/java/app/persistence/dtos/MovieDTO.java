package app.persistence.dtos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;


@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDTO {

    int id;
    String title;
    String overview;
    String release_date;

}
