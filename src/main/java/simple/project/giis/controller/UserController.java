package simple.project.giis.controller;

import org.springframework.web.bind.annotation.*;
import simple.project.giis.dto.BaseResponse;
import simple.project.giis.dto.RetResponse;
import simple.project.giis.dto.RetResponseOld;
import simple.project.giis.dto.RetResult;
import simple.project.giis.entity.User;
import simple.project.giis.service.impl.UserServiceImpl;

import javax.annotation.Resource;

/**
 * @author Simple
 * @date on 2019/1/3 16:27
 */
@RestController
@RequestMapping("/")
public class UserController {
    @Resource
    UserServiceImpl userService;

    @RequestMapping(value = "/login")
    public RetResult<User> login(String phone, String passwd) {
        System.out.println("phone is " + phone + "\npasswd is " + passwd);
        if (!userService.isExisted(phone)) {
            return RetResponse.makeErrRsp("phone not found");
        } else if (!userService.checkPwd(phone, passwd)) {
            return RetResponse.makeErrRsp("password error");
        } else {
            System.out.println("login success!");
            return RetResponse.makeOKRsp("login success!", userService.login(phone, passwd));
        }
    }

    @RequestMapping(value = "/signUp")
    public RetResult<User> signUp(String name, String phone, String passwd) {
        System.out.println("name is " + name + "\nphone is " + phone + passwd);
        if (userService.isExisted(phone)) {
            return RetResponse.makeErrRsp("user's phone is existed");
        } else {
            System.out.println("sign up success!");
            userService.signUp(name, phone, passwd);
            return RetResponse.makeOKRsp("sign up success!");
        }
    }
}
