package mlexpert.tanishqsaluja.etrash;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class ratingActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        toolbar = findViewById(R.id.bar);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.add(new Item("Newspaper","Rs. 9.0/kg" ));
        arrayList.add(new Item("Books","Rs. 6.0/kg" ));
        arrayList.add(new Item("Metal","Rs. 12.0/kg" ));
        arrayList.add(new Item("Plastic","Rs. 10.0/kg" ));
        arrayList.add(new Item("Cardboard","Rs. 7.5/kg" ));
        arrayList.add(new Item("Fibre","Rs. 7.0/kg" ));
        arrayList.add(new Item("E-Waste","Rs. 10.0/kg" ));
        arrayList.add(new Item("Glass bottle","Rs. 0.5/piece" ));
        arrayList.add(new Item("Steel","Rs. 23.0/kg" ));
        arrayList.add(new Item("Newspaper","Rs. 5.0/kg" ));


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String[] states = {"Delhi", "Mumbai", "Orissa", "Indore", "Ahmedabad"};


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SELECT THE CITY");
        builder.setItems(states, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                if (which == 0) {
                    Toast.makeText(ratingActivity.this, "Delhi selected", Toast.LENGTH_SHORT).show();
                    final rateAdapter adapter = new rateAdapter(arrayList);
                    recyclerView.setAdapter(adapter);
                } else if (which == 1) {
                    Toast.makeText(ratingActivity.this, "Mumbai selected", Toast.LENGTH_SHORT).show();
                } else if (which == 2) {
                    Toast.makeText(ratingActivity.this, "Orissa selected", Toast.LENGTH_SHORT).show();
                } else if (which == 3) {
                    Toast.makeText(ratingActivity.this, "Indore selected", Toast.LENGTH_SHORT).show();
                } else if (which == 4) {
                    Toast.makeText(ratingActivity.this, "Ahmedabad selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }
}
