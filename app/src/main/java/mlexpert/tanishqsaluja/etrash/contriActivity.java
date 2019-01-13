package mlexpert.tanishqsaluja.etrash;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class contriActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    android.support.v7.widget.Toolbar toolbar;
    TextView textView;
    ArrayList<Contribution> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution);

        toolbar = findViewById(R.id.bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = findViewById(R.id.tv);
        Typeface tf = Typeface.createFromAsset(getAssets(), "qb.otf");
        textView.setTypeface(tf);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        list.add(new Contribution(R.drawable.tree, "Trees Saved(No's)", 0));
        list.add(new Contribution(R.drawable.energy, "Energy Saved(Kwh)", 0));
        list.add(new Contribution(R.drawable.water, "Water Saved(Ltr)", 0));
        list.add(new Contribution(R.drawable.oil, "Oil Saved(Ltr)", 0));
        list.add(new Contribution(R.drawable.landscape, "Landscape Saved(km2)", 0));

        greenAdapter adapter = new greenAdapter(list);
        recyclerView.setAdapter(adapter);

    }
}
