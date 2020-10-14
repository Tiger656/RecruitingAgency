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

export const UpdateInterviewModal = ({onModalCloseClick, updateStatusInterviewData}) => {

    const [interviewUpdateData, setInterviewUpdateData] = useState({interviewId: updateStatusInterviewData.interviewId, interviewStatusId: null})
    const [interviewStatuses, setInterviewStatuses] = useState([])

    useEffect(() => {
        getInterviewStatuses();
        console.log(updateStatusInterviewData);
    }, [])

    const getInterviewStatuses = () => {
        axios
            .get("http://localhost:8080/interview-status", {headers: authHeader()})
            .then(data => {
                console.log(data);
                setInterviewStatuses(data.data);

            })
            .catch(err => alert(err))
    }
    const handleInputChange = event => {
        console.log("handle");
        setInterviewUpdateData({interviewId: updateStatusInterviewData.interviewId, interviewStatusId: event.currentTarget.value})
    }

    const updateInterview = () => {
        axios
            .put("http://localhost:8080/interview/change-interview-status", {id: interviewUpdateData.interviewId, interviewStatusId: interviewUpdateData.interviewStatusId}, {headers: authHeader()})
            .then(data => {
                console.log(interviewUpdateData);
                onModalCloseClick();
            })
            .catch(err => alert(err))
    }
    return (
        <div style={styles.popupFade} >

            <link
                href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,600,400italic,700'
                rel='stylesheet' type='text/css'/>
            <div className="animate__animated animate__backInLeft wrap-login100"  id="interviewForm" style={styles.divSign}>

                <form className="login100-form validate-form "  >
                    <button className="cl-btn-7" onClick={onModalCloseClick} />
                    <br/>
                    <span style={styles.span}>
					             Update interview
                    </span>




                    <div style={styles.select100}>

                        <select  style={styles.select100} name="interviewStatusId" onChange={handleInputChange}>
                            <option value="" disabled selected>CHOOSE INTERVIEW STATUS</option>
                            {interviewStatuses.map(interviewStatus =>
                                <option style={styles.option100} key={interviewStatus.id} value={interviewStatus.id}
                                >{interviewStatus.name}</option>)}
                        </select>
                        <span className="focus-input100"/>
                    </div>

                    <br/>
                </form>
                <div className="container-login100-form-btn">
                    <button className="login100-form-btn"  onClick={updateInterview} >
                        Save
                    </button>
                </div>

            </div>
        </div>
    )

}