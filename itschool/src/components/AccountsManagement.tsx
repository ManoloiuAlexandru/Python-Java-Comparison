import React, { useCallback, useState } from 'react';
import { Routes, Route, useNavigate } from 'react-router-dom';
import axios from "axios";
import logo from './logo.svg';
import Result from './Result';
import PasswordChecklist from "react-password-checklist";
const baseURL = "http://127.0.0.1:8080/accountsmanagemnt";

export default function AccountsManagement(this: any) {
    const [website, setWebsite] = useState("");
    const [accountName, setAccountName] = useState("");
    const [accountPassword, setAccountPassword] = useState("");
    const [accountEmail, setAccountEmail] = useState("");
    const [accountSecret, setAccountSecret] = useState("");
    const [recoveryEmail, setRecoveryEmail] = useState("");
    const navigate = useNavigate();
    const navigateToResult = () => {

        navigate('/result');
    };
    const triggerAPI = useCallback(async () => {
        const res = await axios.post(baseURL, {
            "website": website,
            "accountName": accountName,
            "accountPassword": accountPassword,
            "accountEmail": accountEmail,
            "accountSecret": accountSecret,
            "recoveryEmail": recoveryEmail
        });
    }, [website, accountName, accountPassword, accountEmail, accountSecret, recoveryEmail]);
    const PrintName = useCallback((e: any) => {
        e.preventDefault();
        triggerAPI();
    }, [triggerAPI])

    const handleChangeWebsite = useCallback((event: any) => {
        setWebsite(event.target.value);
    }, []);
    const handleChangeAccountName = useCallback((event: any) => {
        setAccountName(event.target.value);
    }, []);
    const handleChangeAccountPassword = useCallback((event: any) => {
        setAccountPassword(event.target.value);
    }, []);
    const handleChangeAccountEmail = useCallback((event: any) => {
        setAccountEmail(event.target.value);
    }, []);
    const handleChangeAccountSecret = useCallback((event: any) => {
        setAccountSecret(event.target.value);
    }, []);
    const handleChangeRecoveryEmail = useCallback((event: any) => {
        setRecoveryEmail(event.target.value);
    }, []);
    return (
        <div>
            <div>
                <form onSubmit={PrintName}>
                    <div>
                    <label>
                        Webiste:
                        <input type="text" value={website} name="website" onChange={handleChangeWebsite} />
                        </label>
                    </div>
                    <div>
                    <label>
                        Account Name:
                        <input type="text" value={accountName} name="accountName" onChange={handleChangeAccountName} />
                        </label>
                    </div>
                    <div>
                    <label>
                            Account Password:
                            <input type="password" value={accountPassword} name="accountPassword" onChange={handleChangeAccountPassword} />
                        </label>
                        <div>
                        <PasswordChecklist
                            rules={["capital", "specialChar", "minLength", "number", "lowercase"]}
                            minLength={8}
                            value={accountPassword}
                            messages={{
                                minLength: "The minimum length of the password should be 8.",
                                specialChar:
                                    "The password should contain at least one special character.",
                                number: "The password should contain at least one numeric letter.",
                                capital: "The password should contain at least one uppercase letter.",
                                match: "Password and password again should match.",
                                lowercase: "The password should contain at least one lowercase letter.",
                            }}
                            />
                            </div>
                    </div>
                    <div>
                    <label>
                        Account Email:
                        <input type="text" value={accountEmail} name="accountEmail" onChange={handleChangeAccountEmail} />
                        </label>
                    </div>
                    <div>
                        <label>
                            Account Secret:
                            <input type="text" value={accountSecret} name="accountSecret" onChange={handleChangeAccountSecret} />
                        </label>
                    </div>
                    <div>
                        <label>
                            Recovery Email:
                            <input type="text" value={recoveryEmail} name="recoveryEmail" onChange={handleChangeRecoveryEmail} />
                        </label>
                    </div>

                    <input type="submit" value="Send data to server"  />
                </form>
                <button onClick={navigateToResult}>Result</button>
                <Routes>
                    <Route path="/result" element={<RenderResult />} />
                </Routes>
            </div>
        </div>
    );
}
function RenderResult() {
    return <Result />
}