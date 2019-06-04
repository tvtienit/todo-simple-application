import React, { useEffect } from 'react';
import TodoList from '../containers/TodoList';
import AddTodoForm from '../containers/AddTodoForm';
import { Container } from 'react-bootstrap';
import '../styles/App.css';
import { fetchTodos } from '../actions';

const styleAppTitle = {
    fontWeight: "bold",
    color: "#c0c0c0",
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center'
}

const onAppInit = (dispatch) => {
    dispatch(fetchTodos());
}

const App = ({dispatch}) => {
    useEffect(() => {
        onAppInit(dispatch);
    }, []);

    return (
        <Container style={{ paddingTop: "100px" }}>
            <h1 style={styleAppTitle}> What to be done?</h1>
            <AddTodoForm />
            <TodoList />
        </Container>
    );
}

export default App;