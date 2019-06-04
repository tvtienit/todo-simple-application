import { combineReducers } from 'redux';
import todos from './todos.reducer';
import { routerReducer } from 'react-router-redux';

export default combineReducers({
  todos,
  routing: routerReducer
});