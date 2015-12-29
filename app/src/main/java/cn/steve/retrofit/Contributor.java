package cn.steve.retrofit;

/**
 * 数据返回对应的bean类
 *
 * Created by yantinggeng on 2015/12/29.
 */
public class Contributor {

    public final String login;
    public final int contributions;

    public Contributor(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }

    @Override
    public String toString() {
        return "Contributor{" + "login='" + login + '\'' + ", contributions=" + contributions + '}';
    }


}
