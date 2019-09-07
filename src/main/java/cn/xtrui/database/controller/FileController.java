package cn.xtrui.database.controller;

import cn.xtrui.database.bean.MyFile;
import cn.xtrui.database.bean.User;
import cn.xtrui.database.service.FileService;
import cn.xtrui.database.util.FileManageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@Controller
public class FileController {
    @Autowired
    private FileManageUtil fileManageUtil;

    @Autowired
    private FileService fileService;

    Logger log = LoggerFactory.getLogger("mylog");
    @ResponseBody
    @GetMapping("/getFiles")
    public Vector<MyFile> getFilesByPage(Integer page){

        return fileService.getFilesByPage(page);
    }

    @ResponseBody
    @PostMapping("/file/upFile")
    public Map<Object, Object> upFile(@RequestParam(value = "file", required = false)
                 MultipartFile file, HttpServletRequest request ) {
        User user = (User) request.getSession().getAttribute("user");
        boolean flag = fileManageUtil.upFile(file,user.getId());
        HashMap<Object, Object> map = new HashMap<>();
        if(flag){
            map.put("code","200");
            map.put("message","文件上传成功");

            log.info("用户--"+user.getUsername()+"--上传文件---"+file.getOriginalFilename());
        }
        else {
            map.put("code","404");
            map.put("message","文件上传失败");
        }
        return map;
    }

    @GetMapping("/file/download")
    public Void downloadFile(String fileName , HttpServletRequest request, HttpServletResponse response){

        MyFile myFile = fileService.getFileByFileName(fileName);
        String path = myFile.getPath();
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition",
                "attachment;fileName=" +  fileName);// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(path);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            User user = (User) request.getSession().getAttribute("user");
            log.info("用户--"+user.getUsername()+"---下载文件---"+ fileName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @ResponseBody
    @DeleteMapping("/file/delete")
    public Map deleteFileById(Integer id){
        boolean flag = false;
        flag = fileService.deleteFile(id);
        HashMap<Object, Object> map = new HashMap<>();
        if(flag){
            map.put("code","200");
            map.put("message","删除文件成功");
        }
        else {
            map.put("code","404");
            map.put("message","删除文件失败");
        }
        return map;
    }

}


