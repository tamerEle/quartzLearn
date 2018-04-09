package com.zjpavt.job;

public abstract  class JobEntity {
    protected int priorty;
    protected String common;
    public void execute(){}
    public abstract void addQuertz();
    public abstract int[] getLoopCommon();
 }
