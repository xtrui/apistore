package cn.xtrui.database.bean;

import java.util.Date;

public class TestLog {
    private Integer id;
    private Integer userId;
    private Integer apiId;
    private Integer code;
    private String content;
    private Date time;

    public TestLog(Integer id, Integer userId, Integer apiId, Integer code, String content, Date time) {
        this.id = id;
        this.userId = userId;
        this.apiId = apiId;
        this.code = code;
        this.content = content;
        this.time = time;
    }

    public TestLog() {

    }

    @Override
    public String toString() {
        return "TestLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", apiId=" + apiId +
                ", code=" + code +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
