import React from 'react';
import axios from "axios";
import logo from './logo.svg';
import './App.css';
import Home from './components/Home'
import FirstProblem from './components/FirstProblem'
import { Routes, Route, useNavigate } from 'react-router-dom';
import Result from './components/Result';

export default function App() {
    const navigate = useNavigate();

    const navigateToHome = () => {

        navigate('/');
    };
    const navigateToFirstProblem = () => {

        navigate('/firstproblem');
    };
    return (
        <div>
            <div>
                <button onClick={navigateToHome}>Home</button>
                <button onClick={navigateToFirstProblem}>FirstProblem</button>
                <Routes>
                    <Route path="/" element={<RenderHome />} />
                    <Route path="/firstproblem" element={<RenderFirstProblem />} />
                    <Route path="/result" element={<RenderResult />} />
                </Routes>
            </div>
        </div>
    );
}
function RenderHome() {
    return <Home />
}
function RenderFirstProblem() {
    return <FirstProblem/>
}
function RenderResult() {
    return <Result />
}