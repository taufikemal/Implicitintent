package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText=findViewById(R.id.website_edittext);
        mLocationEditText=findViewById(R.id.location_edittext);
        mShareEditText=findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view){
        String url=mWebsiteEditText.getText().toString();
        Uri webpage=Uri.parse(url);
        Intent intent=new Intent(Intent.ACTION_VIEW,webpage);
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this action!");
        }
    }

    public void openLocation(View view){
        String location = mLocationEditText.getText().toString();
        Uri addresUri = Uri.parse("geo:0,0?q=" + location);
        Intent intent=new Intent(Intent.ACTION_VIEW,addresUri);
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this action!");
        }
    }

    public void shareText(View view){
        String share=mShareEditText.getText().toString();
        ShareCompat.IntentBuilder
                .from(this)
                .setChooserTitle("Share text with :")
                .setText(share)
                .setType("text/plain")
                .startChooser();
    }
}