package dao;

import model.Todo;
import java.math.BigInteger;
import java.util.List;

public interface TodoInt {
    List<Todo> getAllTodoByUserId(Integer userId);
    Todo getTodoById(BigInteger todoId);
    boolean insertTodo(Todo todo, Integer userId);
    boolean deleteTodo(BigInteger todoId);
    boolean updateTodo(Todo todo, BigInteger todoId);

}
