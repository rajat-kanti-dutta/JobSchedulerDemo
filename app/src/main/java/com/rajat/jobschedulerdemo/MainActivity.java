package com.rajat.jobschedulerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//some notes on JobScheduler
    //https://medium.com/@kiitvishal89/android-jobscheduler-schedule-your-jobs-like-a-master-cfa0d80e5f10
    //https://betterprogramming.pub/android-jobscheduler-whats-inside-exploring-the-internals-51d301059d55
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = (Button)findViewById(R.id.buttonStart);
        Button endButton = (Button)findViewById(R.id.buttonStop);
        startButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                scheduleJob(v);
            }
        });
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelJob(v);
            }
        });


    }
    public void scheduleJob(View v){
        ComponentName componentName = new ComponentName(this,MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(451,componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(jobInfo);
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d("JOBSERVICE","Job Scheduled");
        }
        else{
            Log.d("JOBSERVICE","Job Scheduling Failed!!!!!");
        }



    }
    public void cancelJob(View v){
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(451);
        Log.d("JOBSERVICE","Job Cancelled");
    }
}