import { connect } from 'react-redux'
import AddTodoForm from '../components/AddTodoForm';
import { addTodo } from '../actions';

const mapDispatchToProps = (dispatch) => ({
    addTodo: (todo) => dispatch(addTodo(todo))
});

export default connect(null, mapDispatchToProps)(AddTodoForm);