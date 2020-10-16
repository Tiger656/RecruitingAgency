import React, {useEffect, useState} from 'react';
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
    }
};

function EmployerApplication(props) {
    const {employerId} = props;
    const {agencyId} = props;
    const {endDate} = props;

    const [employmentTypes, setEmploymentTypes] = useState({employments: []})
    const [professions, setProfessions] = useState({professions: []})
    const [allCountries, setAllCountries] = useState({allCountries: []});
    const [allCities, setAllCities] = useState({allCities: []});
    useEffect(() => {
        getProfessions();
        getEmploymentTypes();
        getCountries();
        getCities();
    }, [])
    const getProfessions = () => {
        axios
            .get("http://localhost:8080/profession/all", {headers: authHeader()})
            .then(data => {
                setProfessions({professions: data.data})
            })
            .catch(err => alert(err))

    }
    const getEmploymentTypes = () => {
        axios
            .get("http://localhost:8080/employmentType/all", {headers: authHeader()})
            .then(data => {
                setEmploymentTypes({employments: data.data})
            })
            .catch(err => alert(err))

    }
    const getCountries = () => {
        axios
            .get("http://localhost:8080/api/country", {headers: authHeader()})
            .then(data => {
                setAllCountries({allCountries: data.data})
            })
            .catch(err => alert(err))

    }
    const getCities = () => {
        axios
            .get("http://localhost:8080/api/city", {headers: authHeader()})
            .then(data => {
                setAllCities({allCities: data.data})
            })
            .catch(err => alert(err))

    }
    // const getCitiesByCountryId = (countryId) => {
    //     axios
    //         .get('http://localhost:8080/api/city/' + countryId, {headers: authHeader()})
    //         .then((data) => {
    //             setAllCities({allCities: data.data})
    //         })
    //         .catch((err) => alert(err))
    // };
    const createApp = () => {
        let address = {
            street: document.getElementById('street').value,
            building: document.getElementById('building').value,
            apartment: 0
        };

        axios
            .post('http://localhost:8080/address/create', address, {headers: authHeader()})
            .then(resp => {

                let app = {
                    price: 0,
                    agencyId: agencyId, employerId: employerId, creationDate: Date.now(),
                    endDate: endDate, statusName: 'NOT REVIEWED',
                    professionName: document.getElementById('profession').value,
                    salary: document.getElementById('salary').value,
                    employmentTypeName: document.getElementById('employmentType').value,
                    experienceName: document.getElementById('experience').value,
                    ageRestrictionName: document.getElementById('age').value,
                    countryName: document.getElementById('country').value,
                    cityName: document.getElementById('city').value,
                    addressId: resp.data, comment: document.getElementById('comment').value
                }
                console.log(app);
                alert();

                axios
                    .post('http://localhost:8080/employerApplication/create', app, {headers: authHeader()})
                    .then(() => {
                        setProfessions(professions)
                    })

                    .catch((err) => alert(err))
            })
            .catch((err) => alert(err))

    }
    return (
        <>
            <span className="login100-form-title p-b-37" style={styles.span}>Create application</span>

            <div style={styles.select100}>
                <select style={styles.select100} id="profession">
                    <option value="" disabled selected>Profession</option>
                    {professions.professions.map(val =>
                        <option style={styles.option100} key={val.id} value={val.name}
                        >{val.name}</option>)}
                </select>
                <span className="focus-input100"/>
            </div>
            <i>
                <text id='notValidProf' style={{color: "#e00a0a", display: 'none'}}>
                    Profession must be chosen!
                </text>
            </i>

            <div style={styles.divEnterData}>
                <input className="input100" type="text" id="salary" style={styles.input}
                       placeholder="Min salary"/>
                <span className="focus-input100"/>
            </div>
            <i>
                <text id='notValidSalary' style={{color: "#e00a0a", display: 'none'}}>
                    Salary must be numeric and shorter than 38 characters!
                </text>
            </i>
            <div style={styles.select100}>
                <select style={styles.select100} id="employmentType">
                    <option value="" disabled selected>Employment type</option>
                    {employmentTypes.employments.map(val =>
                        <option style={styles.option100} key={val.id} value={val.name}
                        >{val.name}</option>)}
                </select>
                <span className="focus-input100"/>
            </div>
            <i>
                <text id='notValidCostEType' style={{color: "#e00a0a", display: 'none'}}>
                    Employment type must be chosen!
                </text>
            </i>
            <div style={styles.select100}>
                <select style={styles.select100} id='experience'>
                    <option value="" disabled selected>Experience</option>
                    <option value="Up to 1 year">Up to 1 year</option>
                    <option value="1 - 3 years">1 - 3 years</option>
                    <option value="3 - 5 years">3 - 5 years</option>
                    <option value="5 - 8 years">5 - 8 years</option>
                    <option value="Over 8 years">Over 8 years</option>
                </select>
                <span className="focus-input100"/>
            </div>
            <i>
                <text id='notValidExp' style={{color: "#e00a0a", display: 'none'}}>
                    Experience must be chosen!
                </text>
            </i>

            <div style={styles.select100}>
                <select style={styles.select100} id='age'>
                    <option value="" disabled selected>Age restriction</option>
                    <option value="-">-</option>
                    <option value="Up to 30 years">Up to 30 years old</option>
                    <option value="Up to 40 years">Up to 40 years old</option>
                </select>
                <span className="focus-input100"/>
            </div>
            <i>
                <text id='notValidAge' style={{color: "#e00a0a", display: 'none'}}>
                    Age restriction must be chosen!
                </text>
            </i>
            <div style={styles.select100}>
                <select style={styles.select100} id="country">
                    <option style={styles.option100} disabled selected>Country</option>
                    {allCountries.allCountries.map(country =>
                        <option style={styles.option100} key={country.id} value={country.name}
                            // onClick={getCitiesByCountryId(country.id)}
                        >{country.name}</option>)}
                </select>
                <span className="focus-input100"/>
            </div>
            <i>
                <text id='notValidCostCountry' style={{color: "#e00a0a", display: 'none'}}>
                    Country must be chosen!
                </text>
            </i>
            <div style={styles.select100}>
                <select style={styles.select100} id="city">
                    <option style={styles.option100} disabled selected>City</option>
                    {allCities.allCities.map(city =>
                        <option style={styles.option100} key={city.id} value={city.name}
                        >{city.name}</option>)}
                </select>
                <span className="focus-input100"/>
            </div>

            <i>
                <text id='notValidCostCity' style={{color: "#e00a0a", display: 'none'}}>
                    City must be chosen!
                </text>
            </i>
            <div style={styles.divEnterData}>
                <input className="input100" type="text" id="street" style={styles.input}
                       placeholder="Street"/>
                <span className="focus-input100"/>
            </div>
            <i>
                <text id='notValidCostStreet' style={{color: "#e00a0a", display: 'none'}}>
                    Street must be between 1 and 50 characters!
                </text>
            </i>
            <div style={styles.divEnterData}>
                <input className="input100" type="text" id="building" style={styles.input}
                       placeholder="Building"/>
                <span className="focus-input100"/>
            </div>
            <i>
                <text id='notValidCostBuild' style={{color: "#e00a0a", display: 'none'}}>
                    Building must be number!
                </text>
            </i>

            <div style={styles.divEnterData}>
                <input className="input100" type="text" id="comment" style={styles.input}
                       placeholder="Comment"/>
                <span className="focus-input100"/>
            </div>

            <div className="container-login100-form-btn">
                <button type='button' className="login100-form-btn" onClick={
                    function () {
                        const prof = document.getElementById('profession').value === '';
                        const sal = document.getElementById('salary').value;
                        const employment = document.getElementById('employmentType').value === '';
                        const exp = document.getElementById('experience').value === '';
                        const age = document.getElementById('age').value === '';
                        const country = document.getElementById('country').value === 'Country';
                        const city = document.getElementById('city').value === 'City';
                        const street = document.getElementById('street').value;
                        const building = document.getElementById('building').value;
                        if (!prof && !employment && !exp && !age && !country && !city && sal && sal.isNaN && building && building.isNaN && street && street.length < 38) createApp();
                        else {
                            if (prof) document.getElementById('notValidProf').style.display = 'block';
                            if (employment) document.getElementById('notValidCostEType').style.display = 'block';
                            if (exp) document.getElementById('notValidExp').style.display = 'block';
                            if (age) document.getElementById('notValidAge').style.display = 'block';
                            if (country) document.getElementById('notValidCostCountry').style.display = 'block';
                            if (city) document.getElementById('notValidCostCity').style.display = 'block';
                            if (!sal || !sal.isNaN) document.getElementById('notValidSalary').style.display = 'block';
                            if (!building || !building.isNaN) document.getElementById('notValidCostBuild').style.display = 'block';
                            if (!street || street.length >= 38) document.getElementById('notValidCostStreet').style.display = 'block';
                        }
                    }}>Create
                </button>
            </div>
        </>
    )
}

export default EmployerApplication;