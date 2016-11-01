package info.matsumana;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import info.matsumana.entity.Note;
import info.matsumana.jsontype.NoteDetail;
import info.matsumana.jsontype.NoteDetailSection;
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

        service.insert("タイトル1",
                       new NoteDetails(
                               Arrays.asList(
                                       new NoteDetail("鷗外", LocalDate.of(2016, 10, 30),
                                                      LocalDateTime.of(2016, 10, 30, 5, 13, 59)),
                                       new NoteDetail("\uD869\uDEC0", LocalDate.now(),
                                                      LocalDateTime.now())),
                               new NoteDetailSection(100, 55))
        );

        service.insert("タイトル2",
                       new NoteDetails(
                               Arrays.asList(
                                       new NoteDetail("\uD83C\uDF63\uD83C\uDF7B", LocalDate.of(2016, 11, 30),
                                                      LocalDateTime.of(2016, 11, 30, 5, 13, 59)),
                                       new NoteDetail("\uD867\uDE3Dのひらき", LocalDate.now(),
                                                      LocalDateTime.now())),
                               new NoteDetailSection(200, 300))
        );

        service.insert("タイトル3",
                       new NoteDetails(
                               Arrays.asList(
                                       new NoteDetail("\uD869\uDECF", LocalDate.of(2016, 12, 30),
                                                      LocalDateTime.of(2016, 12, 30, 5, 13, 59)),
                                       new NoteDetail("\uD869\uDECA", LocalDate.now(),
                                                      LocalDateTime.now())),
                               new NoteDetailSection(1_000, 400))
        );

        Note note1 = service.selectById(2);
        log.info("note1={}", note1);

        Note note2 = service.selectByName("%鷗%");
        log.info("note2={}", note2);

        Note note3 = service.selectByPages(1_000);
        log.info("note3={}", note3);
    }
}
