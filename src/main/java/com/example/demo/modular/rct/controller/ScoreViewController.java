package com.example.demo.modular.rct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/rct/score")
public class ScoreViewController {

    @GetMapping()
    public RedirectView index(HttpServletRequest request) {
        return new RedirectView(request.getRequestURI() + "/paper");
    }

    @GetMapping("/paper")
    public String paper() {
        return "/rct/score/paper.html";
    }

}
