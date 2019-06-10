package com.example.springbootfirstwebapplication.controller;

import com.example.springbootfirstwebapplication.model.Todo;
import com.example.springbootfirstwebapplication.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
//@SessionAttributes("name") we dont need it because now we use Spring security

public class TodoController {
    // LoginService service = new LoginService(); // without Spring
    // Injected Automatically

    @Autowired
    TodoService service; // Spring can use Dependency Injection (autowired). When Spring service will find any instance of Autowired LoginService then take it and bring here

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    //это просто отображает содержимое базы данных (arraylist)
    public String showTodos(ModelMap model) {
        String name = getLoggedInUserName(model);
        model.put("todos", service.retrieveTodo(name));
        return "list-todos"; //  .jsp
    }


    @RequestMapping(value = "/add-todo", method = RequestMethod.GET) // по этой ссылке принимается запрос (заход)
    public String showAddTodoPage(ModelMap model) {
        //этот мотод просто принимает заход(запрос) по ссылке /add-todo
        model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "some default descr.", new Date(), false)); //default object
        // то есть когда кто-то заходит на эту страницу "/add-to do" то сразу ему создается и добавлется в модель новый TODO объект в качестве атрибута
        return "todo"; //  .jsp
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) { // Todo todo it will be automatically bound to a TODO bean
        // а этот метод уже обрабатывает запрос POST когда по той ссылке нажали submit, так как там форма с методом post.
//        if (result.hasErrors()) {
//            return "todo";
//        }
        // вместо model.put("todos", service.retrieveTodos((String) model.get("name")));
        // or REDIRECT to the URL with a list  return "redirect:/list-todos";

        //Binding result will show if the validation succeded or not
        if (result.hasErrors()) { // if errors user will be redirected back
            return "todo";//.jsp
        }
        service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(), // метод добавляет новое задание в список задача To Do
                false);
        return "redirect:/list-todos"; // .jsp
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET) // по этой ссылке принимается запрос (заход)
    public String deleteTodo(@RequestParam int id) {
        service.deleteTodo(id);
        return "redirect:/list-todos"; //  .jsp
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = service.retrieveTodo(id); // находит по (form:hidden path="id")ID данный бин (то есть задание)
        // to add TODO bean details on a screen add it to a model
        model.put("todo", todo); // и засовывает его во View под аттрубутом todo
        return "todo"; //.jsp that edits Description
    }
    //там челик редактирует описание и жмет Add вызвая метод POST

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        todo.setUser(getLoggedInUserName(model)); //  устанавливаем текущего юзера, так как дисприпш он просто не для юзера будет
        if (result.hasErrors()) {
            return "todo"; //.jsp
        } else {
            service.updateTodo(todo);
        }
        return "redirect:/list-todos"; //  .jsp
    }

    private String getLoggedInUserName(ModelMap model) {
        // return (String) model.get("name"); //getting fro session
        //or via Spring security
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();

    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //Date format is so and so dd/MM/yyyy across whole app
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }//this will same format whenever Date is used.
}

