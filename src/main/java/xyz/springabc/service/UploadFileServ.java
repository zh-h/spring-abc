package xyz.springabc.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.common.QiniuException;

import antlr.collections.List;
import xyz.springabc.web.helper.Qiniu;
import xyz.springabc.web.helper.Qiniu.QiniuResponse;

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
