package com.sun.japan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    //将用户引导至培训检索页面
    @RequestMapping("/trainsearch")
    public String trainSearch(){
        return "trainsearch";
    }

    //将用户引导至培训明细页面
    @RequestMapping("/traindetails")
    public String trainDetails(){
        return "traindetails";
    }

    //将用户引导至课程内容更新画面
    @RequestMapping("/lessoncontentupdate")
    public String lessonContentUpdate(){
        return "lessoncontentupdate";
    }

    //将用户引导至培训人员明细页面
    @RequestMapping("/trainmemberdetails")
    public String trainMemberDetails(){
        return "trainmemberdetails";
    }

    //将用户引导至培训人员追加页面
    @RequestMapping("/trainmemberappend")
    public String trainMemberAppend(){
        return "trainmemberappend";
    }
}
