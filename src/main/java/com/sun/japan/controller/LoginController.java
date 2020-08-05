package com.sun.japan.controller;

import com.sun.japan.entities.Emp;
import com.sun.japan.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private EmpService empService;

    @Autowired
    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }

    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/signin")
    public String signIn(@RequestParam("username")String username, @RequestParam("password")String password, HttpServletRequest request){
        if (username == null || password == null){
            return "forward:/";
        }else {
            Emp emp = empService.loginIn(username, password);
            if (emp != null){
                HttpSession session = request.getSession();
                if (session.getAttribute("username") != null){
                    session.removeAttribute("username");
                }
                if (session.getAttribute("password") != null){
                    session.removeAttribute("password");
                }
                session.setAttribute("username",username);
                session.setAttribute("password",password);
                if (emp.getRole().equals("人事")){
                    return "index";
                }else {
                    return "signupsearch";
                }
            }else {
                return "forward:/";
            }
        }
    }
}
