/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.entities;

/**
 *
 * @author cpu11291
 */
public class Todo {
    private String id;
    private String content;
    private Boolean done;
    
    public Todo(String id, String content) {
        this.id = id;
        this.content = content;
        this.done = false;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getId() {
        return id;
    }
}
