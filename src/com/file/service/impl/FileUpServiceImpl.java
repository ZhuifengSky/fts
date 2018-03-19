package com.file.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.slf4j.Logger;

import com.file.service.IFileUpService;
import com.util.DateUtil;
import com.util.EnvironmentUtil;
import com.util.RandomStrUtil;

public class FileUpServiceImpl implements IFileUpService {

	public static Logger logger;
	public static String path =EnvironmentUtil.getInstance().getPropertyValue("File.SaveDsk")+File.separatorChar + File.separatorChar+EnvironmentUtil.getInstance().getPropertyValue("File.SaveFileFolder");

	@Override
	public String uploadFile(String filName, String suffix,InputStream stream) {
		String fileName = DateUtil.format(new Date(), DateUtil.DATE)
				+ RandomStrUtil.getCharAndNumr(5);
		String folderName = DateUtil.format(new Date(), DateUtil.DATE);
		String savePath = path + File.separatorChar + File.separatorChar
				+ folderName + File.separatorChar + File.separatorChar
				+ fileName + "." + suffix;
		String folderPath = path + File.separatorChar + folderName;
		File file = new File(folderPath);
		if (!file.exists() && !file.isDirectory()) {
			logger.info("//不存在");
			file.mkdir();
		} else {
			logger.info("//目录存在");
		}
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(savePath);
			byte[] buffer = new byte[1024 * 1024];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
		} catch (FileNotFoundException e) {
			logger.error("上传文件时文件未找到！");
			e.printStackTrace();
			return "file up error";
		} catch (IOException e) {
			logger.error("文件上传抛出IO异常！");
			e.printStackTrace();
			return "file up error";
		} finally {
			try {
				fs.close();
				stream.close();
			} catch (IOException e) {
				logger.error("关闭相关流时抛出IO异常！");
				e.printStackTrace();
			}
		}
		 return savePath;
	}

	@Override
	public String downLoadFile(String fileName, String path) {
		System.out.println("传递过来的参数"+fileName+"---"+path);
		String msg = "此方法正在执行！";
		System.out.println("测试测试额");
		return msg;
	}
	

}
