import React, {useEffect, useState} from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'animate.css/animate.min.css'
import axios from 'axios'
import {toast} from "react-toastify";
import authHeader from "../../auth/header";



export const ExpertPage = () => {

    const [unconfirmedInterviews, setUnconfirmedInterviews] = useState([])
    const [confirmedInterviews, setConfirmedInterviews] = useState([])

    useEffect(() => {
        getUnconfirmedInterviews();
        getConfirmedInterviews();
    }, []);
    let agencyName = JSON.parse(localStorage.getItem('response')).agency.name;
    let personEmail = JSON.parse(localStorage.getItem('response')).email;

    const getUnconfirmedInterviews = () => { //1,2
        setUnconfirmedInterviews([]);
        axios
            .get("http://localhost:8080/interview/get-interview-for-expert/" +  JSON.parse(localStorage.getItem("response")).agency.id + "/" + JSON.parse(localStorage.getItem("response")).userId + "/1", {headers: authHeader()})
            .then(data => {
                setUnconfirmedInterviews(data.data);
            })
            .catch(err => alert(err))
        axios
            .get("http://localhost:8080/interview/get-interview-for-expert/" +  JSON.parse(localStorage.getItem("response")).agency.id + "/" + JSON.parse(localStorage.getItem("response")).userId +"/2", {headers: authHeader()})
            .then(data => {
                setUnconfirmedInterviews([...unconfirmedInterviews, ...data.data]);
            })
            .catch(err => alert(err))
    }
    const getConfirmedInterviews = () => { //Only 3
        axios
            .get("http://localhost:8080/interview/get-interview-for-expert/" +  JSON.parse(localStorage.getItem("response")).agency.id + "/" + JSON.parse(localStorage.getItem("response")).userId + "/3", {headers: authHeader()})
            .then(data => {
                setConfirmedInterviews(data.data);
                console.log(data.data);

            })
            .catch(err => alert(err))
    }
    const confirmInterview = (interviewId, interviewStatusId) => {
        let newStatusId;
        if (interviewStatusId === 1) newStatusId = 3;
        if (interviewStatusId === 2) newStatusId = 7;
        axios
            .get("http://localhost:8080/interview/change-interview-status/" + interviewId + "/" + newStatusId,  {headers: authHeader()})
            .then(data => {
                getUnconfirmedInterviews();
                getConfirmedInterviews();

            })
            .catch(err => alert(err))
    }

    return (
        <div>
            {/*<Notification />*/}
            <div>
                <link
                    href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,600,400italic,700'
                    rel='stylesheet' type='text/css'/>
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
                                        <h1 className="to-animate">{agencyName}</h1>
                                        <h2 className="to-animate"> Email: {personEmail}</h2>

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
                                <h2 className="to-animate">Unconfirmed Interview</h2>
                            </div>
                        </div>
                        <br/><br/>
                        <div className="row row-bottom-padded-sm">
                            {unconfirmedInterviews.map((interview) => {
                                return  <div className="col-md-4 col-sm-6">
                                    <div className="fh5co-project-item image-popup to-animate">
                                        <p>Expert name: {interview.expertName}</p>
                                        <p>Employee name: {interview.employeeSurname + " " + interview.employeeName}</p>
                                        <p>Interview status: {interview.interviewStatusName}</p>
                                        <p>Start: {interview.startDateTime.replace('T', ' ')}</p>
                                        <p>End: {interview.endDateTime.replace('T', ' ')}</p>
                                        <p>Manag. comment: {interview.managerComment}</p>
                                        <button style={{marginLeft: 'auto', marginRight: 'auto', marginBottom: '5px' }} className="login100-form-btn" onClick={() => confirmInterview(interview.id, interview.interviewStatusId)}>
                                            Выбрать
                                        </button>

                                    </div>
                                </div>
                            })}
                        </div>
                    </div>
                    <br/><br/>
                </section>
                <section id="fh5co-work" className="section section-6" data-section="work">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-12 section-heading text-center">
                                <h2 className="to-animate">Confirmed Interview</h2>
                            </div>
                        </div>
                        <br/><br/>
                        <div className="row row-bottom-padded-sm">
                            { confirmedInterviews.length > 0 ? confirmedInterviews.map((interview) => {
                                return  <div className="col-md-4 col-sm-6">
                                    <div className="fh5co-project-item image-popup to-animate">
                                        <p>Expert name: {interview.expertName}</p>
                                        <p>Employee name: {interview.employeeSurname + " " + interview.employeeName}</p>
                                        <p>Interview status: {interview.interviewStatusName}</p>
                                        <p>Start: {interview.startDateTime.replace('T', ' ')}</p>
                                        <p>End: {interview.endDateTime.replace('T', ' ')}</p>
                                        <p>Manag. comment: {interview.managerComment}</p>
                                       {/* <button style={{marginLeft: 'auto', marginRight: 'auto', marginBottom: '5px' }} className="login100-form-btn" > будет окно с поялми коммента, update статуса и вопросами
                                            Выбрать
                                        </button>*/}

                                    </div>
                                </div>
                            }): false}
                        </div>
                    </div>
                    <br/><br/>
                </section>
            </div>
        </div>
    )
};
