package com.zjpavt.job;


import com.zjpavt.util.Consts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ThinkPad
 */
public class OfflineCommonJobEntity extends JobEntity {
    private static final String COMMON_PATTERN = "([0-9]{13}):([0-9,a-f]{4})";
    private String commonToHex;
    private String timer;
    public OfflineCommonJobEntity(){
        this.priorty = JobTypeEnum.OFFLINE_COMMON_STATE.getState();
    }
    public OfflineCommonJobEntity(String common){
        this();
        this.common = common;
    }

    @Override
    public int[] getLoopCommon() {
        int[] loopStatusBinary = new int[Consts.DEFAULT_LOOP_NUM];
        Pattern pattern = Pattern.compile(COMMON_PATTERN);
        Matcher matcher = pattern.matcher(this.common);
        if (matcher.matches()) {
            String hexCommon = matcher.group(2);
        }
        return loopStatusBinary;
    }

    @Override
    public void addQuertz() {

    }

    @Override
    public void execute() {
        super.execute();
    }


}
