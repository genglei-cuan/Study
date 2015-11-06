package cn.steve.rxandroid;

/**
 * Created by yantinggeng on 2015/11/2.
 */
public class Course {

    private int cid;
    private String cname;

    public Course(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
