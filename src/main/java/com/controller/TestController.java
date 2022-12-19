package com.controller;

import com.data.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/testScope")
public class TestController {

    @Autowired
    private Order order;

    private String name;

    private static int aa;
    private int bb;

    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public void userProfile(@PathVariable("username") String username) {
        name = username;
        order.setOrderNum(name);
        try {
            for(int i = 0; i < 100; i++) {
                System.out.println(
                        Thread.currentThread().getId()
                                + "name:" + name
                                + "--order:"
                                + order.getOrderNum());
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    @RequestMapping("/test")
    public void test()
    {
        aa++;
        bb++;

        System.out.println("aa: " + aa + " ,bb: " + bb);
    }

    @RequestMapping("/hello/{id}")
    public String getDetails(@PathVariable(value="id") String id,
                             @RequestParam(value="param1", required=true) String param1,
                             @RequestParam(value="param2", required=false) String param2)
    {

        System.out.println();

        return  "test";
    }
}
