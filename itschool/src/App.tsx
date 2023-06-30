import React from 'react';
import axios from "axios";
import logo from './logo.svg';
import './App.css';
import Home from './components/Home'
import FirstProblem from './components/FirstProblem'
import { Routes, Route, useNavigate } from 'react-router-dom';
import Result from './components/Result';
import SecondProblem from './components/SecondProblem';
import MultipleChoice from './components/MultipleChoice';
import CurrencyConvertor from './components/CurrencyConvertor';
import AccountsManagement from './components/AccountsManagement';

export default function App() {
    const navigate = useNavigate();

    const navigateToHome = () => {

        navigate('/');
    };
    const navigateToFirstProblem = () => {

        navigate('/firstproblem');
    };
    const navigateToSecondProblem = () => {

        navigate('/secondproblem');
    };
    const navigateToMultipleChoice = () => {

        navigate('/multiplechoice');
    };
    const navigateToCurrencyConvertor = () => {

        navigate('/currencyconvertor');
    };
    const navigateToAccountsManagement = () => {

        navigate('/accountsmanagement');
    };
    return (
        <div>
            <div>
                <button onClick={navigateToHome}>Home</button>
                <button onClick={navigateToFirstProblem}>FirstProblem</button>
                <button onClick={navigateToSecondProblem}>SecondProblem</button>
                <button onClick={navigateToMultipleChoice}>MultipleChoice</button>
                <button onClick={navigateToCurrencyConvertor}>CurrencyConvertor</button>
                <button onClick={navigateToAccountsManagement}>AccountManagement</button>
                <Routes>
                    <Route path="/" element={<RenderHome />} />
                    <Route path="/firstproblem/*" element={<RenderFirstProblem />} />
                    <Route path="/result/*" element={<RenderResult />} />
                    <Route path="/secondproblem/*" element={<RenderSecondProblem />} />
                    <Route path="/multiplechoice/*" element={<RenderMultipleChoice />} />
                    <Route path="/currencyconvertor/*" element={<RenderCurrencyConvertor />} />
                    <Route path="/accountsmanagement/*" element={<RenderAccountManagement />} />
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
function RenderSecondProblem() {
    return <SecondProblem />
}
function RenderMultipleChoice() {
    return <MultipleChoice />
}
function RenderCurrencyConvertor() {
    return <CurrencyConvertor />
}
function RenderAccountManagement() {
    return <AccountsManagement />
}