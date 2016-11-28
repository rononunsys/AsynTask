package com.android.curso.asyntask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private boolean activate;
    private ProgressTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        final Button start = (Button) findViewById(R.id.button);
        Button reset = (Button) findViewById(R.id.button2);

        progressBar.setProgress(0);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activate){
                    task.cancel(true);
                    task.isCancelled();
                    start.setText("Start");
                    activate = false;
                }else{
                    int progress = progressBar.getProgress();
                    task = new ProgressTask(MainActivity.this);
                    task.execute(progress);
                    activate = true;
                    start.setText("Pause");
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(0);
            }
        });
    }

    public void setProgres(int progress) {
        progressBar.setProgress(progress);
    }

    public void sendMessage() {
        Toast.makeText(this,"Completado", Toast.LENGTH_LONG);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(task != null){
            task.cancel(true);
        }
    }
}
