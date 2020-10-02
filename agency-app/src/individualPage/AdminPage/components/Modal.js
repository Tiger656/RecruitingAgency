import React, {useState} from "react";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'animate.css/animate.min.css'
import "../../../cssForIndividualPage/animate.css";
import "../../../cssForIndividualPage/icomoon.css";
import "../../../cssForIndividualPage/simple-line-icons.css";
import "../../../cssForIndividualPage/magnific-popup.css";
import "../../../cssForIndividualPage/style.css";
import {Notification} from "./Notification";
import {toast} from "react-toastify";

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
        zIndex: 100
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
        alignItems: 'center'

    },
    divFacebookGoogle: {
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


export const Modal = ({submitHandler, onModalCloseClick, id = null, email = '', agencyId = '', roleIds = [], roles = [], allRoles, allAgencies,buttonName}) => {
    let currentAgencyId = JSON.parse(localStorage.getItem('response')).agency.id;
    const initialFormState = {id, email, agencyId, roleIds, roles};
    const [user, setUser] = useState(initialFormState)
    const [rolesIds] = useState([]);

    const handleInputChange = event => {
        const {name, value} = event.currentTarget
        setUser({...user, [name]: value, roleIds: rolesIds})

    }


    const checkChangeHandler = roleId => {
        const result = toggleUserRole(user.roles, roleId);

        setUser({...user, roles: result,agencyId:currentAgencyId});
    }

    const hasRole = (roles, roleId) => roles.find(role => role.id === roleId);


    const toggleUserRole = (roles, roleId) => {

        if (hasRole(roles, roleId))
            return roles.filter(role => role.id !== roleId)
        return [...roles, allRoles.find(role => role.id === roleId)];
    }
    const handleSubmit = event => {
        event.preventDefault()
        /*fix*/
        if (!user.email || !user.agencyId)
            warnEnterAllFieldsNotify("Fields cannot be empty!")
            else
        submitHandler(user)
        setUser(initialFormState)
    }


    const warnEnterAllFieldsNotify =(message)=>{
        toast.warn(message, {position: toast.POSITION.TOP_RIGHT,color:"black"});
    }




    return (
        <div style={styles.popupFade}>
<Notification/>
            <link
                href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,600,400italic,700'
                rel='stylesheet' type='text/css'/>
            <div className="animate__animated animate__backInLeft" id="interviewForm" style={styles.divSign}>

                <form className="login100-form validate-form " onSubmit={handleSubmit}>
                    <button className="cl-btn-7" onClick={onModalCloseClick} />
                    <br/>
                    <span style={styles.span}>
					             ADD USER
                    </span>


                    <div style={styles.divEnterData} >
                        <input className="input100" type="email" style={styles.input}
                               name="email"
                               placeholder="Email"
                               value={user.email}
                               onChange={handleInputChange}/>
                        <span className="focus-input100"/>
                    </div>
                    {/*<div style={styles.select100}>*/}
                    {/*    <select  style={styles.select100} name="agencyId" value={user.agencyId}*/}
                    {/*            onChange={handleInputChange}>*/}
                    {/*        <option style={styles.option100}>Choose</option>*/}
                    {/*        {allAgencies.map(agency =>*/}

                    {/*            <option style={styles.option100} key={agency.id} value={agency.id}*/}
                    {/*            >{agency.name}</option>)}*/}
                    {/*    </select>*/}
                    {/*    <span className="focus-input100"/>*/}
                    {/*</div>*/}

                    <div style={styles.divEnterData}>
                        <div style={{textAlign: 'left'}}>
                            {allRoles.map((role) =>

                                <div className="custom-control custom-checkbox" key={role.id}>
                                    <input name="box" type="checkbox" className="custom-control-input" id={role.id}
                                           checked={!!hasRole(user.roles, role.id)}
                                           value={role.id}
                                           onChange={() => checkChangeHandler(role.id)}
                                    />
                                    <label className="custom-control-label" htmlFor={role.id}
                                           style={{marginLeft: '3.5rem',color:'black'}}>{role.name}</label>
                                </div>
                            )}
                        </div>
                    </div>


                    <br/>

                    <div className="container-login100-form-btn">
                        <button className="login100-form-btn"  style={{marginBottom: '5px'}}>
                            {buttonName}
                        </button>
                    </div>

                    <div className="container-login100-form-btn">
                        <button className="login101-form-btn" style={{marginBottom: '15px'}}
                                onClick={onModalCloseClick}>
                            Cancel
                        </button>
                    </div>


                    <div style={styles.divFacebookGoogle}>
                        <a href="#" className="login100-social-item">
                            <img
                                src="https://www.freeiconspng.com/uploads/facebook-png-icon-follow-us-facebook-1.png"
                                alt="FACEBOOK"/>
                        </a>

                        <a href="#" className="login100-social-item">
                            <img
                                src="https://prooriginal.ru/image/catalog/demo/brendy/photo.jpg"
                                alt="GOOGLE"/>
                        </a>
                    </div>

                </form>
            </div>
        </div>
    )

}
