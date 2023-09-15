package com.example.demo.modular.rct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/rct/report")
public class ReportViewController {

    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping
    public RedirectView index(HttpServletRequest request){
        return new RedirectView(request.getRequestURI() + "/list");
    }

    @GetMapping("/list")
    public String List(){
        return "/rct/report/list";
    }

    @GetMapping("/{name}")
    public RedirectView report(HttpServletRequest request){
        // 根据报告的状态重定向
        return new RedirectView(request.getRequestURI() + "/result");
    }

    @GetMapping("/{name}/basic")
    public ModelAndView reportBasic(@PathVariable("name") String name) {
        ModelAndView mv = new ModelAndView("/rct/report/basic");
        mv.addObject("name", name);
        return mv;
    }

    @GetMapping("/{name}/paper")
    public ModelAndView reportPaper(@PathVariable("name") String name) {
        ModelAndView mv = new ModelAndView("/rct/report/paper");
        mv.addObject("name", name);
        return mv;
    }

    @GetMapping("/{name}/meta")
    public ModelAndView reportMeta(@PathVariable("name") String name) {
        ModelAndView mv = new ModelAndView("/rct/report/meta");
        mv.addObject("name", name);
        return mv;
    }

    @GetMapping("/{name}/result")
    public ModelAndView reportResult(@PathVariable("name") String name) {
        ModelAndView mv = new ModelAndView("/rct/report/result");
        mv.addObject("name", name);
        return mv;
    }

    @GetMapping("/{name}/generate")
    public RedirectView reportView(HttpServletRequest request, @PathVariable("name") String name, @RequestParam(name = "sourceId", required = true) String sourceId) throws IOException {
        // 创建html文件
        Context context = new Context();
        context.setVariable("name", name);
        String htmlString = templateEngine.process("/rct/report/template/theme1.html", context);
        // 生成文件
        File file = new ClassPathResource("templates").getFile();
        String filePath = "C:\\projects\\framework_start\\src\\main\\resources\\static\\html\\"+ name +".html";
        Writer writer = new FileWriter(filePath);
        writer.write(htmlString);
        writer.flush();
        // 重定向到静态文件上去
        String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        //return new RedirectView("/static/" + URLEncoder.encode(name,"UTF-8"));
        return new RedirectView("/rct/report/wrap?url=" +domain + "/html/" + URLEncoder.encode(name,"UTF-8") + ".html" );
    }

    @GetMapping("/wrap")
    public String reportWrap(@RequestParam(name = "url", required = true) String url, Model model) {
        model.addAttribute("url", url);
        return "/rct/report/wrap";
    }
}
