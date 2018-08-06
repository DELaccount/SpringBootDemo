package com.qiyuesuo.service;

import com.qiyuesuo.pojo.FileInfo;


public interface FileMetaDataService {
    /**
     * 保存文件元信息
     *
     * @param info
     * @return
     */
    FileInfo save(FileInfo info);

    /**
     * 删除文件元信息
     *
     * @param uuid
     */
    void delete(String uuid);

    /**
     * 查找文件元信息
     *
     * @param uuid
     */
    FileInfo select(String uuid);
}
