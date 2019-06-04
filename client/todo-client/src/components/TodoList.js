import React from 'react';
import Form from 'react-bootstrap/Form';
import Todo from './Todo';

const TodoList = ({ todos, changeState }) => {
    return (
        <Form style={{paddingTop: "10px"}}>
            {todos.map(todo =>
                <Todo
                    key={todo.id}
                    id={todo.id}
                    {...todo}
                    changeState = {changeState}
                />
            )}
        </Form>
    );
};

export default TodoList