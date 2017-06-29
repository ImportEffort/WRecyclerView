package com.example.wsj.wrecyclerview.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.wsj.wrecyclerview.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addHeader = (Button) findViewById(R.id.head_footer);
        Button addFooter = (Button) findViewById(R.id.section_layout);
        addHeader.setOnClickListener(this);
        addFooter.setOnClickListener(this);
        Button removeHeader = (Button) findViewById(R.id.multi_layout);
        Button removeFooter = (Button) findViewById(R.id.empty_layout);
        removeHeader.setOnClickListener(this);
        removeFooter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_footer:
                gotoHeadAFooterActivity();
                break;
            case R.id.multi_layout:
                gotoMultiActivity();
                break;
            case R.id.section_layout:
                gotoSectionActivity();
                break;
            case R.id.empty_layout:
                gotoEmptyActivity();
                break;
        }
    }

    private void gotoEmptyActivity() {
        startActivity(new Intent(this, EmptyActivity.class));
    }

    private void gotoSectionActivity() {
        startActivity(new Intent(this, SectionActivity.class));
    }

    private void gotoMultiActivity() {
        startActivity(new Intent(this, MutilActivity.class));
    }

    private void gotoHeadAFooterActivity() {
        startActivity(new Intent(this, HeadFooterActivity.class));
    }
}
