package info.matsumana.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import info.matsumana.entity.Note;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO note (title, detail) VALUES (#{title}, #{detail})")
    @Options(useGeneratedKeys = true)
    void insert(Note note);

    @Select("SELECT id, title, detail FROM note WHERE id = #{id}")
    Note selectById(int id);

    @Select("SELECT * FROM note WHERE detail->'$.names[*].name' like #{name}")
    Note selectByName(String name);
}
