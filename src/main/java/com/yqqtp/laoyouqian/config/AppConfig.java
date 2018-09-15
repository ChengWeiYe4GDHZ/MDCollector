package com.yqqtp.laoyouqian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import com.yqqtp.laoyouqian.engine.InitData;
import com.yqqtp.laoyouqian.task.CreateBarTask;
import com.yqqtp.laoyouqian.task.SaveBarTask;
import com.yqqtp.laoyouqian.task.SaveTickTask;
import com.yqqtp.laoyouqian.task.UpdateBarTask;

@Configuration
@ComponentScan(basePackages = "com.yqqtp.laoyouqian")
public class AppConfig {
	
	@Bean
	public SaveTickTask getTickSaveTask() {
		return new SaveTickTask();
	}
	
	@Bean
	public SaveBarTask getBarSaveTask() {
		return new SaveBarTask();
	}
	
	@Bean
	public UpdateBarTask getBarUpdateTask() {
		return new UpdateBarTask();
	}
	
	@Bean
	public CreateBarTask getCreateBarTask() {
		return new CreateBarTask();
	}
	
	@Bean
	public InitData getInitData() {
		return new InitData();
	}
}
