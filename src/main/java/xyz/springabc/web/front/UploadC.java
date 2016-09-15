package xyz.springabc.web.front;

import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xyz.springabc.service.UploadFileServ;
import xyz.springabc.web.helper.Qiniu.QiniuResponse;

import java.io.IOException;

@Controller
public class UploadC {

    @Autowired
    private UploadFileServ uploadFileServ;

    /**
     * 上传文件，返回响应
     *
     * @param file
     * @return
     * @throws QiniuException
     * @throws IOException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public QiniuResponse uploadTest(MultipartFile file) throws Exception {
        return uploadFileServ.upload(file);
    }

    ;
}
