/** Fetch Todos */
export const fetchTodos = () => ({
    type: 'FETCH_TODO'
})

export const fetchTodosSuccess = (todos) => ({
    type: 'FETCH_TODO_SUCCESS',
    todos
})

/** Add Todo */
export const addTodo = (todoContent) => ({
    type: 'ADD_TODO',
    content: todoContent
})

/** Change Todo State */
export const changeTodoState = (todoId) => ({
    type: 'CHANGE_TODO_STATE',
    todoId
})

export const changeTodoStateSuccess = (todoId) => ({
    type: 'CHANGE_TODO_STATE_SUCCESS',
    todoId
})