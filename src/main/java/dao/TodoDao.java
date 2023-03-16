package dao;

import connection.ConnectionToDB;
import model.Todo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


public class TodoDao implements TodoInt{
    private final String GET_ALL_TODO = "from Todo where users_id = :ID";
    private final String GET_TODO_BY_ID = "select title, username, description,targetdate,isDone from Todo where id = :TODOID";
    private final String ADD_TODO = "INSERT INTO todos (description,  is_done, target_date, username, title, users_id) VALUE  (?, ?, ?, ?, ?, ?)";
    private final String DELETE_TODO =  "delete from Todo where id = :ID";
    private final String UPDATE_TODO = "update todos set description = ?, is_done = ?, target_date = ?, username = ?,\n" +
            "                 title = ?\n" +
            "where id = ?";

    public TodoDao() {
    }

    /*
    Метод для получения списка всех задач пользовтеля по его id
     */
    @Override
    public List<Todo> getAllTodoByUserId(Integer userId){
        List<Todo> todoList;
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<Todo> query = session.createQuery(GET_ALL_TODO, Todo.class);
            query.setParameter("ID", userId);
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
    @Override
    public Todo getTodoById(BigInteger todoId){
        Todo todo;
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<Todo> query = session.createQuery(GET_TODO_BY_ID);
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
    /*
    Метод для вставки новой задачи в таблицу задач по номеру пользователя, создавшего задачу
     */
    @Override
    public boolean insertTodo(Todo todo, Integer userId){
        boolean todoAdded;
        String title = todo.getTitle();
        String description = todo.getDescription();
        String username = todo.getUsername();
        boolean isDone = todo.getDone();
        Date date = todo.getTargetdate();
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<Todo> query = session.createSQLQuery(ADD_TODO);
            query.setParameter(1, description);
            query.setParameter(2, isDone);
            query.setParameter(3, date);
            query.setParameter(4, username);
            query.setParameter(5, title);
            query.setParameter(6, userId);
            query.executeUpdate();
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
    /*
    Метод для удаления задачи по её id
     */
    @Override
    public boolean deleteTodo(BigInteger todoId){
        boolean isDelete;
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<Todo> query = session.createQuery(DELETE_TODO);
            query.setParameter("ID", todoId);
            query.executeUpdate();
            isDelete = true;
        } finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
        return isDelete;
    }
    /*
    Метод для обновления существующей задачи по её id
     */
    @Override
    public boolean updateTodo(Todo todo, BigInteger todoId){
        boolean todoAdded;
        String title = todo.getTitle();
        String description = todo.getDescription();
        String username = todo.getUsername();
        boolean isDone = todo.getDone();
        Date date = todo.getTargetdate();
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<Todo> query = session.createSQLQuery(UPDATE_TODO);
            query.setParameter(1, description);
            query.setParameter(2, isDone);
            query.setParameter(3, date);
            query.setParameter(4, username);
            query.setParameter(5, title);
            query.setParameter(6, todoId);
            query.executeUpdate();
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
}
