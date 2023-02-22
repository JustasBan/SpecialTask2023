import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import { UploadContext } from './UploadContextProvider';
import './styling/EmployeeTable.css'

function EmployeeTable() {
    const [employees, setEmployees] = useState([]);
    const [clicked, setClicked] = useContext(UploadContext);

    useEffect(() => {
        if (clicked) {
            axios.get('http://localhost:8080/getEmployees')
                .then(response => {
                    setEmployees(response.data);
                    setClicked(false)
                })
                .catch(error => {
                    console.error(error);
                });
        }

    }, [clicked]);

    return (
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Phone number</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map(item => (
                        <tr key={item.id}>
                            <td>{item.name}</td>
                            <td>{item.phoneNumber}</td>
                            <td>{item.email}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default EmployeeTable;
