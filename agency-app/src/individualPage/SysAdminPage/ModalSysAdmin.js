import React, {useEffect, useState} from "react";
import 'bootstrap/dist/css/bootstrap.min.css'
import 'animate.css/animate.min.css'
import "../../cssForIndividualPage/animate.css";
import "../../cssForIndividualPage/icomoon.css";
import "../../cssForIndividualPage/simple-line-icons.css";
import "../../cssForIndividualPage/magnific-popup.css";
import "../../cssForIndividualPage/style.css";


import axios from "axios";
import authHeader from "../../auth/header";

const styles = {
//     box:{
//         display: "flex",
// flexWrap: "wrap",
// height: "400px",
// alignContent: "space-between"
//     },
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
        marginTop: '2%',
        backgroundColor: 'white',
        width: '30%',
        height: '90%',
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
        border: 'none',
        outline: 'none',
        marginBottom: '20px',
        position: 'relative',
        width: '100%',
        backgroundColor: '#fff',
        borderRadius: '20px'
    },
    option100: {
        border: '20px solid #ffffff',
        outline: 'none',
    }
}

export const ModalSysAdmin = ({
                                  closeModal, submitHandler, getCitiesByCountryId, allCountries,
                                  allCities, buttonName, id, warnEnterAllFieldsNotify, errorNotify, deleteAgency
                              }) => {

    const initialFormState = {
        id: null,
        name: '',
        cityId: '',
        countryId: '',
        addressId: '',
        street: "",
        building: "",
        apartment: "",
        regularPayment:""

    };


    const [agency, setAgency] = useState(initialFormState);

    useEffect(() => {
        if (id) {
            getAgencyById(id)
        }
    }, [id]);

    const getAgencyById = (agencyId) => {
        axios
            .get('http://localhost:8080/api/agency/' + agencyId, {headers: authHeader()})
            .then((res) => setAgency(res.data))
            .catch((err) => errorNotify(err.response.data.error))
    };


    const handleSubmit = event => {
        event.preventDefault();

        if (!agency.name || !agency.countryId || !agency.cityId
            || !agency.street || !agency.building || !agency.apartment || !agency.regularPayment)
            warnEnterAllFieldsNotify("Fields cannot be empty!");
        else {
            submitHandler(agency);
            closeModal();
        }


    }
    const handleInputCountryChange = event => {
        getCitiesByCountryId(event.currentTarget.value);
        const {name, value} = event.currentTarget;
        setAgency({...agency, [name]: value})

    }
    const handleInputChange = event => {
        const {name, value} = event.currentTarget;
        setAgency({...agency, [name]: value})

    }

    const deleteAg = (event) => {
        event.preventDefault();
        deleteAgency(id);
        closeModal();

    }

    console.log(agency)
    return (
        <div style={styles.popupFade}>
            <link
                href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,600,400italic,700'
                rel='stylesheet' type='text/css'/>
            <div className="animate__animated animate__backInLeft" id="interviewForm" style={styles.divSign}>

                <form className="login100-form validate-form "
                      onSubmit={handleSubmit}
                >
                    <button type="button" className="cl-btn-7"
                            onClick={closeModal}
                    />
                    <br/>
                    <span style={styles.span}>
                        {buttonName} Agency
                    </span>


                    <div style={styles.divEnterData}>
                        <input className="input100" type="text" style={styles.input}
                               name="name"
                               placeholder="Agency Name"
                               value={agency.name}
                               onChange={handleInputChange}
                        />
                        <span className="focus-input100"/>
                    </div>


                    <div style={styles.select100}>
                        <select style={styles.select100} name="countryId"
                                value={agency.countryId}
                                onChange={handleInputCountryChange}
                        >
                            <option style={styles.option100}>Country</option>
                            {allCountries.map(country =>
                                <option style={styles.option100} key={country.id} value={country.id}
                                >{country.name}</option>)}
                        </select>
                        <span className="focus-input100"/>
                    </div>

                    <div style={styles.select100}>
                        <select style={styles.select100} name="cityId"
                                value={agency.cityId}
                                onChange={handleInputChange}
                        >
                            <option style={styles.option100}>City</option>
                            {allCities.map(city =>
                                <option style={styles.option100} key={city.id} value={city.id}
                                >{city.name}</option>)}
                        </select>
                        <span className="focus-input100"/>
                    </div>

                    { !id &&
                    <div>
                    <span style={styles.span}>
					             OWNER AND ADMIN
                    </span>

                        <div style={styles.divEnterData}>
                            <input
                                // className="input100"
                                type="text"
                                   // style={styles.input}
                                   name="ownerEmail"
                                   placeholder="Enter owner email"
                                   onChange={handleInputChange}
                            />
                            <span className="focus-input100"/>
                        </div>

                        <div style={styles.divEnterData}>
                            <input
                                // className="input100"
                                type="text"
                                   // style={styles.input}
                                   name="adminEmail"
                                   placeholder="Enter admin email"
                                   onChange={handleInputChange}
                            />
                            <span className="focus-input100"/>
                        </div>

                    </div>
                    }
                    <div style={styles.divEnterData}>
                        <input
                            // className="input100"
                            type="text"
                            // style={styles.input}
                            name="regularPayment"
                            placeholder="Regular payment"
                            value={agency.regularPayment}
                            onChange={handleInputChange}
                        />
                        <span className="focus-input100"/>
                    </div>



                    <span style={styles.span}>
					             ADDRESS
                    </span>



                    <div style={styles.divEnterData}>
                        <input className="input100" type="text" style={styles.input}
                               name="street"
                               placeholder="Street"
                               value={agency.street}
                               onChange={handleInputChange}
                        />
                        <span className="focus-input100"/>
                    </div>

                    <div style={styles.divEnterData}>
                        <input className="input100" type="text" style={styles.input}
                               name="building"
                               placeholder="Building"
                               value={agency.building}
                               onChange={handleInputChange}
                        />
                        <span className="focus-input100"/>
                    </div>
                    <div style={styles.divEnterData}>
                        <input className="input100" type="text" style={styles.input}
                               name="apartment"
                               placeholder="Apartment"
                               value={agency.apartment}
                               onChange={handleInputChange}
                        />
                        <span className="focus-input100"/>
                    </div>
                    <br/>
                    {
                        id &&
                        <div className="container-login100-form-btn">
                            <button type='button' className="login101-form-btn" style={{marginBottom: '15px'}}
                                    onClick={(event) => deleteAg(event)}
                            >
                                Delete
                            </button>
                        </div>
                    }


                    <div className="container-login100-form-btn">
                        <button className="login100-form-btn" style={{marginBottom: '5px'}}

                        >
                            {buttonName}
                        </button>
                    </div>

                    <div className="container-login100-form-btn">
                        <button type='button' className="login101-form-btn" style={{marginBottom: '15px'}}
                                onClick={closeModal}
                        >
                            Cancel
                        </button>
                    </div>


                </form>
            </div>
        </div>
    )

}