package com.file.service;

import java.io.InputStream;
import java.io.OutputStream;

public interface IFileUpService {

	/**
	 * 图片上传方法
	 * @param stream
	 * @param path
	 * @param suffix
	 * @return
	 */
	public String uploadFile(String filName,String suffix,InputStream stream);
	
	
	/**
	 * 文件下载方法
	 * @param fileName
	 * @param path
	 * @return
	 */
	public String downLoadFile(String fileName,String path);
	
}
