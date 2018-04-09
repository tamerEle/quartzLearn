package com.zjpavt.job;

public enum JobTypeEnum {
    OFFLINE_COMMON_STATE(1),
    TODAYTIMER_COMMON_STATE(2),
    EXECUTOR_P_LIGHTINTENSITY_COMMAND_STATE(3),
    K_NOTWITHTIME_COMMAND_STATE(4),
    EXECUTOR_K_WITHTIME_COMMAND_STATE(5);
    private  int state;
    private JobTypeEnum(int state){
        this.state = state;
    }
    public int getState(){
        return this.state;
    }
}
