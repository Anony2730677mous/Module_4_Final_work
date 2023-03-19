# Module_4_Final_work
Веб-проект, позволяющий пользователю вносить свои задачи в список и хранить их в базе данных.
===========================================
Задание: Реализовать логику работы REST приложения, с помощью сервлетов, базы данных, hibernate + тестирование кода.
*******************************************
Требования:

    Это должен быть Maven проект с использованием следующих технологий: сервлеты, jsp, jstl, hibernate, субд mysql, docker
    Для запуска приложения будет использоваться Tomcat 9.
    Необходимо создать структуру данных в СУБД.
    Пользователь может регистрироваться в приложении под своим логином/паролем.
    Пользователь может создавать, редактировать, удалять задачи с указанием их названия, описания, даты наличия их выполения. 
    Логика работы должна быть покрыта тестами. для этого будет использованы JUnit, TestContainers(пока не реализовано).
    Должно быть предусмотрено логирование событий в приложении(пока не реализовано).
    Сделанный проект залить в свой репозиторий на GitHub.
    Часть проекта(jsp страницы, login, user - сервлеты) реализована на основе проекта, размещенного на странице 
    https://www.javaguides.net/2019/10/build-todo-app-using-jsp-servlet-jdbc-and-mysql.html
*******************************************
При разработке проекта использовались следующие инструменты:


1. java, java EE - для написания работы бизнес-логики и работы сервлетов.
2. jsp - для создания динамически обновляемых страниц.
3. log4j2 - для создания журнала событий.(пока не реализовано).
4. junit5 - для создания модульных тестов отдельных методов. (пока не реализовано).
5. tomcat 9  - произведена конфигурация сервера для запуска собранного приложения.
6. maven - проект создан на основе артефакта webapp.
7. docker - СУБД MySql размещена в докер-контейнере.
8. mysql - субд обеспечивает хранение и доступ к данным зарегистрированных пользователей.
9. hibernate - организовано взаимодействие между субд и сервисным слоем приложения.
10. mysql workbench - инструмент для работы с СУБД MySql
*******************************************
В процессе создания веб приложения были проведены такие этапы:

Создан maven проект на основе готового шаблона webapp, в котором в pom файле собраны необходимые для работы приложения зависимости. 
Создан в папке WEB-INF файл web.xml, в котором прописана начальная html страница, с которой начинается представление приложения в браузере пользователя. 
В СУБД MySQL создана база данных с таблицами, проведена конфигурация полей таблиц. Для этого использовался  mysql workbench.
Созданы классы, отвечающие за представление таблиц СУБД в приложении. Настроены зависимости между таблицами БД и полями классов. 
Созданы слои, отвечающие за разную логику работы приложения. В этих слоях реализованы соответстующие классы. Измененены и добавлены jsp файлы для взаимодействия с сервлетами.
Сами jsp файлы, обеспечивающие логику get/post запросов, находятся в папке /views. 
Проведена сборка проекта в maven(mvn clean install). 
Выполнена настройка подключения БД к инструменту hibernate, обеспечивающему работы логики выполения запросов к БД.
Проверены во временном тестовом классе прохождения запросов в БД.
Сама СУБД MySql размещена в докер-контейнере для удобства обращения к ней.
Проведена конфигурация сервера Tomcat 9 для запуска и проверки работоспособности приложения. 
*******************************************
Описание рабочего процесса:
После старта сервера загружается начальная страница приложения, указанная в файле web.xml. Пользователю предлагается ввести свой логин и пароль для входа
в приложение, если он зарегистрирован, или зарегистрироваться, если этого не сделано.
После входа в приложение пользователь может создать новую задачу для себя или посмотреть список всех созданных задач, который отобразится в виде таблицы.
При выборе списка всех задач отобразится таблица с задачами, где доступно их редактирование, удаление, а также название и краткое описание задач. 
Также есть время и факт выполнения задачи.
Созданные пользователи и задачи хранятся в базе данных в виде таблиц. Каждый пользователь имеет доступ только к своим задачам.