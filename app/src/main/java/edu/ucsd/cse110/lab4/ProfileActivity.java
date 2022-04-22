package edu.ucsd.cse110.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        loadProfile();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        saveProfile();

    }

    public void loadProfile(){
        SharedPreferences perferences = getPreferences(MODE_PRIVATE);
        String name=perferences.getString("name","");

        String status=perferences.getString("status","");

        TextView nameView=findViewById(R.id.name_textView);
        nameView.setText(name);
        TextView statusView=findViewById(R.id.status_textView);
        statusView.setText(status);

    }

    public void saveProfile(){
        SharedPreferences perferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor=perferences.edit();

        TextView nameView=findViewById(R.id.name_textView);
        TextView statusView=findViewById(R.id.status_textView);
        editor.putString("name",nameView.getText().toString());
        editor.putString("status",statusView.getText().toString());

        editor.apply();

    }


    public void onExitClicked(View view) {
        finish();
    }
}