package simple.project.giis.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import simple.project.giis.model.dto.RetResponse;
import simple.project.giis.model.dto.RetResult;
import simple.project.giis.model.entity.UserFile;
import simple.project.giis.service.FileService;
import simple.project.giis.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@Controller
public class FileController {

    private static final String FILE = "/file";
    @Resource
    private FileService fileService;

    @Resource
    private UserService userService;

    @RequestMapping(value = FILE + "/upload")
    public RetResult upload(MultipartFile file) {
        if (file.isEmpty())
            return RetResponse.makeErrRsp("上传失败");
        else {
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            String filename = file.getOriginalFilename();
            UserFile userFile = new UserFile();
            userFile.setUuid(uuid);
            userFile.setName(filename);
        }
        return RetResponse.makeOKRsp("OK");
    }


    @RequestMapping(value = FILE + "/download")
    public RetResult download(String uuid) {
        return RetResponse.makeOKRsp(fileService.getFile(uuid));
    }


    @RequestMapping(FILE + "/{filename:.+}")
    public void getHeadImage(@PathVariable String filename, HttpServletResponse response) throws IOException {
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


    @RequestMapping(FILE + "/uploadHead")
    public RetResult uploadHead(@RequestParam("file") MultipartFile file, String phone) throws IOException {
        if (!file.isEmpty()) {
            if (userService.isExisted(phone)) {
                if (null != file) {
                    String filepath = this.getClass().getResource("/static/img/").getPath();
                    File webFile = new File(filepath);
                    if (!webFile.exists()) {//目录不存在就创建
                        webFile.mkdirs();
                    }
                    String replacePath = webFile + "\\" + phone + ".png";
                    file.transferTo(new File(replacePath));//保存文件
                }
//                userService.updateUserHeadImage(phone, file.getBytes());

                return RetResponse.makeOKRsp("上传成功");
            }
        }
        return RetResponse.makeErrRsp("操作失败");
    }

}
