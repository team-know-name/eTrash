package mlexpert.tanishqsaluja.etrash;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ratingActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        toolbar = findViewById(R.id.bar);
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
                    
                }else if (which == 1) {
                    Toast.makeText(ratingActivity.this, "Mumbai selected", Toast.LENGTH_SHORT).show();
                }else if (which == 2) {
                    Toast.makeText(ratingActivity.this, "Orissa selected", Toast.LENGTH_SHORT).show();
                }else if (which == 3) {
                    Toast.makeText(ratingActivity.this, "Indore selected", Toast.LENGTH_SHORT).show();
                }else if (which == 4) {
                    Toast.makeText(ratingActivity.this, "Ahmedabad selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }
}
