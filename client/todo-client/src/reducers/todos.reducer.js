import { generateId } from '../utils/TodoHelper';

const todos = (state = [], action) => {
    switch (action.type) {
        case 'FETCH_TODO_SUCCESS':
            if (!!action.todos) {
                return [...action.todos.todos];
            }
            
        case 'CHANGE_TODO_STATE_SUCCESS':
            return state.map(todo => (
                todo.id === action.todoId ? {...todo, isDone: !todo.isDone} : todo
            ));

        default: 
            return state;
    }
}

export default todos;