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
		System.out.println("���ݹ����Ĳ���"+fileName+"---"+path);
		String msg = "�˷�������ִ�У�";
		System.out.println("���Բ��Զ�");
		return msg;
	}
	

}
