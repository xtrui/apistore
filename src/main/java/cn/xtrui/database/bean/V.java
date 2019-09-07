package cn.xtrui.database.bean;

import java.util.Set;

public class V {
    private Integer VID;
    private String Title;
    private String Jj;

    private Integer Cnum;
    private String Url;

    private Set<User> user;

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Integer getVID() {
        return VID;
    }

    public void setVID(Integer VID) {
        this.VID = VID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getJj() {
        return Jj;
    }

    public void setJj(String jj) {
        Jj = jj;
    }

    public Integer getCnum() {
        return Cnum;
    }

    public void setCnum(Integer cnum) {
        Cnum = cnum;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
