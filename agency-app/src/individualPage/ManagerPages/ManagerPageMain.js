import React, {useEffect, useState} from 'react';
import "../../cssForIndividualPage/animate.css";
import "../../cssForIndividualPage/icomoon.css";
import "../../cssForIndividualPage/simple-line-icons.css";
import "../../cssForIndividualPage/magnific-popup.css";
import "../../cssForIndividualPage/style.css";
import 'react-datepicker/dist/react-datepicker.css'
import "./ManagerPage.css";

import axios from "axios";
import SideBlock from "./components/SideBlock";
import {AddExpertModal} from "./components/AddExpertModal";
import DatePicker from "react-datepicker"
import setHours from "date-fns/setHours";
import setMinutes from "date-fns/setMinutes";
import format from "date-fns/format";
import getMonth from "date-fns/getMonth";
import getYear from "date-fns/getYear";
import getMinutes from "date-fns/getMinutes"
import getHours from "date-fns/getHours"
import getDate from "date-fns/getDate"
import authHeader from "../../auth/header";
import {Filter} from "../AdminPage/components/Filter";




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
        height: '700px',
        width: '500px',
        position: 'absolute',
        margin: '0 0 0 -350px',
        left: '50%',
        display: 'none',
        paddingLeft: '30px',
        paddingRight: '30px',
        paddingTop: '5px',
        paddingBottom: '30px'
    },
    divFacebookGoogle: {
        paddingBottom: '50px',
        display: 'flex',
        justifyContent: 'center'
    },
    select100: {
        height: '62px',
        border:'none',
        outline:'none',
        marginBottom: '20px',
        position: 'relative',
        width: '100%',
        backgroundColor: '#fff',
        borderRadius: '20px'
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





export const ManagerPageMain =  () => {

    const [sideBlockStyle, setSideBlockStyle]= React.useState({display:'none'})
    const [sideBlockData, setSideBlockData]= React.useState({employer: '', profession: '', salary: '', features: []})
    const [experts,setExperts] = useState({experts:[]})
    const [interviewAppIds, setInterviewAppIds] = useState({})
    const [erApplications,setErApplications] = useState({erApplications:[]})
    const [isAddExpertModalCreate, setIsAddExpertModalCreate] = useState(true)
    const [startDate, setStartDate] = useState(setHours(setMinutes(new Date(), 0), 9))
    const [endDate, setEndDate] = useState(null/*setHours(setMinutes(new Date(), 30), 16)*/)
    const [busyHours, setBusyHours] = useState({busyHours:[], busyStartHours:[], busyEndHours:[]});
    const [interviews, setInterviews] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [searchResults, setSearchResults] = useState([]);
    const [myInterviewsShow, setMyInterviewShow] = useState(false);
    const [myInterviewsShowBtnStls, setMyInterviewsShowBtnStls] = useState({backgroundColor: "black", color: "#fff"})



    useEffect(() => {
        getExperts();
        getEmployerApplications();
        getAllInterviews();
        //getEmployeeApplications();
    }, [])

    const modalCreateClickHandler = () => setIsAddExpertModalCreate(false);
    const modalCloseClickHandler = () => setIsAddExpertModalCreate(true);
    const getExperts = () => {
        axios
            .get("http://localhost:8080/expert", {headers: authHeader()})
            .then(data => {
                setExperts({experts: data.data})
            })
            .catch(err => alert(err))

    }
    const getEmployerApplications = () => {
        axios
            .get("http://localhost:8080/employerApplication/all-for-manager", {headers: authHeader()})
            .then(data => {
                setErApplications({erApplications: data.data})
            })
            .catch(err => alert(err))
    };
    const showInterviewForm = id => {
        document.getElementById("interviewForm").style.display = "block";
        if (interviewAppIds.employeeApplicationId === undefined) {
            setInterviewAppIds({...interviewAppIds, employeeApplicationId: id});
        }
        if (interviewAppIds.employerApplicationId === undefined) {
            setInterviewAppIds({...interviewAppIds, employerApplicationId: id});
        }
    }
    const closeInterviewForm = () => {
        document.getElementById("interviewForm").style.display = "none";
    }
    const addToTempAppEmployer = (application, evnt) => {
        setInterviewAppIds({...interviewAppIds, employerApplicationId: application.id});
        setSideBlockStyle({display: 'block'});
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
    const addToTempAppEmployee = (application, evnt) => {
        setInterviewAppIds({...interviewAppIds, employeeApplicationId: application.id});
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
    const resetData = () => {
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
    const createInterview = () => {
        let data = new Object();
        data.agencyId = 1;
        data.employerApplicationId = interviewAppIds.employerApplicationId;
        data.employeeContractId = interviewAppIds.employeeApplicationId;
        data.managerId = 1;
        data.interviewStatusId = 1;
        data.expertId = document.getElementById("expert").value;
        data.startDateTime = format(startDate, 'yyyy-MM-dd HH:mm');
        data.endDateTime = format(endDate, 'yyyy-MM-dd HH:mm');
        /*document.getElementById("date").value*;*/
        data.managerComment = document.getElementById("manager-comment").value;
        axios
            .post("http://localhost:8080/interview", data, {headers: authHeader()})
            .then(data => {
                resetData();
            })
            .catch(err => alert(err))

    }
    const changeStartDateTime = date => {
        setStartDate(date);
        setEndDate(null);
        axios
            .get("http://localhost:8080/interview/get-time/" + JSON.parse(localStorage.getItem("response")).agency.id +"/"+ document.getElementById("expert").value + "/" +
                getYear(date) + "/" + (getMonth(date)+1) + "/" + getDate(date), {headers: authHeader()}) //agency_id/expert_id/year/month/day
            .then(data => {
                console.log(data.data);
                /*setBusyHours([...busyHours, ...data.data.busyHours])*/
                setBusyHours(data.data)
                /*setBusyHours({busyHours: data.data.busyHours, busyStartHours: data.data.busyStartHours, busyEndHours: data.data.busyEndHours})*/
            })
            .catch(err => alert(err))
    }
    const calcMaxTime = () => {
        let filtered = busyHours.busyHours.filter(hour => hour > getHours(startDate)).filter(hour => busyHours.busyStartHours.includes(hour));
        let min = Math.min(...filtered)
        return min === Infinity ? 18 : min;
    }
    const getMyInterviews = () => {
        axios
            .get("http://localhost:8080/interview/" + 1/*JSON.parse(localStorage.getItem("response")).agency.id*/ +"/"+ 2/*document.getElementById("MANAGER_ID").value*/ , {headers: authHeader()}) //agency_id/expert_id/year/month/day
            .then(data => {
                console.log(data.data);
                setInterviews(data.data);
                setSearchResults(data.data);
            })
            .catch(err => alert(err))
    };
    const getAllInterviews = () => {
        axios
            .get("http://localhost:8080/interview/" + 1/*JSON.parse(localStorage.getItem("response")).agency.id*/ /*document.getElementById("MANAGER_ID").value*/ , {headers: authHeader()}) //agency_id/expert_id/year/month/day
            .then(data => {
                console.log(data.data);
                setInterviews(data.data);
                setSearchResults(data.data);
            })
            .catch(err => alert(err))
    }

    const handleSearchChange = e => {
        setSearchTerm(e);
        e !== '' ? setSearchResults(interviews.filter(interview => interview.expertName.toLowerCase().includes(searchTerm.toLowerCase()))/*users.filter(user => user.email.toLowerCase().includes(searchTerm.toLowerCase()))*/) : setSearchResults(interviews/*users*/);
    };

    const changeMyInterviewShow = () => {
        if (myInterviewsShow === true) {
            setMyInterviewsShowBtnStls({backgroundColor: "black", color: "#fff"})
            getAllInterviews()
            setMyInterviewShow(false);
        } else {
            setMyInterviewsShowBtnStls({backgroundColor: "#17a2b8", color: "#fff"})
            getMyInterviews();
            setMyInterviewShow(true);
        }
    }
    const showInterview = () => {

    }

    return (

        <div style={{marginTop: '-52px'}}>
            <SideBlock display={sideBlockStyle.display} data={sideBlockData}/>
            {!isAddExpertModalCreate &&
            <AddExpertModal onModalCloseClick={modalCloseClickHandler} getExperts={getExperts}/>
            }

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
                    <div style={styles.select100}>
                            <select  id="expert" style={styles.select100} name="expert" >
                                {experts.experts.map(expert =>
                                    <option style={styles.option100} key={expert.id} value={expert.id}
                                    >{expert.name}</option>)}
                            </select>
                            <span className="focus-input100"/>
                            <div className="add-btn-1" onClick={modalCreateClickHandler}/>
                    </div>
                    <div style={styles.divEnterData} >
                        <DatePicker
                            selected={startDate}
                            onChange={date => changeStartDateTime(date)/*setStartDate(date)*/}
                            showTimeSelect
                            minDate={new Date()}
                            minTime={setHours(setMinutes(new Date(), 0), 9)}
                            maxTime={setHours(setMinutes(new Date(), 0), 18)}
                            timeIntervals={60}
                            placeholderText='Enter start time'
                            excludeTimes={
                                busyHours.busyHours.filter(hour =>
                                    !busyHours.busyEndHours.includes(hour)
                                ).map(hour => {
                                    return setHours(setMinutes(new Date(), 0), hour)
                                })
                            }

                            dateFormat="yyyy-MM-dd HH:mm"
                        />
                        <span className="focus-input100"/>
                    </div>
                    <div style={styles.divEnterData} >
                        <DatePicker
                            selected={endDate}
                            onChange={date => setEndDate(date)}
                            showTimeSelect
                            minDate={startDate}
                            maxDate={startDate}
                            timeIntervals={60}
                            minTime={setHours(setMinutes(new Date(), 0), getHours(startDate))}
                            maxTime={setHours(setMinutes(new Date(), 0), calcMaxTime())}
                            dateFormat="yyyy-MM-dd HH:mm"
                            placeholderText='Enter end time'
                            excludeTimes={
                                busyHours.busyHours.filter(hour =>
                                    !busyHours.busyStartHours.includes(hour)).map(hour => {return setHours(setMinutes(new Date(), 0), hour)})
                            }

                        />
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
                                    <h1 className="to-animate">{/*{name}*/}</h1>
                                    <h2 className="to-animate" > Email: {/*{email}*/}</h2>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <div className="container-login100-form-btn" style={{backgroundColor: "#f9f6f0"}}>
                <button className="login100-form-btn" onClick={resetData}>
                    Reset
                </button>
            </div>
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

                                            <button style={{marginLeft: 'auto', marginRight: 'auto', marginBottom: '5px' }} className="login100-form-btn add-temp" onClick={addToTempAppEmployer.bind(this, erApplication)}>
                                                Выбрать
                                            </button>
                                            <button style={{display: 'none', marginLeft: 'auto', marginRight: 'auto', marginBottom: '5px' }} className="login100-form-btn create-interview"  onClick={showInterviewForm.bind(this, erApplication.employer.id)}>
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

                                    <button style={{marginLeft: 'auto', marginRight: 'auto', marginBottom: '5px' }} className="login100-form-btn add-temp"  onClick={addToTempAppEmployee.bind(this, erApplication)}>
                                        Выбрать
                                    </button>
                                    <button  style={{display: 'none', marginLeft: 'auto', marginRight: 'auto', marginBottom: '5px' }} className="login100-form-btn create-interview"  onClick={showInterviewForm.bind(this, erApplication.employer.id)}>
                                        Создать интервью
                                    </button>
                                </div>
                            </div>
                        })}
                    </div>
                </div>
            </section>

            <div style={{borderLeft: "30px solid #F6F6F5", borderRight: "30px solid #F6F6F5", display: "flex", justifyContent: "space-between", backgroundColor: "#f9f6f0"}}>
               {/* <select  id="a"  name="a" >
                    {experts.experts.map(expert =>
                        <option key={expert.id} value={expert.id}
                        >{expert.name}</option>)}
                </select>*/}

                <Filter value={searchTerm} handleChange={e => handleSearchChange(e.target.value)}/>

                <div >
                    <button className="button" style={{
                    backgroundColor: myInterviewsShowBtnStls.backgroundColor,
                    color: myInterviewsShowBtnStls.color,
                    }} onClick={changeMyInterviewShow}>
                        Показать Мои
                    </button>
                </div>
            </div>

            <section id="fh5co-work" className="section section-6" data-section="work">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12 section-heading text-center">
                            <h2 className="to-animate">Интервью</h2>
                        </div>
                    </div>
                    <br/><br/>
                    <div className="row row-bottom-padded-sm">
                        {searchResults.map((interview) => {
                            return  <div className="col-md-4 col-sm-6">
                                <div className="fh5co-project-item image-popup to-animate">
                                    <p>Expert name: {interview.expertName}</p>
                                    <p>Employee name: {interview.employeeSurname + " " + interview.employeeName}</p>
                                    <p>Interview status: {interview.interviewStatusName}</p>
                                    <p>Start: {interview.startDateTime.replace('T', ' ')}</p>
                                    <p>End: {interview.endDateTime.replace('T', ' ')}</p>
                                    <p>Manag. comment: {interview.managerComment}</p>
                                    <button style={{marginLeft: 'auto', marginRight: 'auto', marginBottom: '5px' }} className="login100-form-btn"  onClick={showInterview}> {/*будет окно с поялми коммента, update статуса и вопросами*/}
                                        Выбрать
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


