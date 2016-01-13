package xyz.springabc.web.helper;

import java.text.DecimalFormat;

public class Status {
	
	private long ramFree;
	
	private long ramTotal;
	
	private long ramUsed;
	
	private int cpuCore;
	
	private String cpuLoad;
	
	private Runtime runtime;
	
	private static final DecimalFormat df = new DecimalFormat("#.##");
	
	public Status(){
		this.runtime=Runtime.getRuntime();
	}
	
	public long getRamFree() {
		return runtime.freeMemory()/1024/1024;
	}

	public long getRamTotal() {
		return runtime.maxMemory()/1024/1024;
	}

	public long getRamUsed() {
		return runtime.totalMemory()/1024/1024;
	}

	public int getCpuCore() {
		return runtime.availableProcessors();
	}

	public String getCpuLoad() {
		return df.format(2333.00);
	}

}
