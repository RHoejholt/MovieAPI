package app.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Movie {

    private double vote_average;
    private String title;

    @Column(name = "overview", length = 4096)
    private String overview;
    private Date release_date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Movie{title='" + title + "', rating=" + vote_average + "}";
    }

}