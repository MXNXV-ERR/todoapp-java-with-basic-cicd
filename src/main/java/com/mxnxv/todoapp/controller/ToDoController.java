package com.mxnxv.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mxnxv.todoapp.model.ToDo;
import com.mxnxv.todoapp.service.ToDoService;



@Controller
public class ToDoController {

    @Autowired
    private ToDoService service;

    @GetMapping("/")
    String viewAllToDoItems(Model model,@ModelAttribute("message") String msg){
        model.addAttribute("listt", service.getAllToDoItems());
        model.addAttribute("message", msg);

        return "ViewToDo";
    }

    @GetMapping("/uts/{id}")
    String updateToDoStatus(@PathVariable Long id,RedirectAttributes rAttributes){
        if(service.updateStatus(id))
        {
            rAttributes.addFlashAttribute("message","Success");
            return "redirect:/";
        }
        rAttributes.addFlashAttribute("message","hopeless");
        return "redirect:/";

    }

    @GetMapping("/add")
    public String addToDoItem(Model model) {
        model.addAttribute("todo", new ToDo());
        return "AddTodoItem";
    }

    @PostMapping("/saveToDoItem")
    public String saveToDoItem(ToDo todo, RedirectAttributes rAttributes) {
        if(service.saveOrUpdateToDoItem(todo)){
            rAttributes.addFlashAttribute("message","Save Success!!!");
            return "redirect:/";
        }
        rAttributes.addFlashAttribute("message","Save Failure!!!");
        return "redirect:/add";
    }

    @GetMapping("/edit/{id}")
    public String editToDoItem(@PathVariable Long id,Model model) {
        model.addAttribute("todo", service.getToDoItemById(id)); 
        return "EditToDoItem";
    }

    @PostMapping("/editSave")
    public String editSaveToDoItem(ToDo todo,RedirectAttributes rAttributes) {
        if(service.saveOrUpdateToDoItem(todo)){
            rAttributes.addFlashAttribute("message","Edit Success!!!");
            return "redirect:/";
        }
        rAttributes.addFlashAttribute("message","Edit Failure!!!");
        return "redirect:/edit/"+todo.getId();
    }

    @GetMapping("/delete/{id}")
    public String deteleToDoItem(@PathVariable Long id,RedirectAttributes rAttributes) {
        if(service.deleteToDoItem(id)){
            rAttributes.addFlashAttribute("message","Delete Success");
            return "redirect:/";
        }
        rAttributes.addFlashAttribute("message","Delete Failure");
        return "redirect:/";
    }
    
    
    
}
