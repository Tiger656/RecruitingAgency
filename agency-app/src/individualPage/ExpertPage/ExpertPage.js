import React, {useEffect, useState} from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'animate.css/animate.min.css'
import axios from 'axios'
import {toast} from "react-toastify";



export const ExpertPage = () => {


    useEffect(() => {
        getUnconfirmedInterviews();
        getConfirmedInterviews();
    }, []);

    const getUnconfirmedInterviews = () => {
        axios.
            get()
    }

    const getConfirmedInterviews = () => {


    }





    return (
        <div>
            {/*<Notification />*/}
            <div>
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
                                        {/*<h1 className="to-animate">{agencyName}</h1>
                                        <h2 className="to-animate"> Email: {personEmail}</h2>*/}

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
                                <h2 className="to-animate">Interview</h2>
                            </div>
                        </div>
                        <br/><br/>

                    </div>
                    <br/><br/>
                </section>
            </div>
        </div>
    )
};
