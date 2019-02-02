package com.example.gtpaydemo.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtpaydemo.R;
import com.example.gtpaydemo.adapter.PatBtnAdapter;
import com.example.gtpaydemo.listener.OnPwdInputFinishedListener;

public class PwdInputPopWindow implements OnDismissListener, OnClickListener {
	private PopupWindow popupWindow;
	private OnItemClickListener listener;
	private Context context;
	private String cate_id;//�ص���id
	private String strPassword;     //���������
	private TextView[] tvList;      //�����鱣��6��TextView��Ϊʲô�����飿
	//��Ϊ��6������򲻻���ˣ��������ڴ�����̶��ռ䣬��Listʡ�ռ䣨�Լ���Ϊ��
	private GridView gridView;    //��GrideView���ּ��̣���ʵ�����������ļ��̣�ֻ��ģ����̵Ĺ���
	private ArrayList<Map<String, String>> valueList;    //���˿��������ʣ�Ϊ�����ﲻ�������ˣ�
	//��ΪҪ��Adapter�����䣬�����鲻����adapter�����

	private ImageView imgCancel;
	private TextView tvForget;
	private int currentIndex = -1;    //���ڼ�¼��ǰ���������λ��
	private PatBtnAdapter adapter;
	private Intent intent;
	private View backgroundView;
	private AnimationDrawable animationDrawable;

	public PwdInputPopWindow(final Context context, View backgroundView) {
		this.context=context;
		this.backgroundView=backgroundView;
		View view=LayoutInflater.from(context).inflate(R.layout.pop_pwd_input, null);
		popupWindow=new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		//����popwindow�Ķ���Ч��
		popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		popupWindow.setOnDismissListener(this);// ��popWindow��ʧʱ�ļ���
		valueList = new ArrayList<Map<String, String>>();
		tvList = new TextView[6];
		setView();

		imgCancel = (ImageView) view.findViewById(R.id.img_cance);
		tvForget = (TextView) view.findViewById(R.id.tv_forgetPwd);

		tvList[0] = (TextView) view.findViewById(R.id.tv_pass1);
		tvList[1] = (TextView) view.findViewById(R.id.tv_pass2);
		tvList[2] = (TextView) view.findViewById(R.id.tv_pass3);
		tvList[3] = (TextView) view.findViewById(R.id.tv_pass4);
		tvList[4] = (TextView) view.findViewById(R.id.tv_pass5);
		tvList[5] = (TextView) view.findViewById(R.id.tv_pass6);

		gridView = (GridView) view.findViewById(R.id.gv_keybord);
		adapter=new PatBtnAdapter(context,valueList);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (position < 11 && position != 9) {    //���0~9��ť
					if (currentIndex >= -1 && currentIndex < 5) {      //�ж�����λ�á�������ҪС������Խ��
						tvList[++currentIndex].setText(valueList.get(position).get("name"));
					}
				} else {
					if (position == 11) {      //����˸��
						if (currentIndex - 1 >= -1) {      //�ж��Ƿ�ɾ����ϡ�������ҪС������Խ��
							tvList[currentIndex--].setText("");
						}
					}
				}
			}
		});
		imgCancel.setOnClickListener(this);
		tvForget.setOnClickListener(this);
	}

	public interface OnItemClickListener{
		/** ���õ��ȷ�ϰ�ťʱ�����ӿ� */
		public void onClickOKPop();
	}

	/**���ü���*/
	public void setOnItemClickListener(OnItemClickListener listener){
		this.listener=listener;
	}


	//��popWindow��ʧʱ��Ӧ
	@Override
	public void onDismiss() {
		setBackgroundBlack(backgroundView, 1);
		popupWindow.dismiss();
	}


	/**������ʾ��λ��*/  
	public void showAsDropDown(View position){
		popupWindow.showAtLocation(position, Gravity.BOTTOM, 0,  0);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		setBackgroundBlack(backgroundView, 0);
	}


	/** ���Ʊ����䰵 0�䰵 1���� */
	private void setBackgroundBlack(View view, int what) {
		switch (what) {
		case 0:
			view.setVisibility(View.VISIBLE);
			break;
		case 1:
			view.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_cance:
			onDismiss();
			break;
		case R.id.tv_forgetPwd:
			Toast.makeText(context, "��������", 1).show();
		
			break;
		default:
			break;
		}
	}

	//���ü����������ڵ�6λ������ɺ󴥷�
	public void setOnFinishInput(final OnPwdInputFinishedListener pass) {
		tvList[5].addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.toString().length() == 1) {
					strPassword = "";     //ÿ�δ�����Ҫ�Ƚ�strPassword�ÿգ������»�ȡ��������������ɾ����������ɻ���
					for (int i = 0; i < 6; i++) {
						strPassword += tvList[i].getText().toString().trim();
					}
					pass.onFinish(strPassword);    //�ӿ���Ҫʵ�ֵķ������������������ɺ����Ӧ�߼�
				}
			}
		});
	}

	/* ��ȡ��������� */
	public String getStrPassword() {
		return strPassword;
	}


	private void setView() {
		/* ��ʼ����ť��Ӧ����ʾ������ */
		for (int i = 1; i < 13; i++) {
			Map<String, String> map = new HashMap<String, String>();
			if (i < 10) {
				map.put("name", String.valueOf(i));
			} else if (i == 10) {
				map.put("name", "");
			} else if (i == 11) {
				map.put("name", String.valueOf(0));
			} else if (i == 12) {
				map.put("name", "<");
			}
			valueList.add(map);
		}
	}
}