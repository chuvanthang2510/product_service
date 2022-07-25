package com.clothes.service;

import java.io.IOException;

public interface FileService {

    String saveFile(String dir, String fileName, byte[] bytes) throws IOException;

    void deleteFile(String dir);
}
