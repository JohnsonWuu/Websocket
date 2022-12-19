package com.controller;

import com.entity.User;
import com.errorcode.CmsErrorCode;
import com.errorcode.CmsLogMessage;
import com.errorcode.ErrorCode;
import com.errorcode.LogMessage;
import com.exception.CmsCustomRuntimeException;
import com.exception.CustomRuntimeException;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value="/403", method= RequestMethod.GET)
    public String showAccessDeniedPage(){

        System.out.println("showAccessDeniedPage");
        return "403";
    }

    @RequestMapping("/MyFirstPage")
    public String greeting(@RequestParam(value="title", required=false, defaultValue="xiao") String title, Model model) {
        model.addAttribute("name", title);
        return "index2";
    }

    @RequestMapping("/createUser")
    public String createUser(@RequestParam(value="name") String name, Model model)throws FileNotFoundException {
        User user = userService.userRegister(name,18);

        System.out.println("[Controller]createUser,use id: " + user.getId());

        model.addAttribute("name", user.getName());
        return "index2";
    }

    @GetMapping("/delete")
    public String delete()
    {
        System.out.println("This is the delete request");
        return "This is the delete request";
    }

    @GetMapping("/helloo")
    public void helloo() {
        System.out.println("helloo");
       // return "helloo";
    }

    @GetMapping("/hello3")
    public void hello3() {
        System.out.println("hello3");
    }

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello");
        String bookingNo = "100";
        throw new CustomRuntimeException(ErrorCode.UNDEFINED_CODE, LogMessage.SYSTEM_DELETED,bookingNo);

    }

    @GetMapping("/hello2")
    public String hello2() {
        System.out.println("hello2");

        String bookingNo = "100";
        String status = "kkkk";

        //String message2 = ErrorCode.SYSTEM_CANCELLED.getAsFormattedText(bookingNo,status);
       // System.out.println("message2: " + message2);

        throw new CustomRuntimeException(ErrorCode.SYSTEM_CANCELLED, LogMessage.SYSTEM_CANCELLED,bookingNo,status);
    }

    @GetMapping("/helloCms1")
    public String helloCms1() {
        System.out.println("helloCms1");

        String bookingNo = "100";
        String status = "kkkk";

        //String message2 = ErrorCode.SYSTEM_CANCELLED.getAsFormattedText(bookingNo,status);
        // System.out.println("message2: " + message2);

        throw new CmsCustomRuntimeException(CmsErrorCode.CMS_ERROR_ONE, CmsLogMessage.CMS_ERROR,bookingNo,status);
    }

    @GetMapping("/hello4")
    public String hello4() {
        System.out.println("hello4");
        String bookingNo = "100";
        throw new CustomRuntimeException(ErrorCode.UNDEFINED_CODE);

    }

    @GetMapping("/hello5")
    public String hello5() {
        System.out.println("hello5");
        String bookingNo = "100";
        throw new CustomRuntimeException(ErrorCode.TOTE_EMPTY,bookingNo);

    }
}
