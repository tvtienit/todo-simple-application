import { put, call, takeEvery } from 'redux-saga/effects'
import { getTodos, addTodo, changeTodoState } from '../api/todos'; 
import { fetchTodosSuccess, fetchTodos, changeTodoStateSuccess } from '../actions';

function* applyTodos() {
    const data = yield call(getTodos);
    yield put(fetchTodosSuccess(data));
}

function* applyAddTodo(action) {
    const data = yield call(addTodo, action.content);
    if (data.isAdded) {
        yield put(fetchTodos());
    }
}

function* applyChangeTodoState(action) {
    const data = yield call(changeTodoState, action.todoId);
    if (data.isStateChanged) {
        yield put(changeTodoStateSuccess(action.todoId));
    }
}

export default function* rootSaga() {
    yield takeEvery('FETCH_TODO', applyTodos)
    yield takeEvery('ADD_TODO', applyAddTodo)
    yield takeEvery('CHANGE_TODO_STATE', applyChangeTodoState)
}