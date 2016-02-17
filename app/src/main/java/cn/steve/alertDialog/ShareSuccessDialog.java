package cn.steve.alertDialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.steve.study.R;

/**
 * Created by yantinggeng on 2016/2/17.
 */
public class ShareSuccessDialog extends Dialog {

    private android.widget.TextView rewardTitle;
    private android.widget.TextView shareSuccessRewardName;
    private android.widget.TextView rewardTip1;
    private android.widget.TextView rewardTip2;
    private android.widget.TextView rewardNumber;
    private android.widget.RelativeLayout orderPaySuccessShareWithReward;
    private android.widget.Button shareFailButton;
    private android.widget.RelativeLayout orderPaySuccessShareFail;
    private android.widget.TextView shareSuccessActivityName;
    private android.widget.RelativeLayout orderPaySuccessShareNoReward;

    public ShareSuccessDialog(Context context, int themeResId) {
        super(context, themeResId);
        createView();
    }

    public static ShareSuccessDialog buildDialog(Context context, int type, final ShareSuccessResultModel data) {
        final ShareSuccessDialog dialog = new ShareSuccessDialog(context, 0);
        switch (type) {
            case 1:
                dialog.orderPaySuccessShareWithReward.setVisibility(View.VISIBLE);
                dialog.shareSuccessRewardName.setText(data.getActivtyName());
                dialog.rewardNumber.setText(data.getReward());
                break;
            case 2:
                dialog.orderPaySuccessShareFail.setVisibility(View.VISIBLE);
                dialog.setCanceledOnTouchOutside(false);
                dialog.shareFailButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
            case 3:
                dialog.orderPaySuccessShareNoReward.setVisibility(View.VISIBLE);
                dialog.shareSuccessActivityName.setText(data.getActivtyName());
                break;
        }
        return dialog;
    }

    private void createView() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_sharesuccess, null);
        this.orderPaySuccessShareNoReward = (RelativeLayout) view.findViewById(R.id.orderPaySuccessShareNoReward);
        this.shareSuccessActivityName = (TextView) view.findViewById(R.id.shareSuccessActivityName);
        this.orderPaySuccessShareFail = (RelativeLayout) view.findViewById(R.id.orderPaySuccessShareFail);
        this.shareFailButton = (Button) view.findViewById(R.id.shareFailButton);
        this.orderPaySuccessShareWithReward = (RelativeLayout) view.findViewById(R.id.orderPaySuccessShareWithReward);
        this.rewardNumber = (TextView) view.findViewById(R.id.rewardNumber);
        this.rewardTip2 = (TextView) view.findViewById(R.id.rewardTip2);
        this.rewardTip1 = (TextView) view.findViewById(R.id.rewardTip1);
        this.shareSuccessRewardName = (TextView) view.findViewById(R.id.shareSuccessRewardName);
        this.rewardTitle = (TextView) view.findViewById(R.id.rewardTitle);
        this.setContentView(view);
    }


    public static class ShareSuccessResultModel {

        private String activtyName;
        private String reward;

        public ShareSuccessResultModel(String activtyName, String reward) {
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
