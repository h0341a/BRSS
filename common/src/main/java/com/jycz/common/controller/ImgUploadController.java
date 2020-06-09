package com.jycz.common.controller;

import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Locale;
import java.util.UUID;

@Api(tags = "图片服务器")
@RestController
public class ImgUploadController {

    @DeleteMapping("/img")
    public Result deleteImg(String imgName) {
        String imgDir = System.getProperty("user.dir") +"/img/";
        String localPath = imgDir + imgName;
        File file = new File(localPath);
        if (file.exists()) {
            if (file.delete()) {
                return Result.ofSuccess("删除成功");
            } else {
                return Result.ofFail(ErrCodeEnum.DATA_ABORT, "图片存在，但是删除失败");
            }
        } else {
            return Result.ofFail(ErrCodeEnum.USER_OPERATION_PUZZLE, "图片不存在");
        }
    }

    @PostMapping("/upload")
    public Result imageUpload(@RequestParam("uploadFile") MultipartFile uploadImg) {
        String imgDir = System.getProperty("user.dir") +"/img/";
        if (uploadImg == null) {
            return Result.ofFail(ErrCodeEnum.USER_OPERATION_PUZZLE, "图片不能为空");
        } else if (uploadImg.getSize() > 1024 * 1024 * 10) {
            return Result.ofFail(ErrCodeEnum.PARAMETERS_INVALID, "图片大小不能大于10M");
        }
        //获取文件后缀名
        String suffix = uploadImg.getOriginalFilename()
                .substring(uploadImg.getOriginalFilename()
                        .lastIndexOf(".") + 1, uploadImg.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return Result.ofFail(ErrCodeEnum.PARAMETERS_INVALID, "请选择jpg,jpeg,gif,png格式的图片");
        }
        File imgFilePath = new File(imgDir);
        if (!imgFilePath.exists()) {
            imgFilePath.mkdir();
        }
        String filename = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
        try {
            uploadImg.transferTo(new File(imgDir + filename));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ofFail(ErrCodeEnum.DATA_ABORT, "保存文件异常");
        }
        return Result.ofSuccess(filename);
    }
}
