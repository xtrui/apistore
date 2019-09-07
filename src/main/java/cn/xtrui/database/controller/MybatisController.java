package cn.xtrui.database.controller;

import cn.xtrui.database.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MybatisController {
    @Autowired
    EmployeeMapper employeeMapper;
    @ResponseBody
    @GetMapping("/e/{id}")
    public Object get(@PathVariable("id") Integer id){
        MultiValueMap<String, Object> multiValueMap;
        return employeeMapper.getEmployeeById(id);
    }


    @GetMapping("/mum")
    @ResponseBody
    public Object get(){
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("wo", "daye");
        multiValueMap.add("wo", "shen");

        return multiValueMap;
    }

}
