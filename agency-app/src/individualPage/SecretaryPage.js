import React, {useEffect, useState} from 'react';
import "../cssForIndividualPage/animate.css";
import "../cssForIndividualPage/icomoon.css";
import "../cssForIndividualPage/simple-line-icons.css";
import "../cssForIndividualPage/magnific-popup.css";
import "../cssForIndividualPage/style.css";
import axios from "axios";
import authHeader from "../auth/header";

const styles = {
    input: {
        fontSize: '16px',
        color: '#000000',
        lineHeight: '1.2',
        height: '62px',
        background: 'transparent',
        padding: '0 20px 0 23px'
    },
    divEnterData: {
        marginBottom: '20px',
        position: 'relative',
        width: '100%',
        backgroundColor: '#fff',
        borderRadius: '20px'
    },
    span: {
        paddingBottom: '30px',
        display: 'block',
        fontSize: '30px',
        color: 'black',
        lineHeight: '1.2',
        textAlign: 'center'
    },
    button: {
        marginRight: 'auto',
        marginLeft: 'auto',
        textAlign: 'left',
        fontFamily: 'sans-serif',
        width: '100%',
        paddingRight: '15px',
        paddingLeft: '15px',
        border: '1px solid white',
        borderRadius: '5px',
        fontSize: '1.5em',
        color: 'white',
        backgroundColor: 'transparent'
    },
    tableButton: {
        marginRight: 'auto',
        marginLeft: 'auto',
        textAlign: 'center',
        fontFamily: 'sans-serif',
        width: '100%',
        paddingRight: '5px',
        paddingLeft: '5px',
        border: '1px solid black',
        borderRadius: '5px',
        color: 'black',
        backgroundColor: 'transparent'
    },
    popUp: {
        top: '410px',
        width: '90%',
        position: 'absolute',
        zIndex: '999',
        margin: '0 0 0 -45%',
        left: '50%',
        display: 'none',
        paddingLeft: '30px',
        paddingTop: '5px',
        paddingBottom: '10px',
        paddingRight: '30px'
    },
    smallPopUp: {
        top: '350px',
        width: '60%',
        position: 'absolute',
        zIndex: '999',
        margin: '0 0 0 -30%',
        left: '50%',
        display: 'none',
        paddingLeft: '30px',
        paddingTop: '5px',
        paddingBottom: '5px',
        paddingRight: '30px'
    }
}


function SecretaryPage(props) {
    const lang = props.lang;
    let langConst = [];
    if (lang === 'en') {
        langConst.push('Status');
        langConst.push('Contract');
        langConst.push('Interview');
        langConst.push('');
        langConst.push('Просмотреть не рассмотренные заявки');
        langConst.push('Просмотреть одобренные заявки для внесения в систему');
        langConst.push('Не рассмотренные заявки');
    } else {
        langConst.push('Выберите действие');
        langConst.push('Просмотреть не рассмотренные заявки работодателей');
        langConst.push('Просмотреть одобренные заявки работодателей для внесения в систему');
        langConst.push('Не рассмотренные заявки работодателей');
        langConst.push('Просмотреть не рассмотренные заявки соискателей');
        langConst.push('Просмотреть одобренные заявки соискателей для внесения в систему');
        langConst.push('Не рассмотренные заявки соискателей');
    }

    const [applications, setApplications] = useState({applications: []})
    const [employeeContracts, setEmployeeContracts] = useState({employeeContracts: []})
    const [loading, setLoading] = useState(true);
    useEffect(() => {
        getApplications();
        getEmployeeContracts();
    }, [])
    const getApplications = () => {
        axios
            .get("http://localhost:8080/employerApplication/getAllByStatus/" + 'NOT-REVIEWED', {headers: authHeader()})
            .then(data => {
                setApplications({applications: data.data})
                setLoading(false)
            })
            .catch(err => alert(err))

    }
    const getEmployeeContracts = () => {
 /*       axios
            .get("http://localhost:8080/employeeContract/all")
            .then(data => {
                console.log(data.data)
                setEmployeeContracts({employeeContracts: data.data})
                setLoading(false)
            })
            .catch(err => alert(err))
*/
    }

    const updateApp = (idApp, newStatus) => {
        axios
            .put('http://localhost:8080/employerApplication/change-status/' + idApp + "/" + newStatus, null)
            .then()
            .catch((err) => alert(err))
    }

    const sendEmail = (subject, message) => {
        setLoading(true)
        const email = ['mariaz_email@mail.ru', subject, message]
        axios
            .post('http://localhost:8080/employerApplication/sendEmail', email)
            .then(() => {
                setLoading(false)
            })
            .catch((err) => alert(err))
    }

    if (loading) {
        return (
            <div className="spinner-border text-primary spinner" role="status">
                <span className="sr-only">Loading...</span>
                <br/><br/>
            </div>
        )
    }
    let appId;
    let index = 1;
    let index2 = 1;
    return (
        <div style={{marginTop: '-52px'}}>

            <link
                href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,600,400italic,700'
                rel='stylesheet' type='text/css'/>

            <div className="wrap-login100" id="application" style={styles.smallPopUp}>

                <form className="login100-form validate-form">
                    <div className="cl-btn-7" onClick={function () {
                        updateApp(appId, "NOT-REVIEWED");
                        document.getElementById("application").style.display = "none";
                    }}/>
                    <br/>
                    <span className="login100-form-title p-b-37"
                          style={styles.span}>{langConst[0]}</span>

                    <div style={styles.divEnterData}>
                        <input type="text" style={styles.input} value='Номер заявки: ' readOnly="readOnly"/>
                        <input type="text" style={styles.input} id='appNumber' value='' readOnly="readOnly"/>
                        <span className="focus-input100"/>
                    </div>
                    <div style={styles.divEnterData}>
                        <input type="text" style={styles.input} value={"Зарплата: "} readOnly="readOnly"/>
                        <input type="text" style={styles.input} id='appSalary' value='' readOnly="readOnly"/>
                        <span className="focus-input100"/>
                    </div>
                    <div style={styles.divEnterData}>
                        <input type="text" style={styles.input} value={'Специальность: '} readOnly="readOnly"/>
                        <input type="text" style={styles.input} id='appProfession' value='' readOnly="readOnly"/>
                        <span className="focus-input100"/>
                    </div>
                    <div className="container-login100-form-btn">
                        <button type='button' className="login100-form-btn"
                                style={{marginRight: '20px', marginBottom: '10px'}}
                                onClick={function () {
                                    document.getElementById('application').style.display = "none";
                                    document.getElementById('appOk').style.display = "block";
                                }}>Принять заявку
                        </button>
                        <button type='button' className="login100-form-btn"
                                style={{marginRight: '20px', marginBottom: '10px'}}
                                onClick={function () {
                                    document.getElementById('application').style.display = "none";
                                    document.getElementById('appNotOk').style.display = "block";
                                }}>Отказать в заявке
                        </button>
                    </div>
                </form>
            </div>

            <div className="wrap-login100" id='appOk' style={styles.smallPopUp}>
                <form className="login100-form validate-form">
                    <div className="cl-btn-7" onClick={function () {
                        updateApp(appId, "NOT-REVIEWED");
                        document.getElementById("appOk").style.display = "none";
                    }}/>
                    <br/>
                    <span className="login100-form-title p-b-37" style={styles.span}>Назначить стоимость услуг:</span>
                    <div style={styles.divEnterData}>
                        <input className="input100" id="cost" type="text" style={styles.input}
                               placeholder='Введите стоимсть'/>
                        <span className="focus-input100"/>
                    </div>
                    <div className="container-login100-form-btn">
                        <button type='button' className="login100-form-btn" onClick={function () {
                            updateApp(appId, "ACCEPTED");
                            sendEmail('Ваша заявка одобрена', 'Здравствуйте! Ваша заявка о поиске соискателя ' +
                                'одобрена. Стоимость услуг: ' + document.getElementById('cost').value + '. ' +
                                'Подтвердите согласие в вашем личном кабинете.');
                            document.getElementById('appOk').style.display = 'none';
                        }}>Принять заявку
                        </button>
                        <br/>
                    </div>
                </form>
            </div>
            <div className="wrap-login100" id='appNotOk' style={styles.smallPopUp}>
                <form className="login100-form validate-form">
                    <div className="cl-btn-7" onClick={function () {
                        updateApp(appId, "NOT-REVIEWED");
                        document.getElementById("appNotOk").style.display = "none";
                    }}/>
                    <br/>
                    <span className="login100-form-title p-b-37" style={styles.span}>Отказ в заявке:</span>
                    <div style={styles.divEnterData}>
                        <input className="input100" type="text" style={styles.input} id='rejectionReason'
                               placeholder='Введите причину отказа'/>
                        <span className="focus-input100"/>
                    </div>
                    <div className="container-login100-form-btn">
                        <button type='button' className="login100-form-btn" onClick={function () {
                            updateApp(appId, "REJECTED");
                            sendEmail('Ваша заявка отклонена', 'Здравствуйте! Ваша заявка о поиске соискателя ' +
                                'отклонена по следующей причине: ' + document.getElementById('rejectionReason').value);
                            document.getElementById('appOk').style.display = 'none';
                        }}>Отказать в заявке
                        </button>
                        <br/>
                    </div>
                </form>
            </div>


            <div className="wrap-login100" id="searchApplication" style={styles.popUp}>

                <form className="login100-form validate-form">
                    <div className="cl-btn-7" onClick={function () {
                        document.getElementById("searchApplication").style.display = "none";
                    }}/>
                    <br/>
                    <span className="login100-form-title p-b-37" style={styles.span}>{langConst[3]}</span>
                    <br/>
                    <table className="table list">
                        <thead style={{
                            color: 'white', backgroundColor: '#17a2b8db', border: '1px solid transparent',
                            borderRadius: '5px',
                        }}>
                        <tr>
                            <th scope="col">№</th>
                            <th scope="col">Application number</th>
                            <th scope="col">Employment type id</th>
                            <th scope="col">Check</th>
                        </tr>
                        </thead>
                        <tbody>
                        {applications.applications.map(app => (

                            <tr key={app.application_number}>
                                <th scope="row">{index++}</th>
                                <td>{app.application_number}</td>
                                <td>{app.employmentTypeId}</td>
                                <td>
                                    <button type='button' style={styles.tableButton} onClick={
                                        function () {
                                            document.getElementById("searchApplication").style.display = "none";
                                            updateApp(app.id, "CONSIDERED");
                                            appId = app.id;
                                            document.getElementById("appNumber").value = app.application_number;
                                            document.getElementById("appSalary").value = app.salary;
                                            document.getElementById("appProfession").value = app.professionId;
                                            document.getElementById("application").style.display = 'block';
                                        }}>Рассмотреть
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>

                </form>
            </div>

            <div className="wrap-login100" id="searchEmployeeContracts" style={styles.popUp}>
                <form className="login100-form validate-form">
                    <div className="cl-btn-7" onClick={function () {
                        document.getElementById("searchEmployeeContracts").style.display = "none";
                    }}/>
                    <br/>
                    <span className="login100-form-title p-b-37" style={styles.span}>{langConst[4]}</span>
                    <br/>
                    <table className="table list">
                        <thead style={{
                            color: 'white', backgroundColor: '#17a2b8db', border: '1px solid transparent',
                            borderRadius: '5px',
                        }}>
                        <tr>
                            <th scope="col">№</th>
                            <th scope="col">Employee name</th>
                            <th scope="col">Profession id</th>
                            <th scope="col">Check</th>
                        </tr>
                        </thead>
                        <tbody>
                        {employeeContracts.employeeContracts.map(contr => (

                            <tr key={contr.id}>
                                <th scope="row">{index2++}</th>
                                <td>{contr.name + ' ' + contr.surname}</td>
                                <td>{contr.professionId}</td>
                                <td>
                                    <button type='button' style={styles.tableButton} onClick={
                                        function () {
                                            document.getElementById("searchEmployeeContracts").style.display = "none";
                                       }}>Рассмотреть
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>

                </form>
            </div>
            <section id="fh5co-home" className="section section-6" data-section="home"
                     style={{backgroundImage: 'url(images/full_image_2.jpg)', paddingTop: '20px'}}
                     data-stellar-background-ratio="0.5">
                <div className="gradient"/>
                <div className="container">
                    <div className="text-wrap">
                        <div className="text-inner">
                            <div className="row">
                                <div className="col-md-8"
                                     style={{marginLeft: 'auto', marginRight: 'auto', textAlign: 'left'}}>
                                    <h1 className="to-animate">Secretary name</h1>
                                    <h2 className="to-animate"> Email: email</h2>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </section>

            <section id="fh5co-work" className="section section-6" data-section="work">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12 section-heading text-center">
                            <h2 className="to-animate">{langConst[0]}</h2>
                        </div>
                    </div>
                    <br/><br/>
                    <button type="submit" className="btn-block  btn-primary"
                            style={styles.button} onClick={function () {
                        document.getElementById("searchApplication").style.display = "block";
                    }}>1. {langConst[1]}</button>
                    <button type="submit" className="btn-block  btn-primary"
                            style={styles.button}>2. {langConst[2]}</button>
                    <button type="submit" className="btn-block  btn-primary"
                            style={styles.button} onClick={function () {
                        document.getElementById("searchEmployeeContracts").style.display = "block";
                    }}>3. {langConst[4]}</button>
                    <button type="submit" className="btn-block  btn-primary"
                            style={styles.button}>4. {langConst[5]}</button>
                </div>

            </section>
        </div>

    )
}

export default SecretaryPage;