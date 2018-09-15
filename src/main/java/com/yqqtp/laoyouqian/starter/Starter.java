package com.yqqtp.laoyouqian.starter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yqqtp.laoyouqian.config.AppConfig;
import com.yqqtp.laoyouqian.engine.InitData;
import com.yqqtp.laoyouqian.gateway.ctp.CtpGateway;
import com.yqqtp.laoyouqian.gateway.ctp.DataCache;
import com.yqqtp.laoyouqian.task.CreateBarTask;
import com.yqqtp.laoyouqian.task.SaveBarTask;
import com.yqqtp.laoyouqian.task.SaveTickTask;
import com.yqqtp.laoyouqian.task.UpdateBarTask;

public class Starter {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		DataCache.SYMBOLS =new String[]{"au1812","sc1809","ag1812"};
		
		ctx.getBean(InitData.class).initData();
		
		SaveTickTask task = ctx.getBean(SaveTickTask.class);
		Thread thead = new Thread(task);
		thead.start();
		
		SaveBarTask task2 = ctx.getBean(SaveBarTask.class);
		Thread thead2 = new Thread(task2);
		thead2.start();
		
		UpdateBarTask task3 = ctx.getBean(UpdateBarTask.class);
		Thread thead3 = new Thread(task3);
		thead3.start();
		
		CreateBarTask task4 = ctx.getBean(CreateBarTask.class);
		Thread thead4 = new Thread(task4);
		thead4.start();
		
		CtpGateway cg = CtpGateway.getInstance();
		cg.connect();
		Thread.sleep(1000);
		cg.subscribeDupl(DataCache.SYMBOLS);
	}
}
