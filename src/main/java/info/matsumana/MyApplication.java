package info.matsumana;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import info.matsumana.entity.Note;
import info.matsumana.jsontype.NoteDetail;
import info.matsumana.jsontype.NoteDetails;
import info.matsumana.service.NoteService;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class MyApplication implements CommandLineRunner {

    NoteService service;

    MyApplication(NoteService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.insert("タイトル", createDetail());

        Note note = service.select(1);
        log.info("{}", note);
    }

    NoteDetails createDetail() {
        return new NoteDetails(
                Arrays.asList(new NoteDetail("森鷗外", LocalDate.of(2016, 10, 30),
                                             LocalDateTime.of(2016, 10, 30, 5, 13, 59)),
                              new NoteDetail("\uD867\uDE3Dのひらき", LocalDate.now(),
                                             LocalDateTime.now())),
                new HashMap<String, Integer>() {
                    {
                        put("第1章", 100);
                        put("第2章", 55);
                    }
                }
        );
    }
}
