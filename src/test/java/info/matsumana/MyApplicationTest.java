package info.matsumana;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import info.matsumana.entity.Note;
import info.matsumana.jsontype.NoteDetail;
import info.matsumana.jsontype.NoteDetailSection;
import info.matsumana.jsontype.NoteDetails;
import info.matsumana.service.NoteService;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MyApplicationTest {

    @Autowired
    NoteService service;

    @Test
    public void testInsertAndSelect() {
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

        service.insert("タイトル4",
                       new NoteDetails(
                               Arrays.asList(
                                       new NoteDetail("ほげ", LocalDate.of(2016, 1, 30),
                                                      LocalDateTime.of(2016, 1, 30, 5, 13, 59)),
                                       new NoteDetail("ふが", LocalDate.now(),
                                                      LocalDateTime.now())),
                               new NoteDetailSection(10, 40))
        );

        Note note1 = service.selectById(2);
        log.info("note1={}", note1);
        assertThat(note1.getDetail().getNames().get(0).getName(), is("\uD83C\uDF63\uD83C\uDF7B"));

        Note note2 = service.selectByName("%鷗%");
        log.info("note2={}", note2);
        assertThat(note2.getDetail().getNames().get(0).getName(), is("鷗外"));

        Note note3 = service.selectByPages(1_000);
        log.info("note3={}", note3);
        assertThat(note3.getDetail().getNames().get(0).getName(), is("\uD869\uDECF"));

        Note note4 = service.selectByName0("鷗外");
        log.info("note4={}", note4);
        assertThat(note4.getDetail().getNames().get(0).getName(), is("鷗外"));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testNotNull() {
        service.insert("タイトル99",
                       new NoteDetails(
                               Collections.emptyList(),
                               new NoteDetailSection(0, 0))
        );
    }
}
