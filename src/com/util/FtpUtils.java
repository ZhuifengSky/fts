package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.slf4j.impl.Log4jLoggerFactory;

public class FtpUtils {
    //ftp��������ַ
    public static String hostName = EnvironmentUtil.getInstance().getPropertyValue("ftp.host");
    //ftp�������˿ں�
    public static String port = EnvironmentUtil.getInstance().getPropertyValue("ftp.port");
    //ftp��¼�˺�
    public static String username = EnvironmentUtil.getInstance().getPropertyValue("ftp.username");
    //ftp��¼����
    public static String password = EnvironmentUtil.getInstance().getPropertyValue("ftp.password");
    
    public static FTPClient ftpClient = null;
    
    public static Logger logger = Logger.getLogger(FtpUtils.class);
    /**
     * ��ʼ��ftp������
     */
    public static void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            logger.info("connecting...ftp������:"+hostName+":"+port); 
            ftpClient.connect(hostName, Integer.parseInt(port)); //����ftp������
            ftpClient.login(username, password); //��¼ftp������
            int replyCode = ftpClient.getReplyCode(); //�Ƿ�ɹ���¼������
            if(!FTPReply.isPositiveCompletion(replyCode)){
                logger.info("connect failed...ftp������:"+hostName+":"+port); 
            }
            logger.info("connect success...ftp������:"+hostName+":"+port); 
        }catch (MalformedURLException e) { 
           e.printStackTrace(); 
        }catch (IOException e) { 
           e.printStackTrace(); 
        } 
    }

    /**
    * �ϴ��ļ�
    * @param pathname ftp���񱣴��ַ
    * @param fileName �ϴ���ftp���ļ���
    *  @param originfilename ���ϴ��ļ������ƣ����Ե�ַ�� * 
    * @return
    */
    public static boolean uploadFile(String path, String fileName, String originFilePath){
        boolean flag = false;
        InputStream inputStream = null;
        try{
            logger.info("��ʼ�ϴ��ļ�");
            inputStream = new FileInputStream(new File(originFilePath));
            initFtpClient();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            CreateDirecroty(path);
            ftpClient.makeDirectory(path);
            ftpClient.changeWorkingDirectory(path);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            logger.info("�ϴ��ļ��ɹ�");
        }catch (Exception e) {
            logger.info("�ϴ��ļ�ʧ��");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            } 
        }
        return flag;
    }
    /**
     * �ϴ��ļ�
     * @param pathname ftp���񱣴��ַ
     * @param fileName �ϴ���ftp���ļ���
     * @param inputStream �����ļ��� 
     * @return
     */
    public static boolean uploadFile(String path,String fileName,InputStream inputStream){
        boolean flag = false;
        try{
            logger.info("��ʼ�ϴ��ļ�");
            initFtpClient();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            CreateDirecroty(path);
            ftpClient.makeDirectory(path);
            ftpClient.changeWorkingDirectory(path);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            logger.info("�ϴ��ļ��ɹ�");
        }catch (Exception e) {
            logger.info("�ϴ��ļ�ʧ��");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            } 
        }
        return flag;
    }
    //�ı�Ŀ¼·��
     public static boolean changeWorkingDirectory(String directory) {
            boolean flag = true;
            try {
                flag = ftpClient.changeWorkingDirectory(directory);
                if (flag) {
                  logger.info("�����ļ���" + directory + " �ɹ���");

                } else {
                    logger.info("�����ļ���" + directory + " ʧ�ܣ���ʼ�����ļ���");
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return flag;
        }

    //�������Ŀ¼�ļ��������ftp�������Ѵ��ڸ��ļ����򲻴���������ޣ��򴴽�
    public static boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // ���Զ��Ŀ¼�����ڣ���ݹ鴴��Զ�̷�����Ŀ¼
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        logger.info("����Ŀ¼[" + subDirectory + "]ʧ��");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // �������Ŀ¼�Ƿ񴴽����
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

  //�ж�ftp�������ļ��Ƿ����    
    public static boolean existFile(String path) throws IOException {
            boolean flag = false;
            FTPFile[] ftpFileArr = ftpClient.listFiles(path);
            if (ftpFileArr.length > 0) {
                flag = true;
            }
            return flag;
        }
    //����Ŀ¼
    public static boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                logger.info("�����ļ���" + dir + " �ɹ���");

            } else {
                logger.info("�����ļ���" + dir + " ʧ�ܣ�");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    /** * �����ļ� * 
    * @param pathname FTP�������ļ�Ŀ¼ * 
    * @param filename �ļ����� * 
    * @param localpath ���غ���ļ�·�� * 
    * @return */
    public  static boolean downloadFile(String pathName, String fileName, String localpath){ 
        boolean flag = false; 
        OutputStream os=null;
        try { 
            logger.info("��ʼ�����ļ�");
            initFtpClient();
            //�л�FTPĿ¼ 
            ftpClient.changeWorkingDirectory(pathName); 
            FTPFile[] ftpFiles = ftpClient.listFiles(); 
            for(FTPFile file : ftpFiles){ 
                if(fileName.equalsIgnoreCase(file.getName())){ 
                    File localFile = new File(localpath + "/" + file.getName()); 
                    os = new FileOutputStream(localFile); 
                    ftpClient.retrieveFile(file.getName(), os); 
                    os.close(); 
                } 
            } 
            ftpClient.logout(); 
            flag = true; 
            logger.info("�����ļ��ɹ�");
        } catch (Exception e) { 
            logger.info("�����ļ�ʧ��");
            e.printStackTrace(); 
        } finally{ 
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            } 
        } 
        return flag; 
    }
    
    /** * ɾ���ļ� * 
    * @param pathname FTP����������Ŀ¼ * 
    * @param filename Ҫɾ�����ļ����� * 
    * @return */ 
    public static boolean deleteFile(String pathname, String filename){ 
        boolean flag = false; 
        try { 
            logger.info("��ʼɾ���ļ�");
            initFtpClient();
            //�л�FTPĿ¼ 
            ftpClient.changeWorkingDirectory(pathname); 
            ftpClient.dele(filename); 
            ftpClient.logout();
            flag = true; 
            logger.info("ɾ���ļ��ɹ�");
        } catch (Exception e) { 
            logger.info("ɾ���ļ�ʧ��");
            e.printStackTrace(); 
        } finally {
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
        }
        return flag; 
    }
    
}
