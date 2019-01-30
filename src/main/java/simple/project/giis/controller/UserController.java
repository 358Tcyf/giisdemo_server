package simple.project.giis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import simple.project.giis.model.dto.RetResponse;
import simple.project.giis.model.dto.RetResult;
import simple.project.giis.model.entity.PushSetting;
import simple.project.giis.model.entity.User;
import simple.project.giis.service.impl.PushServiceImpl;
import simple.project.giis.service.impl.UserServiceImpl;

import javax.annotation.Resource;
import java.util.Map;

import static simple.project.giis.utils.VerifyCodeUtil.createCode;

/**
 * @author Simple
 * @date on 2019/1/3 16:27
 */
@RestController
public class UserController {

    private static final String USER = "/user";
    private static final String MANAGER = "/manager";

    @Resource
    UserServiceImpl userService;

    @Resource
    PushServiceImpl pushService;

    @RequestMapping(value = USER + "/login")
    public RetResult<User> login(String phone, String password) {
        System.out.println("phone is " + phone + "\npassword is " + password);
        if (!userService.isExisted(phone)) {
            return RetResponse.makeErrRsp("账号错误");
        } else if (!userService.checkPwd(phone, password)) {
            return RetResponse.makeErrRsp("密码错误");
        } else {
            System.out.println("login success!");
            return RetResponse.makeOKRsp("登陆成功", userService.login(phone, password));
        }
    }

    @RequestMapping(value = USER + "/signUp")
    public RetResult<User> signUp(String name, String phone, String password) {
        String code = "";
        if (!userService.checkVerifyCode(phone, code)) {
            System.out.println("name is " + name + "\nphone is " + phone + "\npassword is " + password);
            if (userService.isExisted(phone)) {
                return RetResponse.makeErrRsp("手机号已被注册");
            } else {
                System.out.println("sign up success!");
                userService.signUp(name, phone, password);
                return RetResponse.makeOKRsp("注册成功");
            }
        } else {
            return RetResponse.makeErrRsp("验证码错误");
        }
    }

    @RequestMapping(value = USER + "/updateInfo")
    @ResponseBody
    public RetResult updateInfo(String oldPhone, String newPhone, String password) {
        System.out.println("oldPhone is " + oldPhone + "\nnewPhone is " + newPhone + "\npassword is " + password);
        if (!userService.isExisted(oldPhone)) {
            return RetResponse.makeErrRsp("账号不存在");
        } else {
            userService.updateInfo(oldPhone, newPhone, password);
            return RetResponse.makeOKRsp("修改成功");
        }
    }

    @RequestMapping(value = USER + "/getCode")
    public RetResult getCode(String cid, String phone) {
        String code = createCode();
//        sendSingleMessage(0, cid, linkTemplateDemo(setStyle("验证码", code)));
        return RetResponse.makeOKRsp();
    }

    @RequestMapping(value = USER + "/userInfo")
    public RetResult<Map<String, Object>> getUserInfo(String phone) {
        if (userService.isExisted(phone))
            return RetResponse.makeErrRsp("查无此人");
        else {
            return RetResponse.makeOKRsp(userService.getInfo(phone));
        }
    }

    @RequestMapping(value = MANAGER + "/userList")
    public RetResult<Map<String, Object>> getUserList(String phone) {
        return RetResponse.makeOKRsp(userService.getList(phone));
    }

    @RequestMapping(value = USER + "/getSetting")
    public RetResult<PushSetting> getPushSetting(String phone) {
        return RetResponse.makeOKRsp(pushService.getPushSetting(phone));
    }

    @RequestMapping(value = USER + "/setSetting")
    public void setPushSetting(String phone, boolean var1, boolean var2, boolean var3, boolean var4) {
        pushService.setPushSetting(phone, var1, var2, var3, var4);
    }
}
