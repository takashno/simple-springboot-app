package com.example.simplespringbootapp.domain.service;

import com.example.simplespringbootapp.domain.mapper.TodoSelectMapper;
import com.example.simplespringbootapp.domain.model.Todo;
import com.example.simplespringbootapp.domain.model.TodoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.simplespringbootapp.domain.mapper.TodoMapper;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ToDoService {

    /** Todoマッパー(自動生成). */
    @Autowired
    TodoMapper todoMapper;

    /** Todoマッパー. */
    @Autowired
    TodoSelectMapper todoSelectMapper;

    /**
     * todoを登録する.
     *
     * @param todo todo
     */
    public void registTodo(Todo todo){

        if(todo != null) {
            String uuid = UUID.randomUUID().toString();
            todo.setId(uuid);

            todoMapper.insert(todo);
        }

    }

    /**
     * Todoを完了にする.
     *
     * @param todoId todoId
     * @param userId ユーザID
     */
    public void finishTodo(String todoId, String userId){

        if(todoId != null && userId != null){
            // Todoキーの生成
            TodoKey todoKey = new TodoKey();
            todoKey.setId(todoId);
            todoKey.setUserId(userId);

            // Todoの取得
            Todo todo = todoMapper.selectByPrimaryKey(todoKey);

            // Todoを完了にする
            todo.setPriority(9);

            // Todoの更新
            todoMapper.updateByPrimaryKeySelective(todo);

        }

    }

    /**
     * Todoを削除する.
     *
     * @param todoId todoId
     * @param userId ユーザID
     */
    public void deleteTodo(String todoId, String userId){

        if(todoId != null && userId != null){
            // Todoキーの生成
            TodoKey todoKey = new TodoKey();
            todoKey.setId(todoId);
            todoKey.setUserId(userId);

            // Todoの取得
            todoMapper.deleteByPrimaryKey(todoKey);

        }

    }

    /**
     * Todo一覧の取得
     *
     * @return TodoList
     */
    public List<Todo> getTodoList(){
        return todoSelectMapper.getTodoList();
    }

}
