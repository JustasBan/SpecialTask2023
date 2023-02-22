import React, { useState } from 'react';

export const UploadContext = React.createContext({});

// create provider
export function UploadContextProvider(props) {
    const [clicked, setClicked] = useState(true);

    return (
        <UploadContext.Provider value={[clicked, setClicked]}>
            {props.children}
        </UploadContext.Provider>
    );
}
