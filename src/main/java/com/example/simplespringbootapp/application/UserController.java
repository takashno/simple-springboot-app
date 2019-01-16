package com.example.simplespringbootapp.application;

import com.example.simplespringbootapp.domain.model.User;
import com.example.simplespringbootapp.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class UserController {

    /** ユーザサービス. */
    @Autowired
    UserService userService;

    /**
     * ユーザ登録画面の表紙.
     *
     * @param mav mav
     * @return ユーザ登録画面
     */
    @GetMapping()
    public ModelAndView registView(ModelAndView mav) {
        mav.setViewName("pages/userRegist");
        return mav;
    }

    /**
     * ユーザ登録.
     *
     * @param mav mav
     * @param name 名前
     * @param organization 組織名
     * @param note メモ
     * @return リスト画面
     */
    @PostMapping("regist")
    public ModelAndView regist(ModelAndView mav,
                               @RequestParam("name") String name,
                               @RequestParam("organization") String organization,
                               @RequestParam("note") String note) {

        // ユーザ作成
        User user = new User();

        // 値の代入
        user.setName(name);
        user.setOrganization(organization);
        user.setNote(note);

        // ユーザ登録サービス
        userService.registUser(user);

        mav.addObject("successText","ユーザ登録完了しました。");
        mav.setViewName("redirect:/todo/list");
        return mav;
    }

}
