package service;

import dao.TodoDao;
import dao.UserDao;
import model.Todo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class TodoService implements TodoServiceInt{
    private static final Logger LOGGER = LogManager.getLogger(TodoService.class);

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
        LOGGER.info("The user with the username: " + userName + " received the entire task list in size: " + todoList.size());
        return todoList;
    }
    @Override
    public Todo createNewTodo(String title, String description, String username, boolean isDone, Date date){
        Todo newTodo = new Todo(title, username, description,  date, isDone);
        LOGGER.info("The task with title: " + title + " was created by a user with a username: " + username);
        return newTodo;
    }
    @Override
    public void addNewTodo(Todo todo){
        String userName = todo.getUsername();
        Integer userId = userDao.getUserId(userName);
        boolean isAdded = todoDao.insertTodo(todo, userId);
        LOGGER.info("A user with the a username: " + userName + " added a task? " + isAdded);
        System.out.println("Пользователь добавил задачу? " + isAdded);
    }

    @Override
    public void updateTodo2(Todo todo, BigInteger todoId) { // вариант №2 метода  проводит обновление задачи, но ничего не возвращает
        boolean isUpdate = todoDao.updateTodo(todo, todoId);
        LOGGER.info("A user with the a username: " + todo.getUsername() + " update a task? " + isUpdate);
        System.out.println("Пользователь обновил задачу? " + isUpdate);
    }
    @Override
    public void deleteTodo(BigInteger todoId){
        boolean isDelete = todoDao.deleteTodo(todoId);
        LOGGER.info("Deleted todo with id_s: " + todoId);
        System.out.println("Пользователь удалил задачу? " + isDelete);
    }
    @Override
    public Todo getTodo(BigInteger todoId){
        return todoDao.getTodoById(todoId);
    }
}
