package com.file.service;

import java.util.List;
import java.util.Map;

/**
 * 服务接口
 * @author mozz
 */
public interface HessianAPI {
	
	/**
	 * 从业人员信息验证
	 * @return
	 */
	public Map<String,Object> userInfo(Map<String,byte[]> req);
	
	/**
	 * 从业人员历年学时情况
	 * @return
	 */
	public Map<String,Object> periodInfo(Map<String,byte[]> req);
	
	/**
	 * 接收网上教育学习情况
	 * @return
	 */
	public Map<String,Object> education(Map<String,Object> req);
	
	
	
	/**
	 * 心跳测试，测试服务是否运行中
	 * @return
	 */
	public Map<String,Object> heartbeat(Map<String,byte[]> req);
	
}
