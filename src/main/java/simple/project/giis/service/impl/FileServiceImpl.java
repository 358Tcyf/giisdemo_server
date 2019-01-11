package simple.project.giis.service.impl;

import simple.project.giis.model.dao.UserFileDao;
import simple.project.giis.model.entity.UserFile;
import simple.project.giis.service.FileService;

import javax.annotation.Resource;

public class FileServiceImpl implements FileService {

    @Resource
    private UserFileDao userFileDao;

    @Override
    public void upload(UserFile file) {
        userFileDao.saveAndFlush(file);
    }

    @Override
    public UserFile getFile(String uuid) {
        return userFileDao.getUserFileByUuid(uuid);
    }
}
