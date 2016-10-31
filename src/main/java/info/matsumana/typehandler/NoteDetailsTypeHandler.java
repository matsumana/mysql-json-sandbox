package info.matsumana.typehandler;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import info.matsumana.jsontype.NoteDetail;
import info.matsumana.jsontype.NoteDetails;

@MappedTypes(NoteDetails.class)
public class NoteDetailsTypeHandler extends BaseTypeHandler<NoteDetails> {

    static final DateTimeFormatter DATE_TIME_FORMATTER_LOCAL_DATE =
            DateTimeFormatter.ofPattern("yyyy/MM/dd");
    static final DateTimeFormatter DATE_TIME_FORMATTER_LOCAL_DATE_TIME =
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    ObjectMapper mapper;

    public NoteDetailsTypeHandler() {
        JavaTimeModule module = new JavaTimeModule();

        // LocalDate
        module.addSerializer(LocalDate.class,
                             new LocalDateSerializer(DATE_TIME_FORMATTER_LOCAL_DATE));
        module.addDeserializer(LocalDate.class,
                               new LocalDateDeserializer(DATE_TIME_FORMATTER_LOCAL_DATE));

        // LocalDateTime
        module.addSerializer(LocalDateTime.class,
                             new LocalDateTimeSerializer(DATE_TIME_FORMATTER_LOCAL_DATE_TIME));
        module.addDeserializer(LocalDateTime.class,
                               new LocalDateTimeDeserializer(DATE_TIME_FORMATTER_LOCAL_DATE_TIME));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        this.mapper = mapper;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, NoteDetails parameter, JdbcType jdbcType)
            throws SQLException {
        try {
            ps.setString(i, mapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public NoteDetails getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String jsonString = rs.getString(columnName);
        return convert(jsonString);
    }

    @Override
    public NoteDetails getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonString = rs.getString(columnIndex);
        return convert(jsonString);
    }

    @Override
    public NoteDetails getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonString = cs.getString(columnIndex);
        return convert(jsonString);
    }

    NoteDetails convert(String jsonString) {
        if (jsonString == null) {
            return null;
        } else {
            try {
                return mapper.readValue(jsonString, NoteDetails.class);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }
}
