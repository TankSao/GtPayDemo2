package com.example.gtpaydemo;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gtpaydemo.listener.OnPwdInputFinishedListener;
import com.example.gtpaydemo.view.PwdInputPopWindow;


public class MainActivity extends ActionBarActivity implements OnClickListener{

	private ArrayList<CheckBox> cbs;
	private int payType = 0;//0΢�ţ�1֧������2���п���3��Ǯ
	private LinearLayout llWxpay,llAlipay,llCardpay,llXjpay;
	private CheckBox cbWxpay,cbAlipay,cbCardpay,cbXjpay;
	private Button pay;
	private View background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        setListeners();
    }


    private void setListeners() {
		// TODO �Զ����ɵķ������
		llWxpay.setOnClickListener(this);
		llAlipay.setOnClickListener(this);
		llCardpay.setOnClickListener(this);
		llXjpay.setOnClickListener(this);
		cbWxpay.setOnClickListener(this);
		cbAlipay.setOnClickListener(this);
		cbCardpay.setOnClickListener(this);
		cbXjpay.setOnClickListener(this);
		pay.setOnClickListener(this);
	}


	private void initViews() {
		// TODO �Զ����ɵķ������
		cbs = new ArrayList<CheckBox>();
		llWxpay = (LinearLayout) findViewById(R.id.ll_wxpay);
		llAlipay = (LinearLayout) findViewById(R.id.ll_alipay);
		llCardpay = (LinearLayout) findViewById(R.id.ll_cardpay);
		llXjpay = (LinearLayout) findViewById(R.id.ll_xjpay);
		cbWxpay = (CheckBox) findViewById(R.id.cb_wxpay);
		cbAlipay = (CheckBox) findViewById(R.id.cb_alipay);
		cbCardpay = (CheckBox) findViewById(R.id.cb_cardpay);
		cbXjpay = (CheckBox) findViewById(R.id.cb_xjpay);
		cbs.add(cbWxpay);
		cbs.add(cbAlipay);
		cbs.add(cbCardpay);
		cbs.add(cbXjpay);
		pay = (Button) findViewById(R.id.pay);
		background=(View)findViewById(R.id.home_background);
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onClick(View view) {
		// TODO �Զ����ɵķ������
		switch (view.getId()) {
		case R.id.ll_wxpay:
		case R.id.cb_wxpay:
			setPayType(0);
			break;
		case R.id.ll_alipay:
		case R.id.cb_alipay:
			setPayType(1);
			break;
		case R.id.ll_cardpay:
		case R.id.cb_cardpay:
			setPayType(2);
			break;
		case R.id.ll_xjpay:
		case R.id.cb_xjpay:
			setPayType(3);
			break;
		case R.id.pay:
			//Toast.makeText(MainActivity.this, "ѡ���֧����ʽ�ǣ�"+getType(payType), Toast.LENGTH_LONG).show();
			callPwdPop();//�������������
			break;

		default:
			break;
		}
	}

	private String getType(int payType2) {
		// TODO �Զ����ɵķ������
		String type = "";
		switch (payType2) {
		case 0:
			type = "΢��֧��";
			break;
		case 1:
			type = "֧����";
			break;
		case 2:
			type = "���п�֧��";
			break;
		case 3:
			type = "��Ǯ֧��";
			break;

		default:
			break;
		}
		return type;
	}


	private void setPayType(int i) {
		// TODO �Զ����ɵķ������
		payType = i;
		switch (payType) {
		case 0:
			if(!cbWxpay.isChecked()){
				cbWxpay.setChecked(true);
			}
			if(cbAlipay.isChecked()){
				cbAlipay.setChecked(false);
			}
			if(cbCardpay.isChecked()){
				cbCardpay.setChecked(false);
			}
			if(cbXjpay.isChecked()){
				cbXjpay.setChecked(false);
			}
			break;
		case 1:
			if(cbWxpay.isChecked()){
				cbWxpay.setChecked(false);
			}
			if(!cbAlipay.isChecked()){
				cbAlipay.setChecked(true);
			}
			if(cbCardpay.isChecked()){
				cbCardpay.setChecked(false);
			}
			if(cbXjpay.isChecked()){
				cbXjpay.setChecked(false);
			}
			break;
		case 2:
			if(cbWxpay.isChecked()){
				cbWxpay.setChecked(false);
			}
			if(cbAlipay.isChecked()){
				cbAlipay.setChecked(false);
			}
			if(!cbCardpay.isChecked()){
				cbCardpay.setChecked(true);
			}
			if(cbXjpay.isChecked()){
				cbXjpay.setChecked(false);
			}
			break;
		case 3:
			if(cbWxpay.isChecked()){
				cbWxpay.setChecked(false);
			}
			if(cbAlipay.isChecked()){
				cbAlipay.setChecked(false);
			}
			if(cbCardpay.isChecked()){
				cbCardpay.setChecked(false);
			}
			if(!cbXjpay.isChecked()){
				cbXjpay.setChecked(true);
			}
			break;

		default:
			break;
		}
	}
	private void callPwdPop() {
		// TODO �Զ����ɵķ������
		final PwdInputPopWindow popWindow = new PwdInputPopWindow(MainActivity.this, background);
		popWindow.showAsDropDown(background);
		popWindow.setOnFinishInput(new OnPwdInputFinishedListener() {
			
			@Override
			public void onFinish(String pwd) {
				// TODO �Զ����ɵķ������
				popWindow.onDismiss();
				//Toast.makeText(MainActivity.this, pwd, Toast.LENGTH_LONG).show();
				if(pwd.equals("123456")){
					toActivity(1);
				}else{
					toActivity(0);
				}
			}

			private void toActivity(int i) {
				// TODO �Զ����ɵķ������
				Intent intent = new Intent(MainActivity.this,PayResultActivity.class);
				intent.putExtra("type", i);
				startActivity(intent);
				MainActivity.this.finish();
			}
		});
	}
}
