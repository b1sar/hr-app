package com.cebrail.kodgemisi.hr.Model;

import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@NoArgsConstructor
public class CV implements MultipartFile {
    private String name;
    private String originalFileName;
    private String contentType;
    private byte[] bytes;

    public CV(String originalFileName, String contentType, byte[] bytes)
    {
        this.name = originalFileName;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.bytes = bytes;
    }


    //Overrided methods
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return originalFileName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return bytes;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
    }
}
