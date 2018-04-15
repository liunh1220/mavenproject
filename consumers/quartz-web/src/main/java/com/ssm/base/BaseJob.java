package com.ssm.base;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;

@DisallowConcurrentExecution
public interface BaseJob extends Job{

}
