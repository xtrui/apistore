package cn.xtrui.database.service;

import cn.xtrui.database.bean.Api;
import cn.xtrui.database.bean.TestLog;
import cn.xtrui.database.mapper.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Vector;

import static cn.xtrui.database.util.Constant.NUM_PER_PAGE;

@Component
public class ApiService {

    @Autowired
    private ApiMapper apiMapper;

    public Api getApiById(Integer id){
        return apiMapper.getApiById(id);
    }

    public boolean updateApiStatus(Integer apiId, Integer status){
        return  apiMapper.unpdateApiStatus(apiId, status);
    }

    public String getUsernameByApiIdandType(Integer apiId, Integer type){
        return apiMapper.getUsernameByApiIdandType(apiId, type);
    }

    /**
     * 通过用户Id获取api
     * @param userId
     * @return
     */

    public Vector<Api> getApiByUserId(Integer userId){
        return apiMapper.getApiByUserId(userId);
    }

    public Vector<Api> getApiByStatus(Integer status){
        return apiMapper.getApiByStatus(status);
    }

    public boolean insertApi(Api api){
        return  apiMapper.insertApi(api);
    }

    public  boolean updateApi(Api api){
        return apiMapper.updateApi(api);
    }

    public boolean deleteApiById(Integer id){
        return apiMapper.deleteApiById(id);
    }


    /**
     * testLog开始段
     */

    public List<TestLog> getLogByPage(Integer page){
        if(page == null){page = 1;}
        int count = apiMapper.getLogCount();
        if(page > count/NUM_PER_PAGE + 1){
            page = 1;
        }
        if(page < 1){
            page = count/NUM_PER_PAGE + 1;
        }

        return apiMapper.getTestLogByPage(page);
    }


    public boolean insetLog(TestLog testLog){
        return apiMapper.insertTestLog(testLog);
    }
}
