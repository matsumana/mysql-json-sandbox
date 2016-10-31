package info.matsumana.jsontype;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NoteDetail {
    private String name;
    private LocalDate date;
    private LocalDateTime dateTime;
}
