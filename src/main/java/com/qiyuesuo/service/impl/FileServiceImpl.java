package com.qiyuesuo.service.impl;

import com.qiyuesuo.FilesysApplication;
import com.qiyuesuo.exception.FileSystemException;
import com.qiyuesuo.pojo.FileInfo;
import com.qiyuesuo.service.FileMetaDataService;
import com.qiyuesuo.service.FileService;
import com.qiyuesuo.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    FileMetaDataService fileInfoService;

    /**
     * 通过file保存文件
     *
     * @param inputFile
     * @return uuid
     */
    public String save(byte[] inputFile) {
        String uuid = UUID.randomUUID().toString();
        FileInfo fileInfo = FileUtil.saveFile(inputFile, FileUtil.getFileSavePathByUuid(uuid), uuid);
        fileInfoService.save(fileInfo);
        return uuid;
    }

    /**
     * 通过uuid获取文件元信息
     *
     * @param uuid
     * @return
     */
    public FileInfo getInfo(String uuid) {
        logger.info("开始查找uuid为" + uuid + "的元信息");
        return fileInfoService.select(uuid);
    }

    /**
     * 读取文件到字节数组
     *
     * @param uuid
     * @return byte
     */
    public byte[] readToByte(String uuid) {
        return FileUtil.readFile(uuid);
    }

    /**
     * 删除文件及其元信息
     *
     * @param uuid
     */
    public void delete(String uuid) {
        FileUtil.deleteFile(uuid, FileUtil.getFileSavePathByUuid(uuid));
        fileInfoService.delete(uuid);
    }

}
