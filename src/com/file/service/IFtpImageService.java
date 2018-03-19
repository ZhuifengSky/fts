package com.file.service;

import java.io.InputStream;

/**
 * ftp文件上传下载服务
 * @author 
 *
 */
public interface IFtpImageService {

	/**
	 * 图片上传方法
	 * @param useOriginName
	 * @param stream
	 * @param path
	 * @param suffix
	 * @return
	 */
	public String uploadImage(Boolean useOriginName,String filName,String suffix,InputStream stream);
	
	
	/**
	 * 图片上传方法
	 * @param useOriginName
	 * @param originFilePath
	 * @param path
	 * @param suffix
	 * @return
	 */
	public String uploadImage(Boolean useOriginName,String filName,String suffix,String originFilePath);
	
	
	/**
	 * 图片下载方法
	 * @param fileName
	 * @param path
	 * @param savePath
	 * @return
	 */
	public String downLoadImage(String path,String fileName,String savePath);
	
}
