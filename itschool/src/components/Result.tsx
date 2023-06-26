import React, { useCallback, useEffect, useState } from 'react';
export default function FirstProblem(this: any) {
    const [users, setUsers] = useState([])
    const fetchUserData = () => {
        fetch("http://127.0.0.1:8080/result")
            .then(response => {
                return response.json()
            })
            .then(data => {
                setUsers(data)
            })
    }
    useEffect(() => {
        fetchUserData()
    }, [])
    if (!users) return null;
    return (
        <div>
            {JSON.stringify(users)}
        </div>
    );
}