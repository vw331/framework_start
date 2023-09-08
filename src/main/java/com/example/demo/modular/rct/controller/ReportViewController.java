package com.example.demo.modular.rct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/rct/meta")
public class ReportViewController {

    @GetMapping("/list")
    public String List(){
        return "/rct/meta/list";
    }

    @GetMapping("/{name}")
    public RedirectView report(HttpServletRequest request){
        // 根据报告的状态重定向
        return new RedirectView(request.getRequestURI() + "/result");
    }

    @GetMapping("/{name}/basic")
    public ModelAndView reportBasic(@PathVariable("name") String name) {
        ModelAndView mv = new ModelAndView("/rct/meta/basic");
        mv.addObject("name", name);
        return mv;
    }

    @GetMapping("/{name}/paper")
    public ModelAndView reportPaper(@PathVariable("name") String name) {
        ModelAndView mv = new ModelAndView("/rct/meta/paper");
        mv.addObject("name", name);
        return mv;
    }

    @GetMapping("/{name}/meta")
    public ModelAndView reportMeta(@PathVariable("name") String name) {
        ModelAndView mv = new ModelAndView("/rct/meta/meta");
        mv.addObject("name", name);
        return mv;
    }

    @GetMapping("/{name}/result")
    public ModelAndView reportResult(@PathVariable("name") String name) {
        ModelAndView mv = new ModelAndView("/rct/meta/result");
        mv.addObject("name", name);
        return mv;
    }
}
