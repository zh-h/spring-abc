package xyz.springabc.web.front;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.common.QiniuException;

import xyz.springabc.service.UploadFileServ;
import xyz.springabc.web.helper.Qiniu;
import xyz.springabc.web.helper.Qiniu.QiniuResponse;

@Controller
public class UploadC {

	@Autowired
	private UploadFileServ uploadFileServ;

	/**
	 * 上传文件，返回响应
	 * @param file
	 * @return
	 * @throws QiniuException
	 * @throws IOException
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public QiniuResponse uploadTest(MultipartFile file) throws Exception {
		return uploadFileServ.upload(file);
	};
}
