import React from 'react';
import { Form } from 'react-bootstrap';

const Todo = ({ id, content, isDone, changeState }) => {
    return (
        <Form.Check
            type = "checkbox"
            label = {content}
            checked = {isDone}
            onChange={() => changeState(id)}
        />
    );
}

export default Todo;