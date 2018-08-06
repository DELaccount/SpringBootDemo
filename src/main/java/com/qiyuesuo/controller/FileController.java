package com.qiyuesuo.controller;

import com.qiyuesuo.dao.FileDao;
import com.qiyuesuo.exception.FileSystemException;
import com.qiyuesuo.pojo.FileInfo;
import com.qiyuesuo.service.FileMetaDataService;
import com.qiyuesuo.service.FileService;
import com.qiyuesuo.util.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@EnableRedisHttpSession
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileDao fileDao;

    @Autowired
    FileService fileService;

    @Autowired
    FileMetaDataService fileMetaDataService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/selectAll";
    }

    /**
     * 查找所有元信息
     *
     * @return 所有元信息
     */
    @RequestMapping("/selectAll")
    public ModelAndView fileList(@RequestParam(value = "page", defaultValue = "0") int page, HttpServletRequest request) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page, 20, sort);
        Page<FileInfo> infoList = fileDao.findAll(pageable);

        ModelAndView mav = new ModelAndView("selectAll");
        mav.addObject("page", infoList);
        return mav;
    }

    /**
     * 通过uuid查找元信息并返回
     *
     * @param uuid
     * @return
     */
    @GetMapping(value = "/select")
    @ResponseBody
    public FileInfo metaDataListByUuid(@RequestParam("uuid") String uuid, HttpServletRequest request) {
        FileInfo fileInfo = fileMetaDataService.select(uuid);
        return fileInfo;
    }


    /**
     * 通过元信息删除
     *
     * @param uuid
     */
    @GetMapping("/delete")
    private String metaDataDel(@RequestParam("uuid") String uuid, HttpServletRequest request) {
        fileService.delete(uuid);
        fileDao.deleteByUuid(uuid);
        return "redirect:/selectAll";
    }

    /**
     * 上传文件
     *
     * @throws IOException
     */
    @PostMapping("/upload")
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        if (files.size() <= 0) {
            throw new FileSystemException("文件列表为空");
        }
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    fileService.save(bytes);
                } catch (Exception e) {
                    logger.error("You failed to upload " + i + " =>" + e.getMessage());
                }
            } else {
                logger.error("You failed to upload " + i + " becausethe file was empty.");
            }
        }
        return "redirect:/selectAll";
    }

    /**
     * 下载文件
     *
     * @param uuid
     */
    @GetMapping("/downLoad")
    public void downLoad(@RequestParam("uuid") String uuid, HttpServletRequest request, HttpServletResponse resp) throws IOException {
        request.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (uuid == null) {
            // 上传参数无效
            throw new RuntimeException("获取上传参数失败");
        }
        byte[] arr = fileService.readToByte(uuid);
        // 设置下发内容
        resp.setHeader("Content-Disposition", "attachment; filename=" + uuid + "");
        resp.addHeader("Content-Length", "" + arr.length);
        resp.setContentType("multipart/form-data;charset=UTF-8");
        OutputStream respStream = resp.getOutputStream();
        respStream.write(arr);
        respStream.flush();
        respStream.close();
    }
}
