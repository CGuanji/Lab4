package edu.ucsd.cse110.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Utilities.Utilties;


public class CounterActivity extends AppCompatActivity {
    private int maxCount;
    private ExecutorService backgroundThreadExecutor= Executors.newSingleThreadExecutor();
    private Future<Void> future;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        Bundle extras=getIntent().getExtras();
        this.maxCount=extras.getInt("max_count");

        TextView counterView= findViewById(R.id.counter_view);
      //  counterView.setText(String.valueOf(this.maxCount));

        this.future = backgroundThreadExecutor.submit(()->{
            int count=0;
          do{
              final int countCopy = count;
              runOnUiThread( ()->{
                  counterView.setText(String.valueOf(countCopy));
              });

              count++;
              Thread.sleep(500);

          }while(count<maxCount+1);
           // Utilties.showAlert(this,"Count is finished!");
            runOnUiThread( ()->{
                 Utilties.showAlert(this,"Count is finished!");
            });

            return null;
        });


    }

    public void Back(View view) {
        this.future.cancel(true);

        finish();
    }
}