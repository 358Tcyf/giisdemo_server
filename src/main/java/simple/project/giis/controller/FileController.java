package simple.project.giis.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simple.project.giis.model.dao.UserDao;
import simple.project.giis.model.dto.RetResponse;
import simple.project.giis.model.dto.RetResult;
import simple.project.giis.model.entity.User;
import simple.project.giis.model.entity.UserFile;
import simple.project.giis.service.impl.FileServiceImpl;
import simple.project.giis.service.impl.UserServiceImpl;
import simple.project.giis.utils.FileUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static simple.project.giis.utils.UuidUtil.getUUID32;

@Controller
public class FileController {

    private static final String FILE = "/file";
    private static final String PIC = "/pic";
    private static final String AD = "/ad";
    @Resource
    private FileServiceImpl fileService;

    @Resource
    private UserServiceImpl userService;


    @Resource
    private UserDao userDao;

    @RequestMapping(FILE + "/upload")
    @ResponseBody
    public RetResult upload(@RequestParam("file") MultipartFile file, String phone) throws IOException {
        if (file.isEmpty())
            return RetResponse.makeErrRsp("文件不存在");
        if (!userService.isExisted(phone))
            return RetResponse.makeErrRsp("用户不存在");
        User user = userDao.findByPhone(phone);
        String uuid = getUUID32();
        System.out.println("uuid is " + uuid);
        String filename = file.getOriginalFilename();
        System.out.println("filename is " + filename);

        UserFile userFile = new UserFile();
        userFile.setUuid(uuid);
        userFile.setName(filename);
        userFile.setPath(FileUtil.upload(file, uuid));
        user.setPic(userFile);
        fileService.upload(userFile);
        return RetResponse.makeOKRsp("上传成功");
    }


    @GetMapping(FILE + "/apk")
    public void downApkFile(HttpServletResponse response) throws IOException {
        String filename = "app-debug.apk";
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        byte[] data;
        InputStream inputStream = new ClassPathResource("/static/apk/" + filename).getInputStream();
        data = new byte[inputStream.available()];
        inputStream.read(data);
        inputStream.close();
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }


    @GetMapping(AD + "/{filename:.+}")
    public void downBannerAd(@PathVariable String filename, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        byte[] data;
        InputStream inputStream = new ClassPathResource("/static/img/" + filename + ".png").getInputStream();
        data = new byte[inputStream.available()];
        inputStream.read(data);
        inputStream.close();
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }

    @RequestMapping(PIC + "/{phone:.+}")
    public void downloadPic(@PathVariable String phone, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        byte[] data;
        if (!userService.isExisted(phone)) {
            System.out.println("用户不存在");
            return;
        }
        User user = userDao.findByPhone(phone);
        String path = user.getPic().getPath();
        File file = new File(path);
        if (!file.exists())
            path = "/static/img/user_pic_blue.png";
        InputStream inputStream = new ClassPathResource(path).getInputStream();
        data = new byte[inputStream.available()];
        inputStream.read(data);
        inputStream.close();
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }

}
