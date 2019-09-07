package cn.xtrui.database.mapper;

import cn.xtrui.database.bean.Api;
import cn.xtrui.database.bean.TestLog;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Vector;

@Mapper
public interface ApiMapper {
    @Select("select * from api where id = #{id}")
    public Api getApiById(Integer  id);

    @Select("select * from api where status = #{status}")
    public Vector<Api> getApiByStatus(Integer status);

    @Update("update api set status = #{status} where id = #{id}")
    public boolean unpdateApiStatus(Integer id,Integer status);

    @Select("select * from api where id in(select api_id from api_user where user_id=#{userId})")
    public Vector<Api> getApiByUserId(Integer userId);

    //根据apiid和用户type获取用户名称;

    @Select("select username from user where id in (select user_id from api_user where api_id = #{apiId} and type = #{type})")
    public String getUsernameByApiIdandType(Integer apiId,Integer type);

    @InsertProvider(type = ApiDynaSqlProvider.class,method = "insert")
    public boolean insertApi(Api api);

    @UpdateProvider(type = ApiDynaSqlProvider.class,method = "update")
    public  boolean updateApi(Api api);

    @Delete("delete from api where id = #{id}")
    public  boolean deleteApiById(Integer id);

    /**
     * testLog 开始段
     */

    @Select("select * from test_log where id between 10* (#{page}-1) and 10*#{page} order by time desc")
    public List<TestLog> getTestLogByPage(Integer page);

    @Select("select count(*) from test_log")
    public Integer getLogCount();

    @Insert("insert into test_log(id,user_id,api_id,code,content,time) value(#{id},#{userId},#{apiId},#{code},#{content},#{time})")
    public boolean insertTestLog(TestLog testLog);
}
