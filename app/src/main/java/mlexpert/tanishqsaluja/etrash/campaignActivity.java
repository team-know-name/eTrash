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
        arrayList.add(new Item("Newspaper","Rs. 9.0/kg" ));
        arrayList.add(new Item("Books","Rs. 6.0/kg" ));
        arrayList.add(new Item("Metal","Rs. 12.0/kg" ));
        final campaignAdapter adapter = new campaignAdapter(arrayList);
        recyclerView.setAdapter(adapter);

    }

}
