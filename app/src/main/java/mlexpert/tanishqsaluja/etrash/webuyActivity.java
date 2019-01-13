package mlexpert.tanishqsaluja.etrash;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class webuyActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webuy_activity);
        toolbar = findViewById(R.id.bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = findViewById(R.id.tv);
        Typeface tf = Typeface.createFromAsset(getAssets(), "qb.otf");
        textView.setTypeface(tf);
    }
}
