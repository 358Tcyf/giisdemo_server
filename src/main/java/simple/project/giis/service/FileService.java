package simple.project.giis.service;

import simple.project.giis.model.entity.UserFile;

public interface FileService {

    void upload(UserFile file);

    UserFile getFile(String uuid);
}
