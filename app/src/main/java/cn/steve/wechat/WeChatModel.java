package cn.steve.wechat;

/**
 * Created by yantinggeng on 2015/12/7.
 */
public class WeChatModel {

    private String textContent;
    private String textNickName;
    private int type = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTextNickName() {
        return textNickName;
    }

    public void setTextNickName(String textNickName) {
        this.textNickName = textNickName;
    }

}
