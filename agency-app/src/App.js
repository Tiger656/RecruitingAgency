import React, {useCallback, useState} from 'react';
import HeaderMenu from "./startPage/HeaderMenu";
import InformationInPicture from "./startPage/InformationInPicture";
import './App.css';
import EmployerPage from "./individualPage/EmployerPage";
import EmployeePage from "./individualPage/EmployeePage";
import Contacts from "./startPage/Contacts";
import './startPage/Toggle.css';
import {BrowserRouter as Router, Route} from "react-router-dom";
import {AdminPage} from "./individualPage/AdminPage/AdminPage";
import 'bootstrap/dist/css/bootstrap.min.css';
import SecretaryPage from "./individualPage/SecretaryPage";
import {ManagerPageMain} from "./individualPage/ManagerPages/ManagerPageMain";


export const App = () => {
    const logo = 'https://s3.us-west-1.amazonaws.com/com.soar.p/images/profile/companylogos/iTechArt-7926.jpg';
    let [lang, setLang] = useState('ru');
    const employerEmail = 'employer_email@gmail.com';
    const employeeName = 'Alex Kov';
    const handler = useCallback(() => {
        if (lang === 'en') setLang('ru');
        else setLang('en');
    }, [lang]);

    return (
        <section className="App">
            <input className="checkbox" type="checkbox" id="codepen" onChange={handler}/>
            <label htmlFor="codepen"/>

            <HeaderMenu logo={logo} lang={lang} id='1'/>
            {/*<Router>*/}
            {/*    <Route exact path="/">*/}
            {/*        <InformationInPicture lang={lang}/>*/}
            {/*    </Route>*/}
            {/*    <Route path="/admin-page" exact component={AdminPage}/>*/}
            {/*    <Route path="/employer-page">*/}
            {/*        <EmployerPage lang={lang} email={employerEmail}/>*/}
            {/*    </Route>*/}
            {/*    <Route path="/employee-page">*/}
            {/*        <EmployeePage lang={lang} name={employeeName} email={employerEmail}/>*/}
            {/*    </Route>*/}
            {/*    <Route path="/secretary-page">*/}
            {/*        <SecretaryPage lang={lang} email={employerEmail}/>*/}
            {/*    </Route>*/}
            {/*    <Route path="/manager-page" exact component={ManagerPageMain}/>*/}
            {/*</Router>*/}
            <Contacts/>
        </section>
    )
}
export default App;

