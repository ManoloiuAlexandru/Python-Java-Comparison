import React, { useCallback, useState } from 'react';
import axios from "axios";
import logo from './logo.svg';
const baseURL = "http://127.0.0.1:8080/first/firstproblem";
export default function FirstProblem(this: any) {
    const [name, setName] = useState("");
    const [age, setAge] = useState("");

    const triggerAPI = useCallback(async () => {
        const res = await axios.post(baseURL, {
            "name": name,
            "age": age
        });
    }, [name, age]);
    const PrintName = useCallback((e: any) => {
        e.preventDefault()
        triggerAPI();
    }, [triggerAPI])

    const handleChangeName = useCallback((event: any) => {
        setName(event.target.value);
    }, []);
    const handleChangeAge = useCallback((event: any) => {
        setAge(event.target.value);
    }, []);
    return (
        <div>
            <div>
                <form onSubmit={PrintName}>
                    <label>
                        Name:
                        <input type="text" value={name} name="Name" onChange={handleChangeName} />
                    </label>
                    <label>
                        Age:
                        <input type="text" value={age} name="Age" onChange={handleChangeAge} />
                    </label>
                    <input type="submit" value="Print Result" />
                </form>
            </div>
        </div>
    );
}