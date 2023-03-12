package service;

import model.Todo;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface TodoServiceInt {
    List<Todo> todoList(String userName);
    Todo createNewTodo(String title, String description, String username, boolean isDone, Date date);
    void addNewTodo(Todo todo);
    void updateTodo2(Todo todo, BigInteger todoId);
    void deleteTodo(BigInteger todoId);
    Todo getTodo(BigInteger todoId);
}
