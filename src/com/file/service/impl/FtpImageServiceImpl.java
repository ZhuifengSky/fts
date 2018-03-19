package com.file.service.impl;

import java.io.InputStream;

import com.file.service.IFtpImageService;
import com.util.ImageUpDownUtil;

public class FtpImageServiceImpl implements IFtpImageService {

	
	@Override
	public String uploadImage(Boolean useOriginName,String fileName, String suffix,InputStream inputStream) {
		 return ImageUpDownUtil.upImage(useOriginName, fileName, suffix, inputStream);
	}

	@Override
	public String uploadImage(Boolean useOriginName,String fileName, String suffix,String originFilePath) {
		 return ImageUpDownUtil.upImage(useOriginName, fileName, suffix, originFilePath);
	}
	
	@Override
	public String downLoadImage(String path,String fileName,String savePath) {
		System.out.println("传递过来的参数"+fileName+"---"+path);
		String msg = "此方法正在执行！";
		System.out.println("测试测试额");
		return msg;
	}
	

}
