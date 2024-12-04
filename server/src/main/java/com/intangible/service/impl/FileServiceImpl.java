package com.intangible.service.impl;

import com.intangible.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    private final static String userFilePath = "G:\\成品项目\\2-SSM\\非物质文化遗产推广平台\\service\\image";


    public boolean uploadFile(MultipartFile multipartFile,String fileName)throws IOException {
        File fileDir = new File(userFilePath);
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                return false;
            }
        }
        File file = new File(fileDir.getAbsolutePath() +"/"+fileName);
        if (file.exists()) {
            if (!file.delete()) {
                return false;
            }
        }
        if (file.createNewFile()) {
            multipartFile.transferTo(file);
            return true;
        }
        return false;
    }
}
