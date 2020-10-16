import React from 'react';
import "../../cssForIndividualPage/animate.css";
import "../../cssForIndividualPage/icomoon.css";
import "../../cssForIndividualPage/simple-line-icons.css";
import "../../cssForIndividualPage/magnific-popup.css";
import "../../cssForIndividualPage/style.css";
import "../../startPage/SignIn.css";
import axios from "axios";
import authHeader from "../../auth/header";

const styles = {
    select100: {
        height: '62px',
        border: 'none',
        outline: 'none',
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
        color: "black",
        textAlign: 'left',
        paddingBottom: '20px',
        paddingLeft: '20px',
        paddingRight: '20px'
    }
};

function EmployeeContract() {
    const createAppContract = () => {
        let contract = {
            agencyId: 1,
            name: document.getElementById('name').value,
            surname: document.getElementById('surname').value,
            minSalary: document.getElementById('salary').value,
            email: document.getElementById('email').value,
            telephoneNumber: document.getElementById('telephoneNumber').value,

            experienceId: 1,
            professionId: 1,
            birthDate: new Date(1998, 11, 25),
            cityId: 1,
            addressId: 1,
            passport: 'HB1234567',

            accountUsd: 0,
            statusId: 2,
            compensation: 0,
            creationDate: Date.now(),
            endDate: new Date(2021,10,16),
            isDeleted: false
        }
        console.log(contract);
        axios
            .post('http://localhost:8080/employeeContract/create', contract)
            .then()
            .catch((err) => alert(err))

    }
    return (

        <>
            {/*<div className="wrap-login100" id="request" style={{*/}
            {/*    top: '80px', width: '360px', position: 'absolute', zIndex: '999',*/}
            {/*    margin: '0 0 0 -180px', left: '50%', display: 'none',*/}
            {/*    paddingLeft: '30px', paddingTop: '5px', paddingBottom: '30px', paddingRight: '30px'*/}
            {/*}}>*/}

            {/*    <form className="login100-form validate-form">*/}
            {/*        <div className="cl-btn-7" onClick={requestNone}/>*/}
            {/*        <br/>*/}
            <span className="login100-form-title p-b-37" style={styles.span}>Contract</span>

            <div style={styles.divEnterData}>
                <input className="input100" type="text" id="name" style={styles.input}
                       placeholder="Name"/>
                <span className="focus-input100"/>
            </div>

            <div style={styles.divEnterData}>
                <input className="input100" type="text" id="surname" style={styles.input}
                       placeholder="Surname"/>
                <span className="focus-input100"/>
            </div>

            <div style={styles.divEnterData}>
                <input className="input100" type="text" id="salary" style={styles.input}
                       placeholder="Min salary"/>
                <span className="focus-input100"/>
            </div>

            <div style={styles.divEnterData}>
                <input className="input100" type="text" id="email" style={styles.input}
                       placeholder="Email"/>
                <span className="focus-input100"/>
            </div>

            <div style={styles.divEnterData}>
                <input className="input100" type="text" id="telephoneNumber" style={styles.input}
                       placeholder="Telephone number"/>
                <span className="focus-input100"/>
            </div>

            <div className="container-login100-form-btn">
                <button className="login100-form-btn" onClick={createAppContract}>
                    Apply for a contract
                </button>
            </div>
            {/*    </form>*/}

            {/*</div>*/}
        </>

    )

}

export default EmployeeContract;