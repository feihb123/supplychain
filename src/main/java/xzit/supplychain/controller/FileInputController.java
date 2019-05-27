package xzit.supplychain.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xzit.supplychain.service.ConsumerService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * 文件处理控制器
 * @author datacharm.cn
 **/

@RestController
public class FileInputController {
    @Autowired
    ConsumerService consumerService;

    @RequestMapping("/uploadImg")
    public String upload(@RequestParam("file") MultipartFile file, HttpSession session)  {

        int consumerId =  Integer.parseInt(session.getAttribute("id").toString());
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();

        File dest = null;
        String os = System.getProperty("os.name");

        if (os.toLowerCase().startsWith("win")) {
            String path = "G:"+File.separator+"img"+File.separator;
            dest= new File(path + fileName);
        }else {
            String path = "/webapps/img/";
            dest= new File(path + fileName);
        }

        try {
            file.transferTo(dest);
            //数据库中存储路径  访问 upload/** -> 本地文件
            String src = "upload/"+fileName;
            consumerService.savePersonalImg(src,consumerId);
            return JSON.toJSONString("上传成功！");
        } catch (IOException e) {
            return JSON.toJSONString("上传失败！");
        }

    }
}
