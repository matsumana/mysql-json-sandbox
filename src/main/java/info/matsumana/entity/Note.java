package info.matsumana.entity;

import info.matsumana.jsontype.NoteDetails;
import lombok.Data;

@Data
public class Note {
    private int id;
    private String title;
    private NoteDetails detail;
}
