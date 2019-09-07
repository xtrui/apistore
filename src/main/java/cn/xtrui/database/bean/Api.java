package cn.xtrui.database.bean;

import java.util.Date;

public class Api {
    private Integer id;
    private Integer status;
    private String name;
    private String url;
    private  String info;
    private String reqParam;
    private Date time;
    private String method;

    @Override
    public String toString() {
        return "Api{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", info='" + info + '\'' +
                ", reqParam='" + reqParam + '\'' +
                ", time=" + time +
                ", method='" + method + '\'' +
                '}';
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReqParam() {
        return reqParam;
    }

    public void setReqParam(String reqParam) {
        this.reqParam = reqParam;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
