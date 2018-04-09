package zjpavt.timer;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobEntity implements Job {
    private Logger logger = LoggerFactory.getLogger(JobEntity.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info(String.valueOf(System.currentTimeMillis()));
    }
}
