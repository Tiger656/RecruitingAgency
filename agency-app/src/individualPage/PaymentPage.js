import React, {useState, useRef} from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";




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
};
const PaymentPage = (props) => {


    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    const onChangeUsername = (e) => {
        const email = e.target.value;
        setEmail(email);
    };

    const onChangePassword = (e) => {
        const password = e.target.value;
        setPassword(password);
    };



    return (
        <div className="col-md-12">

            <div className="card card-container">
                <h1>Payment</h1>

                <Form  className="login100-form validate-form">

                    <div className="form-group" style={styles.divEnterData}>
                        <label htmlFor="Email">Email</label>
                        <Input
                            type="text"
                            className="input100"
                            style={styles.input}
                            name="Email"

                            onChange={onChangeUsername}

                        />
                        <span className="focus-input100"/>
                    </div>

                    <div className="form-group" style={styles.divEnterData}>
                        <label htmlFor="password">Enter sum in $</label>
                        <Input
                            type="text"
                            className="input100"
                            name="sum"
                            style={styles.input}
                            onChange={onChangePassword}

                        />
                        <span className="focus-input100"/>
                    </div>

                    <div className="container-login100-form-btn">
                        <button className="login100-form-btn" disabled={loading}>
                            {loading && (
                                <span className="spinner-border spinner-border-sm"/>
                            )}
                            <span>Pay</span>
                        </button>
                    </div>
                    <br/>
                    {message && (
                        <div className="form-group">
                            <div className="alert alert-danger" role="alert">
                                {message}
                            </div>
                        </div>
                    )}


                </Form>
            </div>
            <div id="dropDownSelect1"/>
            <script src="../../jquery-3.2.1.min.js"/>
        </div>


    );
};

export default PaymentPage;
