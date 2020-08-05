package com.sun.japan.controller;

import com.sun.japan.entities.*;
import com.sun.japan.service.*;
import com.sun.japan.utils.DateConversionUtil;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class TrainController {
    private TrainInfoService trainInfoService;
    private TrainStudentsService trainStudentsService;
    private EmpService empService;
    private TrainLessonMasterService trainLessonMasterService;
    private DepartmentService departmentService;
    private TrainLessonScheduleService trainLessonScheduleService;

    @Autowired
    public void setTrainInfoService(TrainInfoService trainInfoService) {
        this.trainInfoService = trainInfoService;
    }

    @Autowired
    public void setTrainStudentsService(TrainStudentsService trainStudentsService) {
        this.trainStudentsService = trainStudentsService;
    }

    @Autowired
    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }

    @Autowired
    public void setTrainLessonMasterService(TrainLessonMasterService trainLessonMasterService) {
        this.trainLessonMasterService = trainLessonMasterService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setTrainLessonScheduleService(TrainLessonScheduleService trainLessonScheduleService) {
        this.trainLessonScheduleService = trainLessonScheduleService;
    }

    //通过对培训公告信息遍历分别拿到trainId获取学员信息，并进行人数统计
    @RequestMapping("/train/search")
    @ResponseBody
    public String trainSearch(String status, String startTime, String endTime, String trainTypeName, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("statusStr") != null){
            session.removeAttribute("statusStr");
        }
        if (session.getAttribute("train_searches") != null){
            session.removeAttribute("train_searches");
        }
        List<Train_Search> train_searches = new ArrayList<>();
        String statusStr = "";
//        Map<Train_Info,List<Integer>> map = new HashMap<>();
//        System.out.println(trainTypeName);
        if (status != null && startTime != null && endTime != null && trainTypeName != null){
            String startTimeStr = DateConversionUtil.strToPeriod(startTime);
            String endTimeStr = DateConversionUtil.strToPeriod(endTime);
//            System.out.println(status+startTimeDate+endTimeDate+subjects);
            String trainType = trainLessonMasterService.getTrainType(trainTypeName);
//            System.out.println(trainType);
            List<Train_Info> trainInfos = trainInfoService.getTrainInfos(status, startTimeStr,endTimeStr, trainType);
            if (status.equals("0")){
                statusStr = "未发布";
            }else if (status.equals("1")){
                statusStr = "报名中";
            }else if (status.equals("2")){
                statusStr = "确定";
            }else {
                statusStr = "完了";
            }
            if (trainInfos != null){
                for (Train_Info trainInfo : trainInfos) {
                    Integer trainId = trainInfo.getTrainId();
                    Integer director = trainInfo.getDirector();
                    String directorName = empService.getEmpNameByEmpId(director);
                    //计划参加人数
                    Integer plannedJoinNum = trainStudentsService.countStudentsNum(trainId, "1");
                    //确定参加人数
                    Integer confirmedJoinNum = trainStudentsService.countStudentsNum(trainId, "2");
                    Train_Search train_search = new Train_Search(trainInfo, plannedJoinNum, confirmedJoinNum,directorName,trainTypeName);
//                    List<Integer> joinNumList = new ArrayList<>();
//                    joinNumList.add(plannedJoinNum);
//                    joinNumList.add(confirmedJoinNum);
//                    map.put(trainInfo,joinNumList);
                    train_searches.add(train_search);
                }
            }
        }
        session.setAttribute("statusStr",statusStr);
        session.setAttribute("train_searches",train_searches);
        return "success";
    }

    //追加一条培训公告记录
    @RequestMapping("/train/details/submit")
    public String trainDetails(@RequestParam("subjects")String subjects,
                               @RequestParam("departmentName") String departmentName,
                               @RequestParam("directorName")String directorName,
                               @RequestParam("period")String period,
                               @RequestParam("trainType")String trainType){
        String departmentId = departmentService.getDepartmentId(departmentName);
        Integer empId = empService.getEmpId(directorName, departmentId);
        trainInfoService.saveTrainInfo(subjects,empId,period,trainType);
        return "trainsearch";
    }

    //查询或修改一条公告相关的培训课程
    @RequestMapping(value = "/train/lesson/update")
    @ResponseBody
    public String lessonContentQuery(Integer trainId,HttpServletRequest request){
//        Integer id = Integer.parseInt(trainId);
//        System.out.println(trainId);
        HttpSession session = request.getSession();
        if (session.getAttribute("trainInfo") != null){
            session.removeAttribute("trainInfo");
        }
        if (session.getAttribute("maps") != null){
            session.removeAttribute("maps");
        }
        Train_Info trainInfo = trainInfoService.getTrain_InfoById(trainId);
        session.setAttribute("trainInfo",trainInfo);
        List<Train_Lesson_Schedule> trainLessonSchedules = trainLessonScheduleService.getTrainLessonScheduleByTrainId(trainId);
        if (trainLessonSchedules != null){
            Map<Train_Lesson_Schedule,String> map = new HashMap<>();
            for (Train_Lesson_Schedule trainLessonSchedule : trainLessonSchedules) {
                String startTime = trainLessonSchedule.getStartTime();
                String endTime = trainLessonSchedule.getEndTime();
                String dateTimeStr = startTime+ "~" + endTime;
                map.put(trainLessonSchedule,dateTimeStr);
            }
            session.setAttribute("maps",map);
        }
        return "success";
    }

    //删除页面复选框选中的课程内容
    @RequestMapping("/train/lesson/delete")
    @ResponseBody
    public String lessonContentDelete(String lessonId){
        if (lessonId == null){
            System.out.println("值为空");
        }
//        System.out.println(lessonId);
        if (lessonId != "checkTr" && lessonId != null && lessonId.trim() != ""){
            trainLessonScheduleService.deleteTrainLessonScheduleByLessonId(lessonId);
        }
        return "success";
    }

    @RequestMapping("/train/lesson/submit")
    public String lessonContentSubmit(String[] dateNum,String[] timeFromTo,String[] description,HttpServletRequest request){
        int length = dateNum.length;
        Train_Info trainInfo = (Train_Info)request.getSession().getAttribute("trainInfo");
        Integer trainId = trainInfo.getTrainId();
        for (int i = 0; i < length; i++) {
            String[] split = timeFromTo[i].split("~");
            String startTime = split[0];
            String endTime = split[1];
            Train_Lesson_Schedule trainLessonSchedule = trainLessonScheduleService.getTrainLessonSchedule(dateNum[i], startTime, endTime, description[i]);
            if (trainLessonSchedule == null){
                String lessonId = "lesson" + new Date().getTime();
                trainLessonScheduleService.createTrainLessonSchedule(trainId,lessonId,dateNum[i],description[i],startTime,endTime);
            }
        }
        return "trainsearch";
    }

    @RequestMapping("/train/lesson/release")
    @ResponseBody
    public String trainLessonRelease(Integer trainId){
        trainInfoService.trainLessonStatusUpdate("1",trainId);
        return "success";
    }

    @RequestMapping("/train/lesson/confirm")
    @ResponseBody
    public String trainLessonConfirm(Integer trainId){
        trainInfoService.trainLessonStatusUpdate("2",trainId);
        return "success";
    }

    @RequestMapping("/train/lesson/list/{flg}")
    @ResponseBody
    public String trainLessonList(Integer trainId,HttpServletRequest request,@PathVariable String flg,Model model){
        HttpSession session = request.getSession();
        if (session.getAttribute("studentMap") != null){
            session.removeAttribute("studentMap");
        }
        if (session.getAttribute("flg") != null){
            session.removeAttribute("flg");
        }
        Map<String,List<String>> studentsMap = new HashMap<>();
        List<Train_Students> studentsList = trainStudentsService.getStudentsList(trainId, flg);
        for (Train_Students train_students : studentsList) {
            Integer empId = train_students.getEmpId();
            Emp emp = empService.getEmp(empId);
            String empName = emp.getEmpName();
            String departmentId = emp.getDepartmentId();
            String role = emp.getRole();
            String departmentName = departmentService.getDepartmentName(departmentId);
//            System.out.println(empName);
//            System.out.println(role);
//            System.out.println(departmentName);
            List<String> list = new ArrayList<>();
            list.add(departmentName);
            list.add(role);
            studentsMap.put(empName,list);
        }
        session.setAttribute("studentsMap",studentsMap);
//        model.addAttribute("studentsMap",studentsMap);
        if (flg.equals("0")){
            session.setAttribute("flg","目标对象");
        }else if (flg.equals("2")){
            session.setAttribute("flg","确认对象");
        }
        return "success";
    }

    @RequestMapping("/train/lesson/terminate")
    @ResponseBody
    public String trainLessonTerminate(Integer trainId){
        trainInfoService.trainLessonStatusUpdate("3",trainId);
        return "success";
    }

    @RequestMapping("/train/member/details")
    public String trainMemberDetails(Integer trainId,Model model){
        model.addAttribute("trainId",trainId);
        return "trainmemberdetails";
    }

    @RequestMapping("/train/member/search")
    public String trainMemberSearch(String departmentName,String trainTypeName,Model model){
        Map<Train_Students,List<String>> trainMemberMap = new HashMap<>();
        String departmentId = departmentService.getDepartmentId(departmentName);
        List<Integer> empIds = empService.getEmpId(departmentId);
        String trainType = trainLessonMasterService.getTrainType(trainTypeName);
        List<Integer> trainIds = trainInfoService.getTrainIdsByType(trainType);
        for (Integer trainId : trainIds) {
            for (Integer empId : empIds) {
                Train_Students trainStudents = trainStudentsService.getTrainStudents(trainId, empId, "1");
                if (trainStudents != null){
                    List<String> list = new ArrayList<>();
                    Emp emp = empService.getEmp(empId);
                    String empName = emp.getEmpName();
                    String role = emp.getRole();
                    list.add(empName);
                    list.add(departmentName);
                    list.add(role);
                    trainMemberMap.put(trainStudents,list);
                }
            }
        }
        model.addAttribute("trainMemberMap",trainMemberMap);
        return "trainmemberappend";
    }

    @RequestMapping("/turn/targetObject")
    @ResponseBody
    public String turnTargetObject(@RequestBody String train_studentsStr){
        train_studentsStr = UDecoder.URLDecode(train_studentsStr, "utf-8");
        String[] split = train_studentsStr.split("=");
        String[] split1 = split[3].split(",");
        String trainIdStr = split1[0];
        Integer trainId = Integer.parseInt(trainIdStr);
        String[] split2 = split[4].split(",");
        String empIdStr = split2[0];
        empIdStr = empIdStr.substring(1,empIdStr.length()-1);
        Integer empId = Integer.parseInt(empIdStr);
        trainStudentsService.updateFlg(trainId,empId,"0");
        return "success";
    }

    @RequestMapping("/turn/confirmObject")
    @ResponseBody
    public String turnConfirmObject(@RequestBody String train_studentsStr){
        train_studentsStr = UDecoder.URLDecode(train_studentsStr, "utf-8");
        String[] split = train_studentsStr.split("=");
        String[] split1 = split[3].split(",");
        String trainIdStr = split1[0];
        Integer trainId = Integer.parseInt(trainIdStr);
        String[] split2 = split[4].split(",");
        String empIdStr = split2[0];
        empIdStr = empIdStr.substring(1,empIdStr.length()-1);
        Integer empId = Integer.parseInt(empIdStr);
//        System.out.println(train_students);
        trainStudentsService.updateFlg(trainId,empId,"2");
        return "success";
    }

    @RequestMapping("/signUp/search")
    public String signUpSearch(Integer trainId,String subjects,String trainTypeName,Model model,HttpServletRequest request){
        List<Train_Search> list = new ArrayList<>();
        String trainType = trainLessonMasterService.getTrainType(trainTypeName);
        List<Train_Info> trainInfos = trainInfoService.getTrainInfos(trainId, subjects, trainType,"1");
        for (Train_Info trainInfo : trainInfos) {
            Integer director = trainInfo.getDirector();
            String empName = (String) request.getSession().getAttribute("username");
            Integer empId = empService.getEmpIdByName(empName);
            if (trainStudentsService.getTrainStudents(trainId,empId) == null){
                String directorName = empService.getEmpNameByEmpId(director);
                Train_Search train_search = new Train_Search(trainId,trainTypeName,trainInfo.getPeriod(),directorName,"1");
                list.add(train_search);
            }
        }
        model.addAttribute("signUpSearchList",list);
        return "signupsearch";
    }

    @RequestMapping("/signUp/submit")
    @ResponseBody
    public String signUp(Integer trainId,HttpServletRequest request){
        String empName = (String)request.getSession().getAttribute("username");
        String password = (String)request.getSession().getAttribute("password");
        Integer empId = empService.getEmpIdByUser(empName, password);
        trainStudentsService.signUp(trainId,empId,"1");
        return "success";
    }

}
