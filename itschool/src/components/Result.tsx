import React, { useCallback, useState } from 'react';
import axios from "axios";
import logo from './logo.svg';
const baseURL = "http://127.0.0.1:8080/result";
export default function FirstProblem(this: any) {
    const [post, setPost] = React.useState<any>();
    React.useEffect(() => {
        axios.get(baseURL).then((response) => {
            setPost(response.data);
        });
    }, []);

    if (!post) return null;

    return (
        <div>
            <p>{post}</p>
        </div>
    );
}