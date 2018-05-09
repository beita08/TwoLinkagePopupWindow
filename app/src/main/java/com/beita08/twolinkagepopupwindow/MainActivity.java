package com.beita08.twolinkagepopupwindow;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResult;
    private TextView tvClassFirst;
    private TextView tvClassSecond;

    private int firstPosition = 0;
    private ListView    firstListView;
    private ListView    secondListView;
    private PopupWindow firstPopupWindow;
    private PopupWindow secondPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tv_result);
        tvClassFirst = (TextView) findViewById(R.id.tv_class_first);
        tvClassSecond = (TextView) findViewById(R.id.tv_class_second);
        tvClassFirst.setOnClickListener(this);
        tvClassSecond.setOnClickListener(this);

        firstListView = new ListView(this);
        firstListView.setBackgroundResource(R.drawable.popup_bg);
        firstListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                firstPosition = position;
                tvClassFirst.setText(Constants.firstClassStr[firstPosition]);
                tvClassSecond.setText(Constants.secondClassStr[firstPosition][0]);
                tvResult.setText(Constants.firstClassStr[firstPosition] + " - " + Constants.secondClassStr[firstPosition][0]);
                firstPopupWindow.dismiss();
            }
        });

        secondListView = new ListView(this);
        secondListView.setBackgroundResource(R.drawable.popup_bg);
        secondListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvClassSecond.setText(Constants.secondClassStr[firstPosition][position]);
                tvResult.setText(Constants.firstClassStr[firstPosition] + " - " + Constants.secondClassStr[firstPosition][position]);
                secondPopupWindow.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_class_first:
                showFirstPopup();
                break;
            case R.id.tv_class_second:
                showSecondPopup();
                break;
            default:
                break;
        }
    }

    private void showFirstPopup() {

        if (firstPopupWindow == null) {//多次点击时,不必重复创建对象
            firstPopupWindow = new PopupWindow(firstListView, tvClassFirst.getWidth(), UiUtils.dp2px(this, 400), true);
            firstPopupWindow.setBackgroundDrawable(new ColorDrawable());
        }
        firstPopupWindow.showAsDropDown(tvClassFirst);
        firstListView.setAdapter(new MyAdapter(Constants.firstClassStr));
    }

    private void showSecondPopup() {

        if (secondPopupWindow == null) {
            secondPopupWindow = new PopupWindow(secondListView, tvClassSecond.getWidth(), UiUtils.dp2px(this, 400), true);
            secondPopupWindow.setBackgroundDrawable(new ColorDrawable());
        }
        secondPopupWindow.showAsDropDown(tvClassSecond);
        secondListView.setAdapter(new MyAdapter(Constants.secondClassStr[firstPosition]));
    }


    private class MyAdapter extends BaseAdapter {

        private String[] mArray;

        //采用构造方法将要展示的数据的数组传进来,这样这个适配器就可以共用了
        public MyAdapter(String[] strArray) {
            this.mArray = strArray;
        }

        @Override
        public int getCount() {
            return mArray.length;
        }

        @Override
        public String getItem(int position) {
            return mArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_popupwindow, null);

                holder = new ViewHolder();
                holder.tvItem = (TextView) convertView.findViewById(R.id.tv_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvItem.setText(getItem(position));

            return convertView;
        }
    }

    static class ViewHolder {
        public TextView tvItem;
    }
}
