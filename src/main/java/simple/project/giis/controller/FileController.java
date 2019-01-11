package simple.project.giis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import simple.project.giis.model.dto.RetResponse;
import simple.project.giis.model.dto.RetResult;
import simple.project.giis.model.entity.UserFile;
import simple.project.giis.service.FileService;

import javax.annotation.Resource;
import java.util.UUID;

public class FileController {

    private static final String FILE = "/file";
    @Resource
    private FileService fileService;

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
}
