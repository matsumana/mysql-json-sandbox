package info.matsumana.jsontype;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NoteDetails {
    private List<NoteDetail> names;
    private NoteDetailSection section;
}
