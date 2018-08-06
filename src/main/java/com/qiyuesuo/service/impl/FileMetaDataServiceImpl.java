package com.qiyuesuo.service.impl;

import com.qiyuesuo.dao.FileDao;
import com.qiyuesuo.exception.FileSystemException;
import com.qiyuesuo.pojo.FileInfo;
import com.qiyuesuo.service.FileMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class FileMetaDataServiceImpl implements FileMetaDataService {

    @Autowired
    FileDao fileDao;

    /**
     * 保存元信息
     *
     * @param info 元信息对象
     * @return
     */
    @Override
    @Cacheable(value = "fileInfo", key = "#info.uuid")
    public FileInfo save(FileInfo info) {
        return fileDao.save(info);
    }

    /**
     * 删除元信息
     *
     * @param uuid
     */
    @Override
    @CacheEvict(value = "fileInfo", key = "#uuid")
    public void delete(String uuid) {
        if (fileDao.deleteByUuid(uuid) <= 0) {
            throw new FileSystemException("删除文件元信息失败");
        }
    }

    /**
     * 查找元信息
     *
     * @param uuid
     * @return
     */
    @Override
    @Cacheable(value = "fileInfo", key = "#uuid")
    public FileInfo select(String uuid) {
        FileInfo fileInfo = fileDao.findByUuid(uuid);
        if (fileInfo == null) {
            throw new FileSystemException("文件元信息不存在");
        }
        return fileInfo;
    }
}
