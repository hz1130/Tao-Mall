package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FTPtest {

	@Test
	public void testFtpClient() throws Exception {
		//创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		//创建ftp连接。默认是21端口
		ftpClient.connect("192.168.169.146", 21);
		//登录ftp服务器，使用应户名和密码
		ftpClient.login("ftpuser", "ftpuser");
		//上传文件
		//读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\晚渡\\Desktop\\新建文件夹1\\图片\\5d7fd65b83030ef1!400x400_big.jpg"));
		//设置上传路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/Pictures");
		//修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//第一个参数：服务器端文档名
		//第二个参数：上传文档的inputStream
		ftpClient.storeFile("Hello001.jpg", inputStream);
		//关闭连接
		ftpClient.logout();
	}
	
	@Test
	public void testFtpUtil() throws FileNotFoundException {
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\晚渡\\Desktop\\新建文件夹1\\图片\\5d7fd65b83030ef1!400x400_big.jpg"));
		FtpUtil.uploadFile("192.168.169.146", 21, "ftpuser", "ftpuser", "/home/ftpuser/Pictures","/2019/6/2" , "Hello001.jpg", inputStream);
	}
	
}
