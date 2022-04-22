package edu.ucsd.cse110.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Optional;

import Utilities.Utilties;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLanuchProfileClicked(View view) {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }


    public void onShowCounterClicked(View view) {
        Intent intent = new Intent(this,CounterActivity.class);
        TextView maxCountView=findViewById(R.id.max_count_view);
        String maxCountStr=maxCountView.getText().toString();

        Optional<Integer> maybeMaxCount= Utilties.parseCount(maxCountStr);

        if (!maybeMaxCount.isPresent()) {
            Utilties.showAlert(this,"That isn't a number!");
            return;
        }

        int maxCount=maybeMaxCount.get();
        if(maxCount<=0){
            Utilties.showAlert(this,"Pls enter a positive number!");
            return;
        }
       // Utilties.showAlert(this,String.format("I got the number %d",maxCount));
         intent.putExtra("max_count",maxCount);
        startActivity(intent);
     //   Utilties.showAlert(this,"Count is finished!");

    }


}