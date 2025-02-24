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
    Date release_date;
    boolean adult;



    public String toString() {
        return """
                🎬 Movie Details 🎬
                =====================
                📌 Title: %s
                📅 Release Date: %s
                🔞 Adult: %s
                📝 Overview: 
                %s
                =====================
                """.formatted(
                title,
                release_date,
                (adult ? "Yes" : "No"),
                wrapText(overview, 80)
        );
    }

    private String wrapText(String text, int lineLength) {
        if (text == null || text.isEmpty()) return "No description available.";
        StringBuilder wrapped = new StringBuilder();
        String[] words = text.split(" ");
        int currentLength = 0;

        for (String word : words) {
            if (currentLength + word.length() > lineLength) {
                wrapped.append("\n");  // New line when exceeding the limit
                currentLength = 0;
            }
            wrapped.append(word).append(" ");
            currentLength += word.length() + 1;
        }
        return wrapped.toString().trim();
    }
}
