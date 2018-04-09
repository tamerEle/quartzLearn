package zjpavt.timer;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

public class TriggerUtil {
    public TriggerUtil(){

    }
    public static CronTrigger get(){
        String str = "";
        return TriggerBuilder.newTrigger().
                withSchedule(CronScheduleBuilder.cronSchedule(str))
                .build();
    }
}
