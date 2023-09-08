package com.example.demo.base.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping()
public class IndexController extends Thread{

    @GetMapping("/index")
    public  String index(HttpServletRequest request, Model model){
        return "index";
    }

    @GetMapping("/searchByPICO")
    public ModelAndView searchPICO(){
        ModelAndView mv = new ModelAndView("pico");
        return mv;
    }

    @GetMapping("/searchResult")
    public ModelAndView searchResult() {
        ModelAndView mv = new ModelAndView("search");

        Map<String, List<Map<String, Object>>> dict = new HashMap<>();
        List<Map<String, Object>> sourceList = new ArrayList<>();
        Map<String, Object> sourceItem1 = new HashMap<>();
        sourceItem1.put("key", "现代预防医学");
        sourceItem1.put("value", "现代预防医学");
        sourceList.add(sourceItem1);

        dict.put("source", sourceList);
        mv.addObject("dict", dict);
        mv.addObject("count", 9527);
        return mv;
    }

    @GetMapping("/article/{id}")
    public ModelAndView article(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("article");
        return mv;
    }

    @GetMapping("/meta_analysis_tools")
    public String metaAnalysis(){
        return "meta_analysis_tools";
    }

    @GetMapping("/network_analysis_tools")
    public String networkAnalysis(){
        return "network_analysis_tools";
    }

    @PostMapping("/api/post")
    @ResponseBody
    public Map<String, Object> mockPost(Model model){

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("code", 0);
        result.put("message", "新增成功");
        result.put("data", null);
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
