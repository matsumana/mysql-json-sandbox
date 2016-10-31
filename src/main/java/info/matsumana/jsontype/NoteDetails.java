package info.matsumana.jsontype;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NoteDetails {
    private List<NoteDetail> names;
    private Map<String, Integer> sections;
}
