<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Todo Management Application</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>

</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="https://www.javarush.ru" class="navbar-brand"> Todo List</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" method="post" class="nav-link">My Todos</a></li>

            <li class="text-center">  <p>Username: ${username}</p></li>

        </ul>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%=request.getContextPath()%>/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Todos</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" method="post"
                class="btn btn-success">Add Todo</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Target Date</th>
                <th>Todo Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach items="${listTodo}" var="listTodo" >

                <tr>
                    <td><c:out value="${listTodo.getTitle()}" /></td>
                    <td><c:out value="${listTodo.getDescription()}" /></td>
                    <td><c:out value="${listTodo.getTargetdate()}" /></td>
                    <td><c:out value="${listTodo.getDone()}" /></td>



                    <td><a href="edit?id=<c:out value='${listTodo.getId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${listTodo.getId()}' />">Delete</a></td>

                    <!--  <td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
                              <button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>



        </table>
    </div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>