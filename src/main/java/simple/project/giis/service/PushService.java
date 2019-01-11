package simple.project.giis.service;

import simple.project.giis.model.entity.PushSetting;

public interface PushService {

    PushSetting getPushSetting(String phone);

    void setPushSetting(String phone,boolean var1, boolean var2, boolean var3, boolean var4);
}
