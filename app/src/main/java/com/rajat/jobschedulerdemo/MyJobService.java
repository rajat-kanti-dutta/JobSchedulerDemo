package com.rajat.jobschedulerdemo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MyJobService extends JobService {
    private boolean jobCancelled=false;


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("JOBSERVICE","OnStartJob");
        ourBackgroundJob(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("JOBSERVICE","OnStopJob");
        jobCancelled=true;
        return true;
    }

    private void ourBackgroundJob(JobParameters params) {

    new Thread(new Runnable(){

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                if(jobCancelled) {
                    return;
                }
                Log.d("JOBSERVICE", "Running:" + i);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Log.d("JOBSERVICE","Job Finished");
            jobFinished(params,false);
        }//run
        //
    }).start();
    }
}
