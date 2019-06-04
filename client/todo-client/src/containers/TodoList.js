import { connect } from 'react-redux'
import TodoList from '../components/TodoList';
import { changeTodoState } from '../actions';

const mapStateToProps = (state) => ({
    todos: state.todos
});

const mapDispatchToProps = (dispatch) => ({
    changeState: (todoId) => dispatch(changeTodoState(todoId))
});

export default connect(mapStateToProps, mapDispatchToProps)(TodoList);