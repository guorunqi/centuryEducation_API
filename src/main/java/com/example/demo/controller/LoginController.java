package com.example.demo.controller;

import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;



    @RequestMapping(value = "/login")
    @ResponseBody
    public ControllerReturn loginVerify(String username,String password,HttpSession session){
        ControllerReturn controllerReturn=new ControllerReturn();
        try {
            User user = loginService.loginVerification(username);
            if (user!=null&&password.equals(user.getPassword())) {
                session.setAttribute("user", user );
                controllerReturn.setCode("true");
                controllerReturn.setData(user);
                return controllerReturn;
            } else {
                controllerReturn.setCode("false");
                controllerReturn.setMessage("用户名或密码不正确");
            }
            return controllerReturn;
        }catch (Exception e){
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }


    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
