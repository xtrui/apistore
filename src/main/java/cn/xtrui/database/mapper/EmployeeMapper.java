package cn.xtrui.database.mapper;

import cn.xtrui.database.bean.V;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
//指定这是一个操作数据库的mapper
public interface EmployeeMapper {
    @Select("select * from V where VID=#{id}")
    @Results({
            @Result(column = "VID", property = "user", many = @Many(
                select = "cn.xtrui.database.mapper.Usermapper.getUserById",
                    fetchType = FetchType.LAZY
            )
            )
    })
    public V getEmployeeById(Integer id);

}
