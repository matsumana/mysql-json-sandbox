package info.matsumana.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.matsumana.entity.Note;
import info.matsumana.jsontype.NoteDetails;
import info.matsumana.mapper.NoteMapper;

@Service
@Transactional
public class NoteService {

    NoteMapper mapper;

    NoteService(NoteMapper mapper) {
        this.mapper = mapper;
    }

    public void insert(String title, NoteDetails detail) {
        Note note = new Note();
        note.setTitle(title);
        note.setDetail(detail);
        mapper.insert(note);
    }

    public Note selectById(int id) {
        return mapper.selectById(id);
    }

    public Note selectByName(String name) {
        return mapper.selectByName(name);
    }
}
