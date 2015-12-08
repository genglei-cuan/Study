package cn.steve.wechat;

/**
 * 用作之后分层用的，产生model对象
 *
 * Created by yantinggeng on 2015/12/8.
 */
public class ModelCreater {

    public static WeChatModel createModel(String content, int type) {
        WeChatModel weChatModel = new WeChatModel();
        weChatModel.setTextContent(content);
        weChatModel.setType(type);
        return weChatModel;
    }

}
