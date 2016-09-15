package xyz.springabc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.springabc.web.helper.Qiniu;
import xyz.springabc.web.helper.Qiniu.QiniuResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class UploadFileServ {
	
	@Autowired
	private Qiniu qiniu;
	
	public QiniuResponse upload(MultipartFile file) throws Exception{
		Date date = new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd-");
		String key = dateFormat.format(date)+UUID.randomUUID().toString();
		return qiniu.upload(file.getBytes(), key);
	}
}
