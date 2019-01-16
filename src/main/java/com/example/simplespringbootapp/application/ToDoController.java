package com.example.simplespringbootapp.application;

import com.example.simplespringbootapp.domain.model.Todo;
import com.example.simplespringbootapp.domain.model.User;
import com.example.simplespringbootapp.domain.service.ToDoService;
import com.example.simplespringbootapp.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("todo")
public class ToDoController {

    /** todoサービス. */
    @Autowired
    ToDoService toDoService;

    @Autowired
    UserService userService;

    /**
     * Todoリストの表示.
     *
     * @param mav mav
     * @param successText 成功テキスト
     * @return todoリスト画面
     */
    @RequestMapping("list")
    public ModelAndView list(ModelAndView mav,
                             @RequestParam(name="successText" , required=false) String successText) {

        List<Todo> todoList = toDoService.getTodoList();

        mav.addObject("successText",successText);
        mav.addObject("todoList",todoList);
        mav.setViewName("pages/list");
        return mav;
    }

    /**
     * Todo登録画面表示.
     *
     * @param mav mav
     * @return Todo登録画面
     */
    @GetMapping("regist")
    public ModelAndView registView(ModelAndView mav) {
        List<User> userList = userService.getUserList();
        mav.addObject("userList",userList);
        mav.setViewName("pages/regist");
        return mav;
    }

    /**
     * Todoの登録.
     *
     * @param mav mav
     * @param title タイトル名
     * @param description 説明
     * @param priority 優先度
     * @param userId ユーザID
     * @return リスト画面
     */
    @PostMapping("regist")
    public ModelAndView regist(ModelAndView mav,
                               @RequestParam("title") String title,
                               @RequestParam("description") String description,
                               @RequestParam("priority") String priority,
                               @RequestParam("userId") String userId,
                               @RequestParam("startTime") String start,
                               @RequestParam("endTime") String end) throws ParseException {

        // Todoの作成
        Todo todo = new Todo();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        System.out.println(userId);
        // 値の設定
        todo.setUserId(userId);
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setPriority(Integer.valueOf(priority));
        todo.setStart(sdf.parse(start));
        todo.setEnd(sdf.parse(end));

        // 登録
        toDoService.registTodo(todo);

        mav.addObject("successText","Todo登録完了しました。");
        mav.setViewName("redirect:/todo/list");
        return mav;
    }

    /**
     * Todoを完了にする.
     *
     * @param mav mav
     * @param todoId todoId
     * @param userId ユーザID
     * @return リスト画面
     */
    @PostMapping(value="action", params="finish")
    public ModelAndView finish(ModelAndView mav,
                               @RequestParam("todoId") String todoId,
                               @RequestParam("userId") String userId){

        System.out.println(todoId);
        System.out.println(userId);

        // Todoを完了にする
        toDoService.finishTodo(todoId,userId);

        mav.addObject("successText","Todoを完了しました。");
        mav.setViewName("redirect:/todo/list");
        return mav;
    }

    /**
     * Todoを消す.
     *
     * @param mav mav
     * @param todoId todoId
     * @param userId ユーザID
     * @return リスト画面
     */
    @PostMapping(value="action", params="delete")
    public ModelAndView delete(ModelAndView mav,
                               @RequestParam("todoId") String todoId,
                               @RequestParam("userId") String userId){

        // Todoを完了にする
        toDoService.deleteTodo(todoId,userId);

        mav.addObject("successText","Todoを削除しました。");
        mav.setViewName("redirect:/todo/list");
        return mav;
    }


}
