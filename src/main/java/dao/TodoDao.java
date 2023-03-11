package dao;

import connection.ConnectionToDB;
import model.Todo;
import model.User;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TodoDao {

    /*
    Метод для получения списка всех задач пользовтеля по его id
     */
    public List<Todo> getAllTodoById(Integer id){
        List<Todo> todoList;
        Session session = null;
        String hql = "from Todo where users_id = :ID";
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<Todo> query = session.createQuery(hql, Todo.class);
            query.setParameter("ID", id);
            todoList = query.getResultList();

        } finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
        return todoList;
    }
    /*
    Метод для получения отдельной задачи пользовтеля по её id
     */
    public Todo getTodoById(BigInteger todoId){
        Todo todo;
        Session session = null;
        String hql = "select title, username, description,targetdate,isDone from Todo where id = :TODOID";
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<Todo> query = session.createQuery(hql);
            query.setParameter("TODOID", todoId);
            todo = session.get(Todo.class, todoId);

        } finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
        return todo;
    }
    public boolean insertTodo(User user, Todo todo){
        boolean todoAdded;
        Integer userId = user.getId();
        String title = todo.getTitle();
        String description = todo.getDescription();
        String username = todo.getUsername();
        boolean isDone = todo.getDone();
        Date date = todo.getTargetdate();
        Session session = null;
        // "insert into Todo (title, username, description, targetdate, isDone, users_id) VALUES (:TITLE, :USERNAME, :DESCRIPTION, :TARGETDATE, :ISDONE, :USERSID)";
        String hql = "insert into Todo (title, username, description, targetdate, isDone, users_id) VALUES (:TITLE, :USERNAME, :DESCRIPTION, :TARGETDATE, :ISDONE, :USERSID)";
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<Todo> query = session.createQuery(hql);
            query.setParameter("TITLE", title);
            query.setParameter("USERNAME", username);
            query.setParameter("DESCRIPTION", description);
            query.setParameter("TARGETDATE", date);
            query.setParameter("ISDONE", isDone);
            query.setParameter("USERSID", userId);
            todoAdded = true;

        }

        finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
        return todoAdded;

    }
    private void addTodo(User user, Todo todo){
        List<Todo> list = new ArrayList<>();
        list.add(todo);
    }
}
