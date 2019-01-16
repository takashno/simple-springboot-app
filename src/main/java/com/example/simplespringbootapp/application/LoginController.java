package com.example.simplespringbootapp.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {

    /**
     * ログイン画面の表示.
     *
     * @param mav mav
     * @return ログイン画面
     */
    @GetMapping()
    public ModelAndView list(ModelAndView mav) {
        mav.setViewName("pages/login");
        return mav;
    }
}
