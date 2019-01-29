package simple.project.giis.service.impl;

import org.springframework.stereotype.Service;
import simple.project.giis.model.dao.PushSettingDao;
import simple.project.giis.model.dao.UserDao;
import simple.project.giis.model.entity.PushSetting;
import simple.project.giis.service.PushService;

import javax.annotation.Resource;

@Service
public class PushServiceImpl implements PushService {

    @Resource
    UserDao userDao;

    @Resource
    PushSettingDao pushSettingDao;

    @Override
    public boolean isExisted(String phone) {
        return (null != userDao.findByPhone(phone).getPushSetting());
    }

    @Override
    public PushSetting creatPushSetting(PushSetting setting) {
        return pushSettingDao.saveAndFlush(setting);
    }

    @Override
    public PushSetting getPushSetting(String phone) {
        return userDao.findByPhone(phone).getPushSetting();
    }


    @Override
    public void setPushSetting(String phone, boolean var1, boolean var2, boolean var3, boolean var4) {

        PushSetting setting = userDao.findByPhone(phone).getPushSetting();
        setting.setPushSwitch(var1);
        setting.setVoice(var2);
        setting.setVibrate(var3);
        setting.setFloatWindow(var4);
        pushSettingDao.save(setting);
    }
}
