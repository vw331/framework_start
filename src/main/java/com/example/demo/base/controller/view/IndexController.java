package com.example.demo.base.controller.view;

import com.example.demo.base.service.ElasticService;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping()
public class IndexController extends Thread{

    @Resource
    ElasticService esService;

    @Value("${wkhtmltopdfCommandpath}")
    private String wkhtmltopdfpath;

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
        Object article = esService.getDoc("sunhao_guidezh_document_paper_zh", "1");
        ModelAndView mv = new ModelAndView("article");
        mv.addObject("article", article);
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

    @GetMapping("/pdf")
    public ResponseEntity<StreamingResponseBody> createPdf(@RequestParam(value = "url", required = true) String url){
        Pdf pdf = new Pdf();
        pdf.addParam(new Param("--encoding", "utf-8"));
        pdf.addParam(new Param("--print-media-type"));
        pdf.addPageFromUrl(url);

        StreamingResponseBody responseBody = response -> {
            try {
                byte[] buffer = pdf.getPDF();
                response.write(buffer);
            }catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
        };

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(responseBody);
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
