package simple.project.giis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import simple.project.giis.model.dao.UserDao;
import simple.project.giis.model.dto.RetResponse;
import simple.project.giis.model.dto.RetResult;
import simple.project.giis.model.entity.PushSetting;
import simple.project.giis.model.entity.User;
import simple.project.giis.service.impl.PushServiceImpl;
import simple.project.giis.service.impl.UserServiceImpl;

import javax.annotation.Resource;

/**
 * @author ys
 * @date on 2019/1/29 21:21
 */
@Controller
public class PushController {
    private static final String PUSH = "/push";
    @Resource
    private UserServiceImpl userService;
    @Resource
    UserDao userDao;
    @Resource
    private PushServiceImpl pushService;

    @RequestMapping(value = PUSH + "/updateSetting")
    @ResponseBody
    public RetResult updateSetting(@RequestParam("userPhone") String userPhone,
                                   @RequestParam("pushSwitch") boolean pushSwitch,
                                   @RequestParam("voice") boolean voice,
                                   @RequestParam("vibrate") boolean vibrate,
                                   @RequestParam("floatWindow") boolean floatWindow) {
        System.out.println("phone is " + userPhone + "\nsetting is " + pushSwitch + ", " + voice + ", " + vibrate + ", " + floatWindow + ", ");
        if (!userService.isExisted(userPhone)) {
            return RetResponse.makeErrRsp("账号不存在");
        } else if (!pushService.isExisted(userPhone)) {
            User user = userDao.findByPhone(userPhone);
            user.setPushSetting(pushService.creatPushSetting(new PushSetting(true, false, false, false)));
            userDao.saveAndFlush(user);
            return RetResponse.makeOKRsp("保存成功");
        } else {
            pushService.setPushSetting(userPhone, pushSwitch, voice, vibrate, floatWindow);
            return RetResponse.makeOKRsp("修改成功");
        }
    }

    @RequestMapping(value = PUSH + "/downloadSetting")
    @ResponseBody
    public RetResult<PushSetting> downloadSetting(@RequestParam("userPhone") String userPhone) {
        System.out.println("phone is " + userPhone);
        if (!userService.isExisted(userPhone)) {
            return RetResponse.makeErrRsp("账号不存在");
        } else if (!pushService.isExisted(userPhone)) {
            return RetResponse.makeErrRsp("推送设置不存在");
        } else {
            return RetResponse.makeOKRsp(pushService.getPushSetting(userPhone));
        }
    }
}
