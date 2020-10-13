package com.heylr.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("bigScreen")
public class BigScreenController {

    @RequestMapping("page")
    public ModelAndView downFrame(){
        ModelAndView modelAndView = new ModelAndView();
        log.info("1111111");
        modelAndView.setViewName("download");
        return modelAndView;
    }
}
