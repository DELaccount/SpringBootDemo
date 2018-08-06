package com.qiyuesuo.dao;

import com.qiyuesuo.pojo.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FileDao extends JpaRepository<FileInfo,Integer> {
    /**
     * 通过uuid查找元信息
     * @param uuid
     * @return
     */
    public FileInfo findByUuid(String uuid);

    /**
     * 通过uuid删除元信息
     * @param uuid
     */
    @Transactional
    public int deleteByUuid(String uuid);
}
