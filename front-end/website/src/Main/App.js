import './App.css'
import '../Components/CsvUploadInput'
import CsvUploadInput from '../Components/CsvUploadInput';
import EmployeeTable from '../Components/EmployeeTable';
import React from 'react';
import {UploadContextProvider} from '../Components/UploadContextProvider';

function App() {
  return (
    <div>
      <div className="headerClass">
        CSV uploader
      </div>
      <UploadContextProvider>
        <CsvUploadInput />
        <EmployeeTable />
      </UploadContextProvider>
    </div>
  );
}

export default App;
