import React from 'react';
import { Form, Button } from 'react-bootstrap';

const AddTodoForm = ({ addTodo }) => {
    let todoInputRef;

    const addTodoHandler = (e) => {
        e.preventDefault();
        const todoValue = todoInputRef.value.trim();
        if (!!todoValue) {
            addTodo(todoValue);
            todoInputRef.value = "";
        }
    };

    return (
        <Form onSubmit={addTodoHandler}>
            <Form.Group controlId="formAddNewTodo">
                <Form.Label>New Todo:</Form.Label>
                <Form.Control type="text" placeholder="Enter Todo" ref={node => todoInputRef = node} />
                <Form.Text className="text-muted">
                    What is in your todo list?
                </Form.Text>
            </Form.Group>

            <Button variant="primary" type="submit">
                Add
            </Button>
        </Form>
    );
}

export default AddTodoForm;