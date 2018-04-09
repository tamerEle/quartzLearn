package zjpavt.timer;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JobSchedule {
    private static Logger logger = LoggerFactory.getLogger(JobSchedule.class);
    public static void initJobSchedule() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(JobEntity.class)
                .withDescription("job desctiption")
                .withIdentity("job name","group name")
                .build();
        long time = System.currentTimeMillis() + 3000;
        Date startTime = new Date();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger name","trigger group name")
                .withDescription("trigger description")
                .startAt(startTime)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * *  * ?"))
                .build();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
    }

    public static void  main(String[] args){
        try {
            initJobSchedule();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

  /*  public class JobEntity implements JobEntity {
        private Logger logger = LoggerFactory.getLogger(JobEntity.class);
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            logger.info("info");
        }
    }*/
}
