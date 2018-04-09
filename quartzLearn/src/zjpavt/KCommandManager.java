package zjpavt;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Pattern;

public class KCommandManager {
    private final KCommandBean kCommandWithTime;
    private final KCommandBean kCommandWithoutTime;
    private static final Pattern K_PATTERN = Pattern.compile("^[0-1]+$");
    private static final KCommandManager kCommandManager = new KCommandManager();
    private  KCommandManager() {
        kCommandWithTime = new KCommandBean();
        kCommandWithoutTime = new KCommandBean();
        initKCommandData();
    }
    private void initKCommandData() {
        String command;
        long endTime;
        try {
            command = "kCommandWithTime";
            endTime = 123;
            Properties prop = new Properties();
            prop.load(new InputStream() {
                @Override
                public int read() throws IOException {
                    return 0;
                }
            });
        }catch (IOException | NumberFormatException e){
            command = "kCommandWithTime";
            endTime = 123;
        }
        synchronized (kCommandWithTime){
            this.kCommandWithTime.setCommand(command);
            this.kCommandWithTime.endTime = endTime;
        }

        try {
            command = "kCommandWithoutTime";
            endTime = 1232;
            Properties prop = new Properties();
            prop.load(new InputStream() {
                @Override
                public int read() throws IOException {
                    return 0;
                }
            });
        }catch (IOException|NumberFormatException e){

        }
        synchronized (kCommandWithTime){
            this.kCommandWithoutTime.command = command;
            this.kCommandWithoutTime.endTime = endTime;
        }
    }

    public boolean saveKCommandWithTime(String command, Long endTime){
        if(command == null || endTime == null) {
            return false;
        }
        boolean result = false;
        return  result;
    }

    private void storeKCommandWithTime(String command,long endTime) throws  IOException {
        synchronized (this.kCommandWithTime) {

        }
    }

    public class  KCommandBean{
        public KCommandBean(String command, long endTime) {
            this.command = command;
            this.endTime = endTime;
        }
        public KCommandBean() {
        }
        public KCommandBean getkCommandWithTime() {
            return kCommandWithTime;
        }

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        private String command;
            private long endTime;
    }
}
