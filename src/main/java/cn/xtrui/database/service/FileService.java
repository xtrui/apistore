package cn.xtrui.database.service;

import cn.xtrui.database.bean.MyFile;
import cn.xtrui.database.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Vector;

import static cn.xtrui.database.util.Constant.NUM_PER_PAGE;

@Component
public class FileService {
    @Autowired
    private FileMapper fileMapper;
    /**
     * 获取路径下文件名
     * @param path
     * @return
     */
   public Vector<String> getFileNamesByPath(String path){
       File file = new File(path);
       File files[] = file.listFiles();
       Vector<String> fileNames = new Vector<>();
       for(File e : files){
           fileNames.add(e.getName());
       }
       return fileNames;
    }

    public boolean addfile(MyFile myFile){
       return fileMapper.insertFile(myFile);
    }

    public boolean deleteFile(Integer id){
       boolean flag =false;
       MyFile myFile = fileMapper.getFileById(id);
       File file = new File(myFile.getPath());
       if(file.exists()){
           flag = file.delete();
       }
       flag = fileMapper.deleteFileById(id);
       return flag;
    }

    public Vector<MyFile> getFilesByPage(Integer page){
       Integer count = fileMapper.count();
       if(page == null){
           page = 1;
       }else if (page > count/NUM_PER_PAGE + 1){
           page = 1;
       }
       else if (page < 1){
           page = count/NUM_PER_PAGE + 1;
       }
       return fileMapper.getFilesByPage(page);
    }

    public Vector<MyFile> getFilesByType(String type){
       return fileMapper.getFilesByType(type);
    }

    public MyFile getFileByFileName(String fileName){
       return fileMapper.getFileByFileName(fileName);
    }
}
