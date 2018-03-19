package com.util;

import java.io.InputStream;
import java.util.Date;

public class DocUpDownUtil {

	//文档上传目录
    public static String docPath = EnvironmentUtil.getInstance().getPropertyValue("doc.savePath");
	
    /**
     * 文档上传
     * @param useOriginalFileName
     * @param filName
     * @param suffix
     * @param originFilePath 绝对路径
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
	 * 文档上传
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
