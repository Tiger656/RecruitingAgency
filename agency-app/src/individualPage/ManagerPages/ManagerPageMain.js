import React, {useEffect, useState} from 'react';
import "../../cssForIndividualPage/animate.css";
import "../../cssForIndividualPage/icomoon.css";
import "../../cssForIndividualPage/simple-line-icons.css";
import "../../cssForIndividualPage/magnific-popup.css";
import "../../cssForIndividualPage/style.css";
import axios from "axios";
import SideBlock from "./components/SideBlock";


const styles = {
    img: {
        width: '100px'
    },
    a: {
        display: 'inline-block',
        color: 'black'
    },
    nav: {
        backgroundColor: 'white',
        position: 'fixed',
        top: '0',
        right: '0',
        left: '0',
        zIndex: '1030'
    },
    span: {
        paddingBottom: '30px',
        display: 'block',
        fontSize: '30px',
        color: 'black',
        lineHeight: '1.2',
        textAlign: 'center'
    },
    divSign: {
        zIndex: '999',
        width: '280px',
        position: 'absolute',
        margin: '0 0 0 -140px',
        left: '50%',
        display: 'none',
        paddingLeft: '30px',
        paddingRight: '30px',
        paddingTop: '30px',
        paddingBottom: '30px'
    },
    divFacebookGoogle: {
        paddingBottom: '50px',
        display: 'flex',
        justifyContent: 'center'
    },
    divEnterData: {
        marginBottom: '20px',
        position: 'relative',
        width: '100%',
        backgroundColor: '#fff',
        borderRadius: '20px'
    },
    txt: {
        fontSize: '16px',
        lineHeight: '1.4',
        color: '#999999'
    }

}

let employerApplicationId;
let employeeApplicationId;

function showInterviewForm(id) {
    document.getElementById("interviewForm").style.display = "block";
    console.log(id);
    if (employeeApplicationId === undefined) {
        employeeApplicationId = id;
    }
    if (employerApplicationId === undefined) {
        employerApplicationId = id;
    }
}


function closeInterviewForm() {
    document.getElementById("interviewForm").style.display = "none";
}



export default function ManagerPageMain(props) {

    const [sideBlockStyle, setSideBlockStyle]= React.useState({display:'none'})
    const [sideBlockData, setSideBlockData]= React.useState({employer: 'a', profession: 'b', salary: 'c', features: []})
    const name = props.name;
    const email = props.email;
    const [experts,setExperts] = useState({experts:[]})
    const [erApplications,setErApplications] = useState({erApplications:[]})

    useEffect(() => {
        getExperts();
        getEmployerApplications();
    }, [])
    const getExperts = () => {
        axios
            .get("http://localhost:8080/expert")
            .then(data => {
                console.log(data.data)
                setExperts({experts: data.data})
            })
            .catch(err => alert(err))

    }
    const getEmployerApplications = () => {
        axios
            .get("http://localhost:8080/employerApplication/all-for-manager")
            .then(data => {
                console.log(data.data)
                setErApplications({erApplications: data.data})
            })
            .catch(err => alert(err))
    };

    function addToTempAppEmployer(application, evnt){
        employerApplicationId = application.id;
        setSideBlockStyle({display:'block'});
        setSideBlockData(application);
        document.getElementsByClassName("employer-section")[0].style.display = "none";
        let list = document.getElementsByClassName("add-temp")
        for (let i = 0; i < list.length; i++) {
            list.item(i).style.display = "none";
        }
        list = document.getElementsByClassName("create-interview")
        for (let i = 0; i < list.length; i++) {
            list.item(i).style.display = "block";
        }

    }

    function addToTempAppEmployee(application, evnt){
        employeeApplicationId = application.id;
        setSideBlockStyle({display:'block'});
        setSideBlockData(application);
        document.getElementsByClassName("employee-section")[0].style.display = "none";

        let list = document.getElementsByClassName("add-temp")
        for (let i = 0; i < list.length; i++) {
            list.item(i).style.display = "none";
        }
        list = document.getElementsByClassName("create-interview")
        for (let i = 0; i < list.length; i++) {
            list.item(i).style.display = "block";
        }
    }

    function resetData(){
        setSideBlockStyle({display:'none'});
        document.getElementsByClassName("employer-section")[0].style.display = "block";
        document.getElementsByClassName("employee-section")[0].style.display = "block";
        let list = document.getElementsByClassName("add-temp")
        for (let i = 0; i < list.length; i++) {
            list.item(i).style.display = "block";
        }
        list = document.getElementsByClassName("create-interview")
        for (let i = 0; i < list.length; i++) {
            list.item(i).style.display = "none";
        }

        document.getElementById("interviewForm").style.display = "none";
    }

    function createInterview() {
        let data = new Object();
        data.agencyId = 1;
        data.employerApplicationId = employerApplicationId;
        data.employeeContractId = employeeApplicationId;
        data.managerId = 1;
        data.interviewStatusId = 1;
        data.expertId = document.getElementById("expert").value;
        data.dateTime = document.getElementById("date").value;
        data.managerComment = document.getElementById("manager-comment").value;
        console.log(data);
        axios
            .post("http://localhost:8080/interview", data)
            .then(data => {
                console.log(data);
                resetData();
            })
            .catch(err => alert(err))

    }
    return (

        <div style={{marginTop: '-52px'}}>
            <SideBlock display={sideBlockStyle.display} data={sideBlockData}/>
            <link
                href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,600,400italic,700'
                rel='stylesheet' type='text/css'/>
            <div className="wrap-login100 " id="interviewForm" style={styles.divSign}>

                <form className="login100-form validate-form">
                    <div className="cl-btn-7" onClick={closeInterviewForm}/>
                    <br/>
                    <span style={styles.span}>
					            ADD INTERVIEW
                    </span>

                    <div style={styles.divEnterData} data-validate="Enter your email">
                        <label htmlFor="experts">Choose:expert </label>
                        <select id="expert" name="cars">
                            {experts.experts.map((expert) => {
                                return <option value={expert.id}>{expert.name}</option>
                            })}
                        </select>

                    </div>

                    <div style={styles.divEnterData} data-validate="Enter password">
                        <input className="input100" type="text" style={styles.input} id="date"
                               name="date"
                               placeholder="date"/>
                        <span className="focus-input100"/>
                    </div>


                    <div style={styles.divEnterData} data-validate="Enter password">
                        <input className="input100" type="text" style={styles.input} id="manager-comment"
                               name="manager_comment"
                               placeholder="Manager's comment"/>
                        <span className="focus-input100"/>
                    </div>

                </form>
                <div className="container-login100-form-btn">
                    <button className="login100-form-btn" onClick={createInterview}>
                        Submit
                    </button>
                </div>

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
                                    <h1 className="to-animate">{name}</h1>
                                    <h2 className="to-animate" > Email: {email}</h2>
                                    <div className="container-login100-form-btn">
                                        <button className="login100-form-btn" onClick={resetData}>
                                            Reset
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </section>

            <section id="fh5co-work" className="section section-6 employer-section" data-section="work">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12 section-heading text-center">
                            <h2 className="to-animate">Заявки работодателей</h2>
                        </div>
                    </div>
                    <br/><br/>
                    <div className="row row-bottom-padded-sm">
                        {erApplications.erApplications.map((erApplication) => {
                            return  <div className="col-md-4 col-sm-6">
                                        <div className="fh5co-project-item image-popup to-animate">
                                            <p>Employer: {erApplication.employer.name}</p>
                                            <p>Employer: {erApplication.employer.name}</p>
                                            <p>Profession: {erApplication.profession.name}</p>
                                            <p>Salary: {erApplication.salary}</p>
                                            <p>Features:</p>
                                            {erApplication.features.map((feature) => {
                                                return <p>{feature.name}</p>
                                            })}

                                            <button className="login100-form-btn add-temp" onClick={addToTempAppEmployer.bind(this, erApplication)}>
                                                Выбрать
                                            </button>
                                            <button style={{display: 'none'}} className="login100-form-btn create-interview"  onClick={showInterviewForm.bind(this, erApplication.employer.id)}>
                                                Создать интервью
                                            </button>
                                        </div>
                                    </div>
                        })}
                    </div>
                    <br/><br/>
                </div>

            </section>

            <section id="fh5co-work" className="section section-6 employee-section" data-section="work">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12 section-heading text-center">
                            <h2 className="to-animate">Заявки соискателей</h2>
                        </div>
                    </div>
                    <br/><br/>
                    <div className="row row-bottom-padded-sm">
                        {erApplications.erApplications.map((erApplication) => {
                            return  <div className="col-md-4 col-sm-6">
                                <div className="fh5co-project-item image-popup to-animate">
                                    <p>Employer: {erApplication.employer.name}</p>
                                    <p>Employer: {erApplication.employer.name}</p>
                                    <p>Profession: {erApplication.profession.name}</p>
                                    <p>Salary: {erApplication.salary}</p>
                                    <p>Features:</p>
                                    {erApplication.features.map((feature) => {
                                        return <p>{feature.name}</p>
                                    })}

                                    <button className="login100-form-btn add-temp"  onClick={addToTempAppEmployee.bind(this, erApplication)}>
                                        Выбрать
                                    </button>
                                    <button  style={{display: 'none'}} className="login100-form-btn create-interview"  onClick={showInterviewForm.bind(this, erApplication.employer.id)}>
                                        Создать интервью
                                    </button>
                                </div>
                            </div>
                        })}
                    </div>
                </div>
            </section>
        </div>
    )
}

