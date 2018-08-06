package com.qiyuesuo.service;

import com.qiyuesuo.pojo.FileInfo;
import org.reactivestreams.Publisher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PublicKey;

public interface FileService {
    /**
     * 通过file保存文件
     *
     * @param inputFile
     * @return uuid
     */
    public String save(byte[] inputFile);

    /**
     * 通过uuid获取文件元信息
     *
     * @param uuid
     * @return
     */
    public FileInfo getInfo(String uuid);

    /**
     * 读取文件到字节数组
     *
     * @param uuid
     * @return byte
     */
    public byte[] readToByte(String uuid);

    /**
     * 删除文件及其元信息
     *
     * @param uuid
     */
    public void delete(String uuid);

}
