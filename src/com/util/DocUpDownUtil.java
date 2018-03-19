package com.util;

import java.io.InputStream;
import java.util.Date;

public class DocUpDownUtil {

	//�ĵ��ϴ�Ŀ¼
    public static String docPath = EnvironmentUtil.getInstance().getPropertyValue("doc.savePath");
	
    /**
     * �ĵ��ϴ�
     * @param useOriginalFileName
     * @param filName
     * @param suffix
     * @param originFilePath ����·��
     * @return
     */
	public static boolean upDoc(Boolean useOriginalFileName,String fileName, String suffix,String originFilePath){
		boolean flag = false;
		String folderName = DateUtil.format(new Date(), DateUtil.DATE_NO);
		String path = docPath + "/" + folderName;
		if (useOriginalFileName) {
			flag = FtpUtils.uploadFile(path, fileName, originFilePath);			
		}else{
			fileName = DateUtil.format(new Date(), DateUtil.DATE_NO)
					+ RandomStrUtil.getCharAndNumr(5)+"."+suffix;
			flag = FtpUtils.uploadFile(path, fileName, originFilePath);
		}
		return flag;		
	}
	
	/**
	 * �ĵ��ϴ�
	 * @param useOriginalFileName
	 * @param fileName
	 * @param suffix
	 * @param inputStream
	 * @return
	 */
	public static boolean upDoc(Boolean useOriginalFileName,String fileName, String suffix,InputStream inputStream){
		boolean flag = false;
		String folderName = DateUtil.format(new Date(), DateUtil.DATE_NO);
		String path = docPath + "/" + folderName;
		if (useOriginalFileName) {
			flag = FtpUtils.uploadFile(path, fileName, inputStream);			
		}else{
			fileName = DateUtil.format(new Date(), DateUtil.DATE_NO)
					+ RandomStrUtil.getCharAndNumr(5)+"."+suffix;
			flag = FtpUtils.uploadFile(path, fileName, inputStream);
		}
		return flag;		
	}
	
	public static void main(String[] args) {
		DocUpDownUtil.upDoc(false,"dd.doc", "png", "D://3.0//dd.doc");
	}
}
