import React from 'react';
import "../cssForIndividualPage/animate.css";
import "../cssForIndividualPage/icomoon.css";
import "../cssForIndividualPage/simple-line-icons.css";
import "../cssForIndividualPage/magnific-popup.css";
import "../cssForIndividualPage/style.css";


function EmployeePage(props) {
    const name = props.name;
    const email = props.email;
    const lang = props.lang;
    let langConst = [];
    if (lang === 'en') {
        langConst.push('Status');
        langConst.push('Contract');
        langConst.push('Interview');
        langConst.push('Interviews');
    } else {
        langConst.push('Статус');
        langConst.push('Контракт');
        langConst.push('Интервью');
        langConst.push('Интервью');
    }
    return (

        <div style={{marginTop: '-52px'}}>

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
                                    <h1 className="to-animate">{name}</h1>
                                    <h2 className="to-animate"> Email: {email}</h2>
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
                            <h2 className="to-animate">{langConst[3]}</h2>
                        </div>
                    </div>
                    <br/><br/>
                    <div className="row row-bottom-padded-sm">
                        <div className="col-md-4 col-sm-6">
                            <a href="../images/work_1.jpg" className="fh5co-project-item image-popup to-animate">
                                <img src="../images/work_1.jpg" alt="Image" className="img-responsive"/>
                                <div className="fh5co-text">
                                    <h2>{langConst[0]} 1</h2>
                                    <span>{langConst[2]} </span>
                                </div>
                            </a>
                        </div>
                        <div className="col-md-4 col-sm-6">
                            <a href="../images/work_2.jpg" className="fh5co-project-item image-popup to-animate">
                                <img src="../images/work_2.jpg" alt="Image" className="img-responsive"/>
                                <div className="fh5co-text">
                                    <h2>{langConst[0]} 2</h2>
                                    <span>{langConst[2]} </span>
                                </div>
                            </a>
                        </div>

                    </div>
                    <br/><br/>
                </div>

            </section>

            <section id="fh5co-work" className="section section-6" data-section="work">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12 section-heading text-center">
                            <h2 className="to-animate">{langConst[1]}</h2>
                        </div>
                    </div>
                    <br/><br/>
                    <div className="row row-bottom-padded-sm">
                        <div className="col-md-4 col-sm-6 ">
                            <a href="../images/work_1.jpg" className="fh5co-project-item image-popup to-animate">
                                <img src="../images/work_1.jpg" alt="Image" className="img-responsive"/>
                                <div className="fh5co-text">
                                    <h2>{langConst[1]} </h2>
                                </div>
                            </a>
                        </div>
                        <div className="clearfix visible-sm-block"/>
                    </div>
                </div>
            </section>
        </div>

    )
}

export default EmployeePage;