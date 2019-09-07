package cn.xtrui.database.util;

import cn.xtrui.database.bean.MyFile;
import cn.xtrui.database.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
@Component
public class FileManageUtil {
    @Autowired
    private FileService fileService;
    /**
     * 文件上传
     * @param file
     * @return
     */
    public boolean upFile(MultipartFile file,Integer userId) {
        boolean flag = false;

        String type = null;
        if(file != null){
            Date date = new Date();
            long t = date.getTime();
            String fileName = ""+userId+t+file.getOriginalFilename();
            if(fileName.lastIndexOf(".") != -1) {
                 type = fileName.substring(fileName.lastIndexOf(".") + 1);

            }
            String pathHome = "/webFile/";
            String path = pathHome+"files/"+type+"/"+fileName;
            String dirPath = pathHome+"files/"+type;
            File dir = new File(dirPath);
            if(!dir.exists()){
                dir.mkdirs();
            }

            MyFile myFile = new MyFile();
            myFile.setName(fileName);
            myFile.setPath(path);
            myFile.setType(type);
            myFile.setTime(date);

            try {
                file.transferTo(new File(dir.getAbsolutePath()+"/"+fileName));
                myFile.setPath(dir.getAbsolutePath()+"/"+fileName);
                fileService.addfile(myFile);
                flag = true;

            } catch (IOException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }

    /**
     * 下载文件
     * @param path
     * @return
     */

    public boolean downloadFile(String path, String fileNam, HttpServletResponse response){
        boolean flag = false;
        String fileName = path.substring(path.lastIndexOf(".")+1);
        Date date = new Date();
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition",
                "attachment;fileName=" + fileNam);// 设置文件名
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
            flag = true;

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
    return  flag;

    }


}
