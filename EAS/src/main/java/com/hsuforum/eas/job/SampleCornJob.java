package com.hsuforum.eas.job;

import java.util.UUID;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.hsuforum.eass.entity.schedule.Job;
import com.hsuforum.eass.entity.schedule.JobExecuteLog;
import com.hsuforum.eass.entity.schedule.JobExecuteStatus;
import com.hsuforum.eass.entity.schedule.JobSystem;
import com.hsuforum.eass.service.JobExecuteLogJpaService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class SampleCornJob  extends QuartzJobBean {
	@Autowired
	private JobExecuteLogJpaService jobRunningLogJpaService;
	@Autowired
	private JobSystem jobSystem;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    	log.info("SampleCornJob ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());

    	log.info("SampleCornJob exec!");

    	log.info("Next SampleCornJob scheduled @ {}", context.getNextFireTime());
    	//write job execute log
    	JobExecuteLog jobExecuteLog = new JobExecuteLog();
    	jobExecuteLog.setId(UUID.randomUUID().toString());
    	jobExecuteLog.setJob(Job.SampleCornJob_EAS);
    	jobExecuteLog.setJobExecuteStatus(JobExecuteStatus.Success);
    	jobRunningLogJpaService.save(jobExecuteLog);
    }
}