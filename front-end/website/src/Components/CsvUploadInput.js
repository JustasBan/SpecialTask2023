import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import './styling/CsvUploadInput.css'
import { UploadContext } from './UploadContextProvider';

function CsvUploadInput() {

    const [file, setFile] = useState(null);
    const [feedbackMsg, setFeedbackMsg] = useState("Select file...");

    const [clicked, setClicked] = useContext(UploadContext);

    useEffect(() => {
        if (file) {
            setFeedbackMsg(file.name);
        }
    }, [file]);

    useEffect(() => {
        if (feedbackMsg === 'successful CSV upload to server' || feedbackMsg === 'Only CSV files are allowed') {
            const timer = setInterval(() => { setFeedbackMsg('Select file...') }, 1000)
            return () => {
                clearInterval(timer)
            }
        }
    }, [feedbackMsg]);


    const handleFileUpload = (event) => {
        const selectedFile = event.target.files[0];
        const fileType = selectedFile.name.split('.').pop();
        if (fileType !== 'csv') {
            setFeedbackMsg('Only CSV files are allowed');
            setFile(null);
        } else {
            setFile(selectedFile);
        }
    };

    const handleFormSubmit = async (event) => {
        event.preventDefault();
        if (file) {
            const formData = new FormData();
            formData.append('file', file);

            try {
                setFeedbackMsg("uploading...");
                await axios.post('http://localhost:8080/upload', formData);
                setFeedbackMsg("successful CSV upload to server");
                setClicked(true)
            }
            catch (error) {
                setClicked(true)
                if (String(error.response.data).includes('Error uploading file')) {
                    setFeedbackMsg("File contains invalid data")
                }
                else {
                    setFeedbackMsg("server error, try again later...")
                }
                console.error('Error uploading file:', error);
            }
        } else {
            setFeedbackMsg('Select file...');
        }
    };

    return (
        <form onSubmit={handleFormSubmit}>
            <div className="file-upload">
                <input type="file" id="file-upload" accept=".csv" onChange={handleFileUpload} />
                <label htmlFor="file-upload">{feedbackMsg}</label>
            </div>
            <button className='button' type="submit">Upload</button>
        </form>
    );
}

export default CsvUploadInput;