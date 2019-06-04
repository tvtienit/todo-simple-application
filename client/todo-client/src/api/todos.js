import { API_HOST, TODO_API } from '../utils/Config';

const headers = {
    "Content-type": "application/json; charset=UTF-8"
}

export const getTodos = () => {
    return fetch(`${API_HOST}/${TODO_API}`, {
        method: 'GET',
        headers
    }).then(res => res.json());
};

export const addTodo = (todoContent) => {
    return fetch(`${API_HOST}/${TODO_API}`, {
        method: 'POST',
        body: JSON.stringify({
            todo: todoContent,
            request_name: "add_todo"
        }),
        headers
    }).then(res => res.json());
};

export const changeTodoState = (todoId) => {
    return fetch(`${API_HOST}/${TODO_API}`, {
        method: 'POST',
        body: JSON.stringify({
            todo_id: todoId,
            request_name: "change_todo_state"
        }),
        headers
    }).then(res => res.json());
};