package service;

import dao.TodoDao;
import dao.UserDao;
import model.Todo;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class TodoService implements TodoServiceInt{

    private final TodoDao todoDao;
    private final UserDao userDao;
    public TodoService(TodoDao todoDao, UserDao userDao){
        this.todoDao = todoDao;
        this.userDao = userDao;
    }

    @Override
    public List<Todo> todoList(String userName){
        Integer userId = userDao.getUserId(userName);
        List<Todo> todoList = todoDao.getAllTodoByUserId(userId);
        return todoList;
    }
    @Override
    public Todo createNewTodo(String title, String description, String username, boolean isDone, Date date){
        Todo newTodo = new Todo(title, username, description,  date, isDone);
        return newTodo;
    }
    @Override
    public void addNewTodo(Todo todo){
        String userName = todo.getUsername();
        Integer userId = userDao.getUserId(userName);
        boolean isAdded = todoDao.insertTodo(todo, userId);
        System.out.println("Пользователь добавил задачу? " + isAdded);
    }

    @Override
    public void updateTodo2(Todo todo, BigInteger todoId) // вариант №2 метода  проводит обновление задачи, но ничего не возвращает
    {
        boolean isUpdate = todoDao.updateTodo(todo, todoId);
        System.out.println("Пользователь обновил задачу? " + isUpdate);
    }
    @Override
    public void deleteTodo(BigInteger todoId){
        boolean isDelete = todoDao.deleteTodo(todoId);
        System.out.println("Пользователь удалил задачу? " + isDelete);
    }
    @Override
    public Todo getTodo(BigInteger todoId){
        return todoDao.getTodoById(todoId);
    }
}
