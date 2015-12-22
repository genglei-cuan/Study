package cn.steve.listview.robot;

/**
 * 用作之后分层用的，产生model对象
 *
 * Created by yantinggeng on 2015/12/22.
 */
public class ModelCreater {
    
    public static OnlineModel createModel(String content, int type) {
        OnlineModel model = new OnlineModel();
        model.setTextContent(content);
        model.setType(type);
        return model;
    }

}
