// TODO: Fix the TodoService class to use the TodoCreateDto and TodoUpdateDto classes
package com.brayanobisto.todo_api.services;

import com.brayanobisto.todo_api.dtos.TodoCreateDto;
import com.brayanobisto.todo_api.dtos.TodoUpdateDto;
import com.brayanobisto.todo_api.entities.Todo;
import com.brayanobisto.todo_api.repositories.TodoRepository;
import com.brayanobisto.todo_api.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public Todo createTodo(TodoCreateDto todoCreateDto) {
        Todo todo = new Todo();
        todo.setTitle(todoCreateDto.getTitle());
        todo.setIsCompleted(todoCreateDto.getIsCompleted());

        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, TodoUpdateDto todoUpdateDto) {
        Todo todo = getTodoById(id);

        EntityUtils.copyNonNullProperties(todoUpdateDto, todo);

        System.out.println(todo);

        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
