package com.mxnxv.todoapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxnxv.todoapp.model.ToDo;
import com.mxnxv.todoapp.repo.IToDoRepo;

@Service
public class ToDoService {

        @Autowired
        IToDoRepo repo;

        public List<ToDo> getAllToDoItems(){
            ArrayList<ToDo> todoList = new ArrayList<>();
            repo.findAll().forEach(todo -> todoList.add(todo));
        
            return todoList;
        }

        public ToDo getToDoItemById(Long id){
            return repo.findById(id).get();
        }

        public boolean updateStatus(Long id){
            ToDo toDo = getToDoItemById(id);

            toDo.setStatus("Completed");

            return saveOrUpdateToDoItem(toDo);
        }


        public boolean saveOrUpdateToDoItem(ToDo toDo){
            ToDo updated_ToDo = repo.save(toDo);
            if(getToDoItemById(updated_ToDo.getId())!=null)
                return true;
            else
                return false;
        }

        public boolean deleteToDoItem(Long id){
            repo.deleteById(id);

            try
            {
                getToDoItemById(id);
                return false;
            }catch(NoSuchElementException e)
            {
                return false;
            }
            // if(getToDoItemById(id)==null)
            //     return true;
            // else
            //     return false;
        }


}
