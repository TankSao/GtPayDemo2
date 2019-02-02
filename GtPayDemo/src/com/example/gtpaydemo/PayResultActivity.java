package com.example.gtpaydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PayResultActivity extends Activity{
	private ImageView iv;
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pay_result);
		iv = (ImageView) findViewById(R.id.iv_result);
		tv = (TextView) findViewById(R.id.tv_result);
		int type = getIntent().getIntExtra("type", 0);
		if(type == 0){
			iv.setBackgroundResource(R.drawable.error);
			tv.setText("支付失败");
		}else{
			iv.setBackgroundResource(R.drawable.success);
			tv.setText("支付成功");
		}
	}
}
