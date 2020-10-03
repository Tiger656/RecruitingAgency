import React, {useEffect, useState} from 'react';
import "../cssForIndividualPage/animate.css";
import "../cssForIndividualPage/icomoon.css";
import "../cssForIndividualPage/simple-line-icons.css";
import "../cssForIndividualPage/magnific-popup.css";
import "../cssForIndividualPage/style.css";
import "../startPage/SignIn.css";
import axios from "axios";

const styles = {
    divEnterData: {
        marginBottom: '20px',
        position: 'relative',
        width: '100%',
        backgroundColor: '#fff',
        borderRadius: '20px'
    },
    input: {
        fontSize: '16px',
        color: '#000000',
        lineHeight: '1.2',
        height: '62px',
        background: 'transparent',
        padding: '0 20px 0 23px'
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
        textAlign: 'center',
        fontFamily: 'sans-serif',
        width: '400px',
        paddingRight: '15px',
        paddingLeft: '15px',
        border: '3px solid white',
        borderRadius: '99px',
        fontSize: '2em',
        color: 'white',
        backgroundColor: 'transparent'
    }
};


function request() {
    document.getElementById("request").style.display = "block";

}

function requestNone() {
    document.getElementById("request").style.display = "none";
    document.getElementById("userLogin").value = "";
    document.getElementById("userPassword").value = "";
}


function EmployerPage(props) {
    const email = props.email;
    const lang = props.lang;
    let langConst = [];
    if (lang === 'en') {
        langConst.push('Request');
        langConst.push('Requests');
        langConst.push('Status');
        langConst.push('Contracts');
        langConst.push('Contract');
        langConst.push('Submit your application');
    } else {
        langConst.push('Заявка');
        langConst.push('Заявки');
        langConst.push('Статус');
        langConst.push('Контракты');
        langConst.push('Контракт');
        langConst.push('Подать заявку');
    }

    const id = 1;
    let is_suspended;
    const [contract, setContract] = useState({contract: []})
    const [professions, setProfessions] = useState({professions: []})

    const [applications, setApplications] = useState([])
    const [employer, setEmployer] = useState({employer: [], isLoading: true})

    useEffect(() => {
        getEmployer();
        getContract();
        getProfessions();
    }, [])
    const getEmployer = () => {
        axios
            .get("http://localhost:8080/employer/" + id)
            .then(data => {
                setEmployer({employer: data.data, isLoading: false})
            })
            .catch(err => alert(err))
    }

    const getContract = () => {
        axios
            .get("http://localhost:8080/employer-contract/" + id)
            .then(data => {
                setContract({contract: data.data})
            })
            .catch(err => alert(err))

    }
    const getProfessions = () => {
        axios
            .get("http://localhost:8080/profession/all")
            .then(data => {
                setProfessions({professions: data.data})
            })
            .catch(err => alert(err))

    }
    const createApp = (app) => {
        axios
            .post('http://localhost:8080/employerApplication/create', app)
            .then(resp => setApplications([...applications, resp.data]))
            .catch((err) => alert(err))
    }
    if (contract.contract.is_suspended === true)
        is_suspended = 'Контракт приостановлен';
    else is_suspended = 'Контракт действителен';
    return (

        <div style={{marginTop: '-75px'}}>

            <link
                href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,600,400italic,700'
                rel='stylesheet' type='text/css'/>
            <div className=" section-0">

                <div className="wrap-login100" id="request" style={{
                    top: '80px', width: '360px', position: 'absolute', zIndex: '999',
                    margin: '0 0 0 -180px', left: '50%', display: 'none',
                    paddingLeft: '30px', paddingTop: '5px', paddingBottom: '30px', paddingRight: '30px'
                }}>

                    <form className="login100-form validate-form">
                        <div className="cl-btn-7" onClick={requestNone}/>
                        <br/>
                        <span className="login100-form-title p-b-37" style={styles.span}>{langConst[0]}</span>


                        <div style={styles.divEnterData} data-validate="Enter your email">
                            <input className="input100" type="button" style={styles.input} id="profession"
                                   value="Специальность" onClick={
                                function () {
                                    let value = [];
                                    professions.professions.map((val) => {
                                        value.push(val.name);
                                        //  return (<text style={styles.input}>{val.name}</text>)
                                    })
                                    console.log(value);
                                    value.map((val, ind) => {
                                        return <div key={ind}><input style={styles.input} value={val}/></div>
                                    })
                                }
                            }>

                            </input>
                            <span className="focus-input100"/>
                        </div>

                        <div style={styles.divEnterData} data-validate="Enter password">
                            <input className="input100" type="text" id="userPassword" name="pass"
                                   style={styles.input}
                                   placeholder="Заработная плата"/>
                            <span className="focus-input100"/>
                        </div>
                        <div style={styles.divEnterData} data-validate="Enter password">
                            <input className="input100" type="text" id="workDay" name="pass"
                                   style={styles.input}
                                   placeholder="Рабочий график"/>
                            <span className="focus-input100"/>
                        </div>
                        <div style={styles.divEnterData} data-validate="Enter password">
                            <input className="input100" type="text" id="experience" name="pass"
                                   style={styles.input}
                                   placeholder="Опыт работы"/>
                            <span className="focus-input100"/>
                        </div>

                        <div style={styles.divEnterData} data-validate="Enter password">
                            <input className="input100" type="text" id="age" name="pass"
                                   style={styles.input}
                                   placeholder="Возраст"/>
                            <span className="focus-input100"/>
                        </div>

                        <div style={styles.divEnterData} data-validate="Enter password">
                            <input className="input100" type="text" id="loc" name="pass"
                                   style={styles.input}
                                   placeholder="Город проживания соискателя"/>
                            <span className="focus-input100"/>
                        </div>

                        <div style={styles.divEnterData} data-validate="Enter password">
                            <input className="input100" type="text" id="location" name="pass"
                                   style={styles.input}
                                   placeholder="Адрес расположения офиса"/>
                            <span className="focus-input100"/>
                        </div>

                        <div style={styles.divEnterData} data-validate="Enter password">
                            <input className="input100" type="text" id="pluses" name="pass"
                                   style={styles.input}
                                   placeholder="Плюсы работы в компании"/>
                            <span className="focus-input100"/>
                        </div>

                        <div style={styles.divEnterData} data-validate="Enter password">
                            <input className="input100" type="text" id="comments" name="pass"
                                   style={styles.input}
                                   placeholder="Комментарии"/>
                            <span className="focus-input100"/>
                        </div>

                        <div className="container-login100-form-btn">
                            <button className="login100-form-btn">
                                {langConst[5]}
                            </button>
                        </div>
                    </form>

                </div>
                <div id="dropDownSelect1"/>
                <script src="../jquery-3.2.1.min.js"/>
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
                                    <h1 className="to-animate">{employer.employer.name}</h1>
                                    <h2 className="to-animate"> Email: {email}</h2>
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
                            <h2 className="to-animate">{langConst[1]}</h2>
                        </div>
                    </div>
                    <br/><br/>
                    <div className="row row-bottom-padded-sm">
                        <div className="col-md-4 col-sm-6">
                            <a href="../images/work_1.jpg" className="fh5co-project-item image-popup to-animate">
                               <div className="fh5co-text">
                                    <h2>{langConst[0]} 1</h2>
                                    <span>{langConst[2]}: </span>
                                </div>
                            </a>
                        </div>
                        <div className="col-md-4 col-sm-6">
                            <a href="../images/work_2.jpg" className="fh5co-project-item image-popup to-animate">
                               <div className="fh5co-text">
                                    <h2>{langConst[0]} 2</h2>
                                    <span>{langConst[2]}: </span>
                                </div>
                            </a>
                        </div>

                        <div className="clearfix visible-sm-block"/>

                        <div className="col-md-4 col-sm-6">
                            <a href="../images/work_3.jpg" className="fh5co-project-item image-popup to-animate">
                               <div className="fh5co-text">
                                    <h2>{langConst[0]} 3</h2>
                                    <span>{langConst[2]}: </span>
                                </div>
                            </a>
                        </div>
                        <div className="col-md-4 col-sm-6">
                            <a href="../images/work_4.jpg" className="fh5co-project-item image-popup to-animate">
                               <div className="fh5co-text">
                                    <h2>{langConst[0]} 4</h2>
                                    <span>{langConst[2]}: </span>
                                </div>
                            </a>
                        </div>
                    </div>
                    <br/><br/>
                    <button type="submit" className="btn-block  btn-primary"
                            style={styles.button} onClick={request}>{langConst[5]}</button>
                </div>

            </section>

            <section id="fh5co-work" className="section section-6" data-section="work">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12 section-heading text-center">
                            <h2 className="to-animate">{langConst[4]}</h2>
                        </div>
                    </div>
                    <br/><br/>
                    <div className="row row-bottom-padded-sm">
                        <div>
                            <a href="../images/work_1.jpg" className="fh5co-project-item image-popup to-animate">
                                <div className="fh5co-text">
                                    <h2>{is_suspended}</h2>
                                </div>
                                <div style={{
                                    color: "black",
                                    textAlign: 'left',
                                    paddingBottom: '20px',
                                    paddingLeft: '20px',
                                    paddingRight: '20px'
                                }}>
                                    <h10> Дата создания контракта: {contract.contract.contract_creation_date}</h10>
                                    <br/>
                                    <h10> Дата окончания контракта: {contract.contract.contract_end_date}</h10>
                                    <br/>
                                    <h10> Тип контракта: {contract.contract.contractTypeId}</h10>

                                </div>
                            </a>
                        </div>
                        <div className="clearfix visible-sm-block"/>
                    </div>
                    <text>Нажмите на контракт для скачивания файла с подробным описанием</text>
                </div>
            </section>
        </div>

    )
}

export default EmployerPage;