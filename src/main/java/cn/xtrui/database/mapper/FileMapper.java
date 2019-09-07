package cn.xtrui.database.mapper;

import cn.xtrui.database.bean.MyFile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Vector;

@Mapper
public interface FileMapper {

    @Select("select count(*) from file")
    public Integer count();

    @Select("select * from file where id = #{id}")
    public MyFile getFileById(Integer id);

    @Select("select * from file where type = #{type}")
    public Vector<MyFile> getFilesByType(String type);

    @Select("select * from file where name=#{fileName}")
    public MyFile getFileByFileName(String fileName);

    @Select("select * from file where id between 10* (#{page}-1) and 10*#{page}")
    public Vector<MyFile> getFilesByPage(Integer page);

    @Delete("delete from file where id = #{id}")
    public boolean deleteFileById(Integer id);

    @Insert("insert into file(name,path,type,time) values(#{name},#{path},#{type},NOW())")
    public boolean insertFile(MyFile myFile);
}
