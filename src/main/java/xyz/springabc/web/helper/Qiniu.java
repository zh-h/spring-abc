package xyz.springabc.web.helper;

import java.io.File;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.google.gson.JsonArray;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Json;
import com.qiniu.util.StringMap;

public class Qiniu {
	private String accessKey;
	private String secretKey;
	private String space;
	private String domain;
	private Auth auth;
	private UploadManager uploadManager;

	public Qiniu(String accessKey,String secretKey,String space,String domain) {
		this.accessKey=accessKey;
		this.secretKey=secretKey;
		this.space=space;
		this.domain=domain;
		this.auth = Auth.create(accessKey, secretKey);
	}
	
	public Qiniu(){
		
	}

	private String getUpToken() {
		return auth.uploadToken("spring-abc", null, 3600, new StringMap().putNotEmpty("returnBody",
				"{\"key\": $(key), \"hash\": $(etag), \"width\": $(imageInfo.width), \"height\": $(imageInfo.height)}"));
	}

	public QiniuResponse upload(File file, String key) throws QiniuException {

			String token = getUpToken();
			Response res = uploadManager.put(file, key, token);
			QiniuResponse ret = res.jsonToObject(QiniuResponse.class);
			return ret;
	}
	
	public QiniuResponse upload(byte[] bytes, String key) throws QiniuException {
		String token = getUpToken();
		Response res = uploadManager.put(bytes, key, token);
		QiniuResponse ret = res.jsonToObject(QiniuResponse.class);
		ret.url="http://"+this.domain+"/"+ret.key;
		return ret;
}


	public UploadManager getUploadManager() {
		return uploadManager;
	}

	public void setUploadManager(UploadManager uploadManager) {
		this.uploadManager = uploadManager;
	}
	
	/**
	 * 图片信息
	 * @author zonghua
	 *
	 */
	public class QiniuResponse {
		public long fsize;
		public String key;
		public String url;
		public String hash;
		public int width;
		public int height;
	}

}
