package com.file.service;

import java.io.InputStream;

/**
 * ftp�ļ��ϴ����ط���
 * @author 
 *
 */
public interface IFtpImageService {

	/**
	 * ͼƬ�ϴ�����
	 * @param useOriginName
	 * @param stream
	 * @param path
	 * @param suffix
	 * @return
	 */
	public String uploadImage(Boolean useOriginName,String filName,String suffix,InputStream stream);
	
	
	/**
	 * ͼƬ�ϴ�����
	 * @param useOriginName
	 * @param originFilePath
	 * @param path
	 * @param suffix
	 * @return
	 */
	public String uploadImage(Boolean useOriginName,String filName,String suffix,String originFilePath);
	
	
	/**
	 * ͼƬ���ط���
	 * @param fileName
	 * @param path
	 * @param savePath
	 * @return
	 */
	public String downLoadImage(String path,String fileName,String savePath);
	
}
