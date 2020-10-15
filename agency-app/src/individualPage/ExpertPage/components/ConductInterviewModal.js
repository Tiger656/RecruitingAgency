import React, {useEffect, useState} from "react";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'animate.css/animate.min.css'
import "../../../cssForIndividualPage/animate.css";
import "../../../cssForIndividualPage/icomoon.css";
import "../../../cssForIndividualPage/simple-line-icons.css";
import "../../../cssForIndividualPage/magnific-popup.css";
import "../../../cssForIndividualPage/style.css";
import axios from "axios";
import authHeader from "../../../auth/header";
import setHours from "date-fns/setHours";
import setMinutes from "date-fns/setMinutes";
import {SimpleQuestionModal} from "./SimpleQuestionModal";
import {CustomQuestionModal} from "./CustomQuestionModal";
import {toast} from "react-toastify";
import {Notification} from "../../AdminPage/components/Notification";

const styles = {
    popupFade: {
        position: 'fixed',
        width: '100%',
        height: '100%',
        top: 0,
        left: 0,
        backgroundColor: 'rgba(0,0,0,0.5)',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        zIndex: 1000
    },
    img: {
        width: '100px'
    },
    a: {
        display: 'inline-block',
        color: 'black'
    },
    nav: {
        backgroundColor: 'aqua',
        position: 'fixed',
        top: '0',
        right: '0',
        left: '0',
    },
    span: {
        paddingBottom: '30px',
        display: 'flex',
        fontSize: '30px',
        color: 'black',
        lineHeight: '1.2',
        textAlign: 'center'
    },
    divSign: {
        paddingTop: '0px',
        marginTop: '5%',
        backgroundColor: 'white',
        width: '30%',
        height: '85%',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexDirection: 'column'

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
    option100:{
        border:'20px solid #ffffff',
        outline:'none',
    }
}

export const ConductInterviewModal = ({onModalCloseClick, interviewId}) => {

    const [questions, setQuestions] = useState([]);

    useEffect(() => {
       getQuestionsForConducting()
    }, [])

   const getQuestionsForConducting = () => {
        axios
            .get("http://localhost:8080/interview/get-interview-for-conducting/" + interviewId, {headers: authHeader()})
            .then(data => {
                console.log(data);
                setQuestions(data.data);
            })
            .catch(err => alert(err))
    }

    const handleInputChange = event => {
        const {name, value} = event.currentTarget ;
        let q = questions
        q.map(question => {
            //console.log(question.id);
            //console.log(name);
            if (question.id == name) {
                //console.log(value);
                question.answer = value;
            }
        })
        setQuestions(q);
    };


    const updateInterview = () => {
        axios
            .post("http://localhost:8080/question/get-interview-by-id/", questions, {headers: authHeader()})
            .then(data => {

            })
            .catch(err => alert(err))

    }
    return (
        <div>
            <Notification/>
        <div style={styles.popupFade} >
            <link
                href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,600,400italic,700'
                rel='stylesheet' type='text/css'/>
            <div className="animate__animated animate__backInLeft wrap-login100"  id="interviewForm" style={styles.divSign}>

                <form className="login100-form validate-form ">
                    <br/>
                    <span style={styles.span}>
					             Conduct interview
                    </span>
                    {questions.map(question =>
                        <div>
                            <h5>{question.name}</h5>
                            {question.questionVariants.map(variant => <div><h7>{variant.label + ": " + variant.name}</h7></div>)}
                            <div style={styles.divEnterData} >
                                <input className="input100" type="text"  style={styles.input} onChange={handleInputChange}
                                       name={question.id}
                                       placeholder="Answer"
                                />
                                <span className="focus-input100"/>
                            </div>
                        </div>
                    )}
                    <br/>
                </form>

                <div className="container-login100-form-btn">
                    <button className="login100-form-btn"  onClick={updateInterview}>
                        Save
                    </button>
                </div>
                <div className="container-login100-form-btn">
                    <button className="login100-form-btn" onClick={onModalCloseClick}>
                        Close
                    </button>
                </div>
            </div>
        </div>
        </div>
    )

}