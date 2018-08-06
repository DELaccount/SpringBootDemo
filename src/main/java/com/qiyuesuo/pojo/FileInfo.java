package com.qiyuesuo.pojo;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class FileInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;
    private Long fileSize;
    private String createTime;

    public FileInfo() {

    }

    public FileInfo(String uuid, long fileSize, String createDate) {
        this.uuid = uuid;
        this.fileSize = fileSize;
        this.createTime = createDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "{\"uuid\":\"" + uuid + "\",\"size\":" + fileSize + ",\"createDate\":\"" + createTime + "\"}";
    }
}
