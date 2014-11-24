package cn.steve.statckover;

import java.io.IOException;

import net.sf.stackwrap4j.StackOverflow;
import net.sf.stackwrap4j.StackWrapper;
import net.sf.stackwrap4j.entities.Stats;
import net.sf.stackwrap4j.json.JSONException;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import cn.steve.study.R;

public class StackOverFlowActivity extends Activity {
	private TextView mStack_tv = null;
	String displayText = null;
	Stats stats;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_stack);
		StackWrapper stackWrap = new StackOverflow();
		mStack_tv = (TextView) findViewById(R.id.stack_tv);
		try {
			stats = stackWrap.getStats();
			displayText = "Stack Overflow Statistics";
			displayText += "\nTotal Questions: " + stats.getTotalQuestions();
			
			System.out.println(stats.getTotalQuestions());
			
			displayText += "\nTotal Unanswered: " + stats.getTotalUnanswered();
			displayText += "\nTotal Answers: " + stats.getTotalAnswers();
			displayText += "\nTotal Comments: " + stats.getTotalComments();
			displayText += "\nTotal Votes: " + stats.getTotalVotes();
			displayText += "\nTotal Users: " + stats.getTotalUsers();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mStack_tv.setText(displayText);

	}

}
