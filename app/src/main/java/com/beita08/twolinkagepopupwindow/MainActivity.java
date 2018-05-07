package com.beita08.twolinkagepopupwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvClassFirst;
    private TextView tvClassSecond;

    /*
    private ListView    firstListView;
    private ListView    secondListView;
    */

    //private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvClassFirst = (TextView) findViewById(R.id.tv_class_first);
        tvClassSecond = (TextView) findViewById(R.id.tv_class_second);
        tvClassFirst.setOnClickListener(this);
        tvClassSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_class_first:

                break;
            case R.id.tv_class_second:

                break;
            default:
                break;
        }
    }
}
