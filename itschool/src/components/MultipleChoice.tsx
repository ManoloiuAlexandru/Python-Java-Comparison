import React, { useCallback, useEffect, useState } from 'react';
export default function MultipleChoice(this: any) {
    const [active, setActive] = useState(false);

    const handleClick = () => {
        setActive(!active);
    };

    return (
        <div>
            <p>message = 'Python\n' <br/>
                print(message)
</p>
            <p
                onClick={handleClick}
                style={{ color: active ? "green" : "black" }}
            >
                a. 'Python'
            </p>
            <p
                onClick={handleClick}
                style={{ color: active ? "red" : "black" }}
            >
                b. ' Python'
            </p>
            <p
                onClick={handleClick}
                style={{ color: active ? "red" : "black" }}
            >
                c. ' Python\n'
            </p>
        </div>
    );
};