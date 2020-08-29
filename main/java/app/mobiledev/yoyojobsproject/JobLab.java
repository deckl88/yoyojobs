package app.mobiledev.yoyojobsproject;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JobLab {
    private static JobLab sJobLab;

    private List<Jobs> mJobs;


    public static JobLab getInstance(Context context) {
        if(sJobLab == null){
            sJobLab = new JobLab(context);
        }
        return sJobLab;
    }

    private JobLab(Context context) {
        mJobs = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            Jobs jobs = new Jobs();
            jobs.setmJobType("Job #" + i);
            jobs.setmHiring(i % 2 == 0);
            mJobs.add(jobs);
        }
    }
    public List<Jobs> getmJobs(){
        return mJobs;
    }
    public Jobs getJobs(UUID id){
        for (Jobs jobs : mJobs){
            if (jobs.getmId().equals(id)){
                return jobs;
            }
        }
        return null;
    }
}
