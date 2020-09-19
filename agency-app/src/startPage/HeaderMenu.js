import React from 'react';
import PropTypes from 'prop-types';
import './SignIn.css';


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
let show = 0;

function sign() {
    document.getElementById("signInForm").style.display = "block";

}

function signNone() {
    document.getElementById("signInForm").style.display = "none";
    document.getElementById("userLogin").value = "";
    document.getElementById("userPassword").value = "";
}


function showSub() {

    if (show === 0) {
        document.getElementById("sub-menu").style.lineStyleType = "none";
        document.getElementById("sub-menu").style.height = "auto";
        document.getElementById("sub-menu").style.overflow = "visible";
        document.getElementById("sub-menu").style.opacity = "1";
        document.getElementById("sub-menu").style.paddingLeft = "calc(100% - 150px)";
        document.getElementById("sub-menu").style.marginTop = "-30px";
        show = 1;
    } else {
        toggleMenuStarStyle();
    }

}

function toggleMenuStarStyle() {
    document.getElementById("sub-menu").style.height = "0";
    document.getElementById("sub-menu").style.overflow = "hidden";
    document.getElementById("sub-menu").style.opacity = "0";
    document.getElementById("sub-menu").style.paddingLeft = "0";
    document.getElementById("sub-menu").style.marginTop = "0";
    show = 0;
}

function HeaderMenu(props) {
    const logo = props.logo;
    let langConst = [];
    if (props.lang === 'en') {
        langConst.push('About company');
        langConst.push('Our clients');
        langConst.push('Services');
        langConst.push('Contacts');
        langConst.push('Sign in');
        langConst.push('Sign up');
        langConst.push('password');
        langConst.push('Or login with');
        langConst.push('My page');
    } else {
        langConst.push('О компании');
        langConst.push('Наши клиенты');
        langConst.push('Услуги');
        langConst.push('Контакты');
        langConst.push('Войти');
        langConst.push('Зарегистрироваться');
        langConst.push('пароль');
        langConst.push('Или войти с помощью');
        langConst.push('Моя страница');
    }
    const st = {width: '150px', color: 'white', backgroundColor: 'black', marginLeft: '-23px'};
    const ulStyle = {height: '0', lineStyleType: 'none', overflow: 'hidden', opacity: '0', width: '0'};
    if (props.id === "")
        return (
            <section>
                <header id="header">

                    <nav className="navbar navbar-expand-lg" style={styles.nav}>
                        <div className="container-fluid">
                            <div className="d-flex align-items-center">
                                <a href="/home">
                                    <img style={styles.img} src={logo} alt=""/>
                                </a>
                            </div>

                            <button aria-label="Toggle navigation" type="button"
                                    className="navbar-toggler-right navbar-toggler">
                                <i className="fa fa-bars" id="fa-bars" onClick={showSub}>
                                    <svg width="1.5em" height="1em" viewBox="0 0 16 16" className="bi bi-list"
                                         id="bi-list"
                                         fill="currentColor"
                                         xmlns="http://www.w3.org/2000/svg" href="#">
                                        <path fillRule="evenodd"
                                              d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>

                                    </svg>
                                </i>

                            </button>

                            <div className="collapse navbar-collapse">
                                <ul className="ml-auto navbar-nav" id="ul">
                                    <li className="nav-item">
                                        <a aria-haspopup="true" href="#aboutCompany"
                                           className="active  nav-link"
                                           aria-expanded="false" style={styles.a}>{langConst[0]}</a>
                                    </li>
                                    <li className="position-static  nav-item">
                                        {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                                        <a aria-haspopup="true" href="#ourClients" className=" nav-link"
                                           aria-expanded="false" style={styles.a}>{langConst[1]}</a>
                                    </li>
                                    <li className="nav-item"><a href="#ourServices" className="nav-link"
                                                                style={styles.a}>{langConst[2]}</a>
                                    </li>
                                    <li className="nav-item">
                                        {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                                        <a aria-haspopup="true" className=" nav-link" style={styles.a}
                                           aria-expanded="false" href="#contacs">{langConst[3]}</a>
                                    </li>
                                    <li className="nav-item "><a href="#" className="nav-link"
                                                                 style={styles.a} onClick={sign}>{langConst[4]}</a>
                                    </li>

                                </ul>
                            </div>
                        </div>
                        <ul id="sub-menu" style={ulStyle}>
                            <li>
                                <a href="#aboutCompany" className="nav-link" style={st}>{langConst[0]}</a>
                            </li>
                            <li>
                                <a href="#ourClients" className="nav-link" style={st}>{langConst[1]}</a>
                            </li>
                            <li>
                                <a href="#ourServices" className="nav-link" style={st}>{langConst[2]}</a>
                            </li>
                            <li>
                                <a href="#contacts" className="nav-link" style={st}>{langConst[3]}</a>
                            </li>
                            <li><a href="#" className="nav-link" style={st} onClick={sign}>{langConst[4]}</a></li>

                        </ul>
                    </nav>

                </header>
                <div className="section-0" style={{paddingTop: '70px'}}>

                    <div className="wrap-login100 " id="signInForm" style={styles.divSign}>

                        <form className="login100-form validate-form">
                            <div className="cl-btn-7" onClick={signNone}/>
                            <br/>
                            <span style={styles.span}>
					{langConst[4]}
				</span>

                            <div style={styles.divEnterData} data-validate="Enter your email">
                                <input className="input100" type="text" style={styles.input} id="userLogin"
                                       name="username"
                                       placeholder="email"/>
                                <span className="focus-input100"/>
                            </div>

                            <div style={styles.divEnterData} data-validate="Enter password">
                                <input className="input100" type="password" id="userPassword" name="pass"
                                       style={styles.input}
                                       placeholder={langConst[6]}/>
                                <span className="focus-input100"/>
                            </div>

                            <div className="container-login100-form-btn">
                                <button className="login100-form-btn">
                                    {langConst[4]}
                                </button>
                            </div>

                            <div style={{textAlign: 'center', paddingTop: '20px', paddingBottom: '10px'}}>
					<span style={styles.txt}>
						{langConst[7]}
					</span>
                            </div>

                            <div style={styles.divFacebookGoogle}>
                                {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                                <a href="#" className="login100-social-item">
                                    <img
                                        src="https://www.freeiconspng.com/uploads/facebook-png-icon-follow-us-facebook-1.png"
                                        alt="FACEBOOK" style={{width: "30px"}}/>
                                </a>

                                {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                                <a href="#" className="login100-social-item">
                                    <img
                                        src="https://prooriginal.ru/image/catalog/demo/brendy/photo.jpg"
                                        alt="GOOGLE"/>
                                </a>
                            </div>
                        </form>


                    </div>


                    <div id="dropDownSelect1"/>
                    <script src="../jquery-3.2.1.min.js"/>
                </div>
            </section>
        );
    else return (
        <section>
            <header className="header" id="header">

                <nav className="navbar navbar-expand-lg " style={styles.nav}>
                    <div className="container-fluid">
                        <div className="d-flex align-items-center">
                            <a href="/home">

                                <img style={styles.img} src={logo} alt=""/>
                            </a>
                        </div>

                        <button aria-label="Toggle navigation" type="button"
                                className="navbar-toggler-right navbar-toggler">
                            <i className="fa fa-bars" id="fa-bars" onClick={showSub}>
                                <svg width="1.5em" height="1em" viewBox="0 0 16 16" className="bi bi-list" id="bi-list"
                                     fill="currentColor"
                                     xmlns="http://www.w3.org/2000/svg" href="#">
                                    <path fillRule="evenodd"
                                          d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>

                                </svg>
                            </i>

                        </button>

                        <div className="collapse navbar-collapse">
                            <ul className="ml-auto navbar-nav" id="ul">
                                <li className="dropdown nav-item">
                                    <a aria-haspopup="true" href="http://localhost:3000/home#aboutCompany"
                                       className="active dropdown-toggle nav-link"
                                       aria-expanded="false" style={styles.a}>{langConst[0]}</a>
                                </li>
                                <li className="position-static dropdown nav-item">
                                    {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                                    <a aria-haspopup="true" href="http://localhost:3000/home#ourClients"
                                       className="dropdown-toggle nav-link"
                                       aria-expanded="false" style={styles.a}>{langConst[1]}</a>
                                </li>
                                <li className="nav-item"><a href="http://localhost:3000/home#ourServices"
                                                            className="nav-link"
                                                            style={styles.a}>{langConst[2]}</a>
                                </li>
                                <li className="nav-item">
                                    {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                                    <a aria-haspopup="true" className="dropdown-toggle nav-link" style={styles.a}
                                       aria-expanded="false"
                                       href="http://localhost:3000/home#contacs">{langConst[3]}</a>
                                </li>
                                <li className="nav-item"><a href="http://localhost:3000/employerPage"
                                                            className="nav-link"
                                                            style={styles.a}>{langConst[8]}</a>
                                </li>

                            </ul>
                        </div>
                    </div>
                    <ul id="sub-menu" style={ulStyle}>
                        <li>
                            <a href="#aboutCompany" className="nav-link" style={st}>{langConst[0]}</a>
                        </li>
                        <li>
                            <a href="#ourClients" className="nav-link" style={st}>{langConst[1]}</a>
                        </li>
                        <li>
                            <a href="#ourServices" className="nav-link" style={st}>{langConst[2]}</a>
                        </li>
                        <li>
                            <a href="#contacts" className="nav-link" style={st}>{langConst[3]}</a>
                        </li>
                        <li><a href="/employerPage" className="nav-link" style={st}>{langConst[8]}</a></li>

                    </ul>
                </nav>

            </header>
            <div className="section-0" style={{paddingTop: '70px'}}>

                <div id="signInForm" style={styles.divSign}>

                    <form className="login100-form validate-form">
                        <div className="cl-btn-7" onClick={signNone}/>
                        <br/>
                        <span style={styles.span}>
					{langConst[4]}
				</span>

                        <div style={styles.divEnterData} data-validate="Enter your email">
                            <input className="input100" type="text" style={styles.input} id="userLogin" name="username"
                                   placeholder="email"/>
                            <span className="focus-input100"/>
                        </div>

                        <div style={styles.divEnterData} data-validate="Enter password">
                            <input className="input100" type="password" id="userPassword" name="pass"
                                   style={styles.input}
                                   placeholder={langConst[6]}/>
                            <span className="focus-input100"/>
                        </div>

                        <div className="container-login100-form-btn">
                            <button className="login100-form-btn">
                                {langConst[4]}
                            </button>
                        </div>

                        <div style={{textAlign: 'center', paddingTop: '20px', paddingBottom: '10px'}}>
					<span style={styles.txt}>
						{langConst[7]}
					</span>
                        </div>

                        <div style={styles.divFacebookGoogle}>
                            {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                            <a href="#" className="login100-social-item">
                                <img
                                    src="https://www.freeiconspng.com/uploads/facebook-png-icon-follow-us-facebook-1.png"
                                    alt="FACEBOOK" style={{width: "30px"}}/>
                            </a>

                            {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                            <a href="#" className="login100-social-item">
                                <img
                                    src="https://prooriginal.ru/image/catalog/demo/brendy/photo.jpg"
                                    alt="GOOGLE"/>
                            </a>
                        </div>
                    </form>


                </div>


                <div id="dropDownSelect1"/>
                <script src="../jquery-3.2.1.min.js"/>
            </div>
        </section>
    );
}


HeaderMenu.propTypes = {
    logo: PropTypes.string,
    telephone: PropTypes.string
};

export default HeaderMenu;