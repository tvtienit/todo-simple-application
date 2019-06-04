/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.handlers;

/**
 *
 * @author cpu11291
 */
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.WebUtils;
import todo.entities.Todo;
import todo.models.TodoModel;

public class TodoServlet extends HttpServlet {

    /**
     * Get all todos
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

        ArrayList<Todo> todos = TodoModel.getInstance().getTodos();
        writeTodos(response, todos);
    }

    /**
     * Add todo
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        
        Map<String, Object> body = extractBody(request);

        switch (body.get("request_name").toString()) {
            case "add_todo":
                doAddTodoAction(request, response, body);
                break;
            
            case "change_todo_state":
                doChangeTodoStateAction(request, response, body);
                break;
        }
    }
    
    private void doAddTodoAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> body) throws IOException {
        String todoContent = body.get("todo").toString();
        boolean isAdded = TodoModel.getInstance().add(todoContent);

        writeAddOperationResult(response, isAdded);
    }
    
    private void doChangeTodoStateAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> body) throws IOException {
        String todoId = body.get("todo_id").toString();
        boolean isStateChanged = TodoModel.getInstance().changeTodoState(todoId);

        writeChangeStateOperationResult(response, isStateChanged);
    }

    private void writeAddOperationResult(HttpServletResponse response, boolean isAdded) throws IOException {
        PrintWriter out = response.getWriter();
        JsonObject data = new JsonObject();

        data.addProperty("isAdded", isAdded);

        out.print(data);
        out.flush();
    }
    
    private void writeChangeStateOperationResult(HttpServletResponse response, boolean isStateChanged) throws IOException {
        PrintWriter out = response.getWriter();
        JsonObject data = new JsonObject();

        data.addProperty("isStateChanged", isStateChanged);

        out.print(data);
        out.flush();
    }

    private void writeTodos(HttpServletResponse response, ArrayList<Todo> todos) throws IOException {
        PrintWriter out = response.getWriter();
        JsonObject data = new JsonObject();
        JsonArray todosArray = new JsonArray();

        todos.stream().forEach((todo) -> {
            JsonObject todoObj = new JsonObject();
            todoObj.addProperty("id", todo.getId());
            todoObj.addProperty("content", todo.getContent());
            todoObj.addProperty("isDone", todo.isDone());
            todosArray.add(todoObj);
        });

        data.add("todos", todosArray);

        out.print(data);
        out.flush();
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> extractBody(HttpServletRequest request) throws IOException {
        String str, wholeStr = "";
        BufferedReader br = request.getReader();
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        if (StringUtils.isNotBlank(wholeStr)) {
            return JSON.parseObject(wholeStr, Map.class);
        }
        return getParameterMap(request);
    }

    public static final Map<String, Object> getParameterMap(HttpServletRequest request) {
        return WebUtils.getParametersStartingWith(request, null);
    }
}
