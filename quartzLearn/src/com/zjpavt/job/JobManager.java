package com.zjpavt.job;

/**
 * @author  zyc
 */
public class JobManager {
    private  JobManager(){
    }
    private static JobManager jobManager;
    static {
        jobManager = new JobManager();
    }
    public static JobManager getJobManager() {
        return jobManager;
    }

}
