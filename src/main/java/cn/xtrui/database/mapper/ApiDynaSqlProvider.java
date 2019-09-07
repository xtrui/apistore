package cn.xtrui.database.mapper;

import cn.xtrui.database.bean.Api;
import org.apache.ibatis.jdbc.SQL;

public class ApiDynaSqlProvider {

    public String insert(Api api){
        return new SQL(){
            {
                INSERT_INTO("api");
                if(api.getInfo() == null || api.getInfo() ==""){
                    VALUES("id,name,status,time,url,req_param,method",
                            "null,#{name},1,#{time},#{url},#{reqParam},#{method}");
                }
                else {
                    VALUES("id,name,status,info,time,url,req_param,method",
                            "null,#{name},1,#{info},#{time},#{url},#{reqParam},#{method}");
                }
            }
        }.toString();
    }


    public String update(Api api){
        return new SQL(){
            {
                UPDATE("api");
                SET("name = #{name}");
                SET("status = 1");
                SET("time = #{time}");
                SET("url = #{url}");
                SET("req_param = #{reqParam}");
                if(api.getInfo() == null || api.getInfo() ==""){ }
                else {
                    SET("info = #{info}");
                }
                SET("method = #{method}");
                WHERE("id = #{id}");
            }
        }.toString();

    }
}
