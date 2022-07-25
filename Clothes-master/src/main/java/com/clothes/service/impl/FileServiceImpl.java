package com.clothes.service.impl;

import com.clothes.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String saveFile(String dir, String fileName, byte[] bytes) throws IOException {
        try {
            Date date = new Date();
            String link = dir + date.getTime() + "_" + fileName;
            Path path = Paths.get(link);
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdirs();
            }
            Path des = Files.write(path, bytes);
            if (des == null) {
                throw new IOException("Save file failed");
            }
            return link;
        } catch (IOException e) {
            throw new IOException("Save file failed");
        }
    }

    @Override
    public void deleteFile(String dir) {
        File file = new File(dir);
        file.delete();
    }
}
