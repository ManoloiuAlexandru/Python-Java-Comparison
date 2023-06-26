import React, { useCallback, useState } from 'react';
import { Routes, Route, useNavigate } from 'react-router-dom';
import axios from "axios";
import Result from './Result';
const baseURL = "http://127.0.0.1:8080/first/secondproblem";
export default function SecondProblem(this: any) {
    const [side1, setSide1] = useState("");
    const [side2, setSide2] = useState("");
    const navigate = useNavigate();
    const navigateToResult = () => {

        navigate('/result');
    };
    const triggerAPI = useCallback(async () => {
        const res = await axios.post(baseURL, {
            "side1": Number(side1),
            "side2": Number(side2)
        });
    }, [side1, side2]);
    const PrintName = useCallback((e: any) => {
        e.preventDefault()
        triggerAPI();
        navigateToResult();
    }, [triggerAPI])

    const handleChangeSide1 = useCallback((event: any) => {
        setSide1(event.target.value.replace(/\D/g, ''));
    }, []);
    const handleChangeSide2 = useCallback((event: any) => {
        setSide2(event.target.value.replace(/\D/g, ''));
    }, []);
    return (
        <div>
            <div>
                <form onSubmit={PrintName}>
                    <label>
                        Side1:
                        <input type="number" value={side1} name="Side1" onChange={handleChangeSide1} />
                    </label>
                    <label>
                        Side2:
                        <input type="number" value={side2} name="Side2" onChange={handleChangeSide2} />
                    </label>
                    <input type="submit" value="Print Result" />
                </form>
            </div>
            <Routes>
                <Route path="/result" element={<RenderResult />} />
            </Routes>
        </div>
    );
}
function RenderResult() {
    return <Result />
}