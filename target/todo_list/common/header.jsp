<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #78ff47">
        <div>
            <a href="https://www.javarush.ru" class="navbar-brand">Todo List</a>
        </div>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%= request.getContextPath() %>/login" class="nav-link">Login</a></li>
            <li><a href="<%= request.getContextPath() %>/register" class="nav-link">Signup</a></li>
        </ul>
    </nav>
</header>