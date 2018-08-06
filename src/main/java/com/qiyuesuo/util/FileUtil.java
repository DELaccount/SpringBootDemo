package com.qiyuesuo.util;

import com.qiyuesuo.exception.FileSystemException;
import com.qiyuesuo.pojo.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件操作工具类
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static String ROOT_DIRECTORY = "./File";


    /**
     * @param uuid 根据uuid创建文件夹 返回文件保存路径
     */
    public static String getFileSavePathByUuid(String uuid) {
        return ROOT_DIRECTORY + "/" + uuid.substring(0, 2) + "/" + uuid.substring(2, 4); // 返回保存路径
    }

    /**
     * 通过字节流保存文件
     *
     * @param souceByteStream
     * @param targetPath
     * @param targetFileName
     * @return 保存的文件信息
     */
    public static FileInfo saveFile(byte[] souceByteStream, String targetPath, String targetFileName) {
        File targetFolder = new File(targetPath);

        FileInfo fileInfo = null;

        // 判断当前目标文件目录是否存在，不存在则建立
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        Path path = Paths.get(targetPath + "/" + targetFileName);

        if (!Files.exists(path)) {
            try {
                Files.write(path, souceByteStream);
                fileInfo = new FileInfo(targetFileName, Files.size(path), TimeUtil.getNowTime());
                System.out.println(fileInfo.toString());
                logger.info("通过字节流写入文件成功:" + targetPath + "/" + targetFileName);
            } catch (IOException e) {
                throw new FileSystemException("通过字节流写入文件失败");
            }
        }

        return fileInfo;
    }

    public static byte[] readFile(String uuid) {
        String filePath = FileUtil.getFileSavePathByUuid(uuid);
        Path path = Paths.get(filePath + "/" + uuid);

        if (Files.exists(path)) {
            try {
                logger.info("读取文件" + filePath + "/" + uuid);
                return Files.readAllBytes(path);
            } catch (IOException e) {
                throw new FileSystemException("文件读取失败");
            }
        } else {
            throw new FileSystemException("文件不存在");
        }
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @param filePath
     */
    public static void deleteFile(String fileName, String filePath) {

        Path path = Paths.get(filePath + "/" + fileName);

        try {
            if (Files.exists(path)) {
                Files.delete(path);
                logger.info("删除文件：" + filePath + "/" + fileName);
                deleteEmptyFolder(new File(filePath));//检测二级目录是否为空，为空则删除
                deleteEmptyFolder(new File(filePath.substring(0, filePath.length() - 3)));//检测一级目录是否为空，为空则删除
            } else {
                throw new FileSystemException("要删除的文件不存在");
            }
        } catch (IOException e) {
            throw new FileSystemException("删除文件失败");
        }

    }

    /**
     * 判断目录是否为空，为空则删除该目录
     *
     * @param filePath
     */
    public static void deleteEmptyFolder(File filePath) {
        File[] fileList = filePath.listFiles();
        if (fileList.length <= 0) {
            filePath.delete();
            logger.info("删除目录：" + filePath.getName());
        }
    }
}
