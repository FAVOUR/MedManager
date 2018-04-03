package com.example.olijefavour.med_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MonthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
    }

    public void january(View view) {
        Intent intent = new Intent(MonthActivity.this, MonthsActivity.class);
        intent.putExtra("month", 2);
        startActivity(intent);
    }

    public void february(View view) {
    }

    public void march(View view) {
    }

    public void april(View view) {
    }

    public void may(View view) {
    }

    public void june(View view) {
    }

    public void july(View view) {

    }

    public void august(View view) {
    }

    public void september(View view) {
    }

    public void october(View view) {
    }

    public void november(View view) {
    }

    public void december(View view) {
    }
}
