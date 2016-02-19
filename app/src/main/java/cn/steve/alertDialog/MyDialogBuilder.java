package cn.steve.alertDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/2/19.
 */
public class MyDialogBuilder {

    public static void showDialog(Context context, ResultModel model, SHAREDSTATUS type) {
        StringBuffer stringBuffer = new StringBuffer();
        switch (type) {
            case SHARED_REWARD:
                stringBuffer.append("您已成功分享了\n").append(model.activtyName).append("\n").append("本地分享您获得奖金").append(model.getReward()).append("\n*活动奖金会在48小时内发放到您账号奖金账户");
                break;
            case SHARED_FAILED:
                stringBuffer.append("分享失败\n");
                break;
            case SHARED_NOREWARD:
                stringBuffer.append("您已成功分享了\n").append(model.activtyName).append("\n").append("本地分享您获得奖金").append("\n神马!红包领完了?");
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(stringBuffer.toString());
        builder.setIcon(R.drawable.ic_launcher);
        builder.setCancelable(false);
        builder.setPositiveButton("朕知道了！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    public enum SHAREDSTATUS {
        SHARED_REWARD, SHARED_NOREWARD, SHARED_FAILED
    }

    public static class ResultModel {

        private String activtyName;
        private String reward;

        public ResultModel(String activtyName, String reward) {
            this.activtyName = activtyName;
            this.reward = reward;
        }

        public String getActivtyName() {
            return activtyName;
        }

        public void setActivtyName(String activtyName) {
            this.activtyName = activtyName;
        }

        public String getReward() {
            return reward;
        }

        public void setReward(String reward) {
            this.reward = reward;
        }
    }
}
