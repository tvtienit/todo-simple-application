/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.models;

/**
 *
 * Simulate this model which interacts with DB
 */
import todo.entities.Todo;
import java.util.ArrayList;

public class TodoModel {

    private static TodoModel INSTANCE = null;
    private ArrayList<Todo> todoList;
    private int incrementTodoId;

    private TodoModel() {
        todoList = new ArrayList<Todo>();
        incrementTodoId = 0;
    }

    public static TodoModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TodoModel();

            /**
             * Some mock data
             */
            INSTANCE.add("todo 1");
            INSTANCE.add("todo 2");
            INSTANCE.add("todo 3");
        }

        return INSTANCE;
    }

    public boolean add(String todoContent) {
        return todoList.add(new Todo("todo-id-" + (incrementTodoId++), todoContent));
    }

    public boolean changeTodoState(String todoId) {
        Todo changedTodo = todoList.stream().filter(todo -> todo.getId().equals(todoId)).findFirst().orElse(null);
        if (changedTodo != null) {
            changedTodo.setDone(!changedTodo.isDone());
            return true;
        }
        
        return false;
    }

    public ArrayList<Todo> getTodos() {
        return todoList;
    }
}
