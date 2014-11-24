package cn.steve.customdialog;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.steve.customdialog.dialog.Effectstype;
import cn.steve.customdialog.dialog.NiftyDialogBuilder;
import cn.steve.study.R;
public class SimpleDialog extends Activity {
	private Button mButton = null;
	//自定义的效果对象
	private Effectstype mEffect;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_simpletest);
		//创建效果对象
		mEffect=Effectstype.Fadein;
		mButton = (Button) findViewById(R.id.dialog_simple_btn);
		//设置监听器
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//对话框管理器
				NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(SimpleDialog.this);
			    	//设置展示的对话框的属性
				    dialogBuilder
	                .withTitle("这是对话框的标题")                                 
	                .withTitleColor("#FFFFFF")                                  
	                .withDividerColor("#11000000")                            
	                .withMessage("This is a modal Dialog.")                    
	                .withMessageColor("#FFFFFF")                                
	                .withIcon(getResources().getDrawable(R.drawable.customer_icon))
	                .isCancelableOnTouchOutside(true)                           
	                .withDuration(700)                                          
	                .withEffect(mEffect)                                        
	                .withButton1Text("第一个按钮显示的文字")                                      
	                .withButton2Text("第二个按钮显示的文字")                                  
	                .setCustomView(R.layout.dialog_view,SimpleDialog.this) 
	                //设置点击第一个按钮的时候的响应函数
	                .setButton1Click(new View.OnClickListener() {
	                    @Override
	                    public void onClick(View v) {
	                        Toast.makeText(v.getContext(), "第一个被点", Toast.LENGTH_SHORT).show();
	                    }
	                })
	                //设置点击第二个按钮的时候的响应函数
	                .setButton2Click(new View.OnClickListener() {
	                    @Override
	                    public void onClick(View v) {
	                        Toast.makeText(v.getContext(), "第二个被点击", Toast.LENGTH_SHORT).show();
	                    }
	                })
	                //调用函数展示
	                .show();
			}
		});

	}
}
