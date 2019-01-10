package simple.project.giis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.project.giis.model.dto.RetResponse;
import simple.project.giis.model.dto.RetResult;
import simple.project.giis.model.entity.User;
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
    public RetResult<User> login(String phone, String password) {
        System.out.println("phone is " + phone + "\npasswd is " + password);
        if (!userService.isExisted(phone)) {
            return RetResponse.makeErrRsp("phone not found");
        } else if (!userService.checkPwd(phone, password)) {
            return RetResponse.makeErrRsp("password error");
        } else {
            System.out.println("login success!");
            return RetResponse.makeOKRsp("login success!", userService.login(phone, password));
        }
    }

    @RequestMapping(value = "/signUp")
    public RetResult<User> signUp(String name, String phone, String password) {
        System.out.println("name is " + name + "\nphone is " + phone + password);
        if (userService.isExisted(phone)) {
            return RetResponse.makeErrRsp("user's phone is existed");
        } else {
            System.out.println("sign up success!");
            userService.signUp(name, phone, password);
            return RetResponse.makeOKRsp("sign up success!");
        }
    }
}
