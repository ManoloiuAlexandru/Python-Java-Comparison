import React, { useCallback, useEffect, useState } from 'react';
import { Routes, Route, useNavigate } from 'react-router-dom';
import axios from "axios";
import logo from './logo.svg';
import Result from './Result';
const baseURL = "http://127.0.0.1:8080/currencyconvertor";

export default function CurrencyConvertor(this: any) {
    const [amount, setAmount] = useState("");
    const [currencyToConvertTo, setcurrencyToConvertTo] = useState("");
    const navigate = useNavigate();
    const [currency, setCurrency] = useState("");
    const [data, setUsers] = React.useState<any>();
    const fetchUserData = () => {
        fetch("http://127.0.0.1:8080/currencys")
            .then(response => {
                return response.json()
            })
            .then(data => {
                setUsers(data)
            })
    }
    useState(() => {
        fetchUserData()
    });
    const navigateToResult = () => {

        navigate('/result');
    };
    const triggerAPI = useCallback(async () => {
        const res = await axios.post(baseURL, {
            "currency": currency,
            "amount": amount,
            "currencyToConvertTo": currencyToConvertTo
        });
    }, [currency, amount, currencyToConvertTo]);
    const PrintName = useCallback((e: any) => {
        e.preventDefault();
        triggerAPI();
    }, [triggerAPI])

    const handleChangeCurrency = useCallback((event: any) => {
        setCurrency(event.target.value);
    }, []);
    const handleChangeAmount = useCallback((event: any) => {
        setAmount(event.target.value);
    }, []);
    const handleChangeCurrencyToConvertTo = useCallback((event: any) => {
        setcurrencyToConvertTo(event.target.value);
    }, []);
    if (!data) return null;
    return (
        <div>
            <p>Choose currency</p>
            <form onSubmit={PrintName}>
                <select value={currency} onChange={handleChangeCurrency}>
                    {Object.keys(data).map((currency: any, index: any) => (
                        <option value={currency}>{currency}</option>
                    ))}

                </select>
                <div>
                    <label>
                        Amount to convert :
                        <input type="text" value={amount} name="amount" onChange={handleChangeAmount} />
                    </label>
                </div>
                <p>Choose currency to convert to</p>
                <select value={currencyToConvertTo} onChange={handleChangeCurrencyToConvertTo}>
                    {Object.keys(data).map((currencyToConvertTo: any, index: any) => (
                        <option value={currencyToConvertTo}>{currencyToConvertTo}</option>
               ))}

                </select>
                
                <input type="submit" value="Send data to server" />
            </form>
            <button onClick={navigateToResult}>Result</button>
            <Routes>
                <Route path="/result" element={<RenderResult />} />
            </Routes>
        </div>
    );
}
function RenderResult() {
    return <Result />
}