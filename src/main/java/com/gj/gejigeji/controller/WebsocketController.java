package com.gj.gejigeji.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("websocket")
public class WebsocketController {

    @Value("${server.port}")
    private String port;

    public static final String INDEX = "websocket/index";

    @RequestMapping("index/{accountID}")
    public ModelAndView index(@PathVariable("accountID")String accountID){
        ModelAndView mav = new ModelAndView(INDEX);
        mav.addObject("port", port);
        mav.addObject("accountID",accountID);
        return mav;
    }
}
