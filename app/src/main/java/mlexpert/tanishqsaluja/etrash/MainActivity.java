package mlexpert.tanishqsaluja.etrash;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    TextToSpeech textToSpeech;
    String[] arr = {"cardboard", "paper", "metal", "plastic"};
    android.support.v7.widget.Toolbar mybar;
    private static int CAPTURE_IMAGE = 123;
    private FloatingActionButton fab;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseReference;
    private ImageView webuy, rates, classify, contribution, wallet, sell;
    private OkHttpClient okHttpClient;
    private Uri imageUri;

    String base = "https://hidden-woodland-45917.herokuapp.com";
    String route = "/fileupload";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        mybar = findViewById(R.id.mytoolbar);
        String text = "";
        setSupportActionBar(mybar);
        getSupportActionBar().setTitle(text);

        webuy = findViewById(R.id.webuy);
        rates = findViewById(R.id.rates);
        classify = findViewById(R.id.classify);
        contribution = findViewById(R.id.contribution);
        wallet = findViewById(R.id.wallet);
        sell = findViewById(R.id.sell);

        mStorageRef = FirebaseStorage.getInstance().getReference("images/");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("pic/");

        classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                //intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAPTURE_IMAGE);
                Log.e("TEST", "Image Taken.");
            }
        });

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                Log.e("TEST", map.get("generated_text"));
                Toast.makeText(MainActivity.this, "It is " + map.get("generated_text"), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        webuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, webuyActivity.class);
                startActivity(intent);
            }
        });

        contribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, contriActivity.class);
                startActivity(intent);
            }
        });

        rates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ratingActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            Toast.makeText(MainActivity.this, "Uploading....", Toast.LENGTH_SHORT).show();
            imageUri = data.getData();
            final String[] downloadURL = {""};

            final StorageReference fileref = mStorageRef.child("images/pic");
            fileref.putFile(imageUri).
                    addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();

                            fileref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        Uri downloadUri = task.getResult();
                                        String downloadUrl = downloadUri.toString();
                                        Toast.makeText(MainActivity.this, downloadUrl, Toast.LENGTH_SHORT).show();


                                        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
                                            @Override
                                            public void onInit(int status) {
                                                if (status == TextToSpeech.SUCCESS) {
                                                    // Make a multi-language model
                                                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                                                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                                        Log.e("error", "This Language is not supported");
                                                    } else {
                                                        Toast.makeText(MainActivity.this, "Output :" + "Plastic", Toast.LENGTH_SHORT).show();
                                                        textToSpeech.speak("Plastic", TextToSpeech.QUEUE_FLUSH, null);
                                                    }
                                                } else {
                                                    Log.e("error", "Initilization Failed!");
                                                }
                                            }

                                        });

                                        String[] states = {"Yes", "No"};


                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        builder.setTitle("Was I correct ?");
                                        builder.setItems(states, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // the user clicked on colors[which]
                                                if (which == 0) {
                                                    Toast.makeText(MainActivity.this, "I am glad", Toast.LENGTH_SHORT).show();
                                                } else if (which == 1) {
                                                    Toast.makeText(MainActivity.this, "I will improve next time.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                        builder.show();
                                    }

                                }
                            });

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage((int) progress + "% Uploaded...");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Error in uploading", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else

        {
            Toast.makeText(MainActivity.this, "Cannot Upload", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, startActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.accountsettings) {
            Intent settingIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingIntent);
        } else if (item.getItemId() == R.id.about) {
            Intent settingIntent = new Intent(MainActivity.this, aboutActivity.class);
            startActivity(settingIntent);
        }
        return true;
    }

}

