package com.example.demo;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class StartController extends Thread{

    @GetMapping("/**")
    public  String common(HttpServletRequest request, Model model){
        return request.getServletPath().replace("/demo/", "");
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
