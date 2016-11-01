package info.matsumana;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import info.matsumana.entity.Note;
import info.matsumana.service.NoteService;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class MyApplication implements CommandLineRunner {

    NoteService service;

    MyApplication(NoteService service) {
        this.service = service;
    }

    public static void main(String... args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Note note = service.selectById(1);
        log.info("note={}", note);
    }
}
