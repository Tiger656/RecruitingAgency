import React from 'react';
import "../../cssForIndividualPage/animate.css";
import "../../cssForIndividualPage/icomoon.css";
import "../../cssForIndividualPage/simple-line-icons.css";
import "../../cssForIndividualPage/magnific-popup.css";
import "../../cssForIndividualPage/style.css";
import "../../startPage/SignIn.css";

const styles = {
    button: {
        color: "black",
        textAlign: 'left',
        paddingBottom: '20px',
        paddingLeft: '20px',
        paddingRight: '20px'
    }
};

function EmployeeInterview(props) {
    const {interview} = props;
    return (
        <div className="col-md-4 col-sm-6">
            <a className="fh5co-project-item image-popup to-animate">
                <div style={styles.button}>
                    <br/>
                    <h6><b>Интервью</b></h6>
                    <h10>Имя эксперта: {interview.expertName}</h10>
                    <br/>
                    <h10>Время и дата начала: {interview.startDateTime} </h10>
                    <br/>
                    <h10>Время и дата окончания: {interview.endDateTime} </h10>
                    <br/>
                    <h10>Комментарий менеджера: {interview.managerComment} </h10>
                    <br/>
                    <h10>Статус: {interview.interviewStatusName} </h10>
                </div>
            </a>
        </div>)

}

export default EmployeeInterview;