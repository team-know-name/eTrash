package mlexpert.tanishqsaluja.etrash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class campaignActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);
        toolbar = findViewById(R.id.bar);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.add(new Item("Plain to Cable","12/02/2019" ));
        arrayList.add(new Item("Clean to Green","20/02/2019" ));
        arrayList.add(new Item("Swatchch Bharat Day","02/10/2019" ));
        final campaignAdapter adapter = new campaignAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);

    }

}
