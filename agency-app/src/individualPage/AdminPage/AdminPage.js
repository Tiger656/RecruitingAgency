import React, {useEffect, useState} from "react";
import {UserTable} from "../AdminPage/components/UserTable"
import 'bootstrap/dist/css/bootstrap.min.css';
import 'animate.css/animate.min.css'
import axios from 'axios'
import {Modal} from "./components/Modal";
import {Filter} from "./components/Filter";
import {Notification} from "./components/Notification";
import authHeader from "../../auth/header";
import {toast} from "react-toastify";


export const AdminPage = () => {
    const [editing, setEditing] = useState(false)
    const initialFormState = {id: null, email: '', agencyName: '', agencyId: '', agency: {}, roleIds: [], roles: []}
    const [currentUser, setCurrentUser] = useState(initialFormState)
    const [users, setUsers] = useState([])
    const [loading, setLoading] = useState(true);
    const [isModalCreate, setIsModalCreate] = useState(true)
    const [allRoles, setAllRoles] = useState([]);
    const [allAgencies, setAllAgencies] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [searchResults, setSearchResults] = useState([]);

    useEffect(() => {
        getUsers();
        // getAgencies();
        getRoles();
    }, []);
    let agencyName = JSON.parse(localStorage.getItem('response')).agency.name;
    let personEmail = JSON.parse(localStorage.getItem('response')).email;

    const getUsers = () => {
        axios
            .get('http://localhost:8080/api/user?name=' + agencyName, {headers: authHeader()})
            .then((data) => {
                setUsers(data.data)
                setLoading(false)
            })
            .catch((err) => errorNotify(err.response.data.error))
        console.log(users);

    };

    const deleteUs = id => {
        axios
            .delete('http://localhost:8080/api/user/' + id, {headers: authHeader()})
            .then(()=>successNotify('User delete successfully!'))
            .catch((err) => errorNotify(err.response.data.error))
    };

    // const getAgencies = () => {
    //     axios
    //         .get('http://localhost:8080/api/agencies', {headers: authHeader()})
    //         .then((data) => setAllAgencies(data.data))
    //         .catch((err) => errorNotify(err.response.data.error))
    //
    // };

    const getRoles = () => {
        axios
            .get('http://localhost:8080/api/roles', {headers: authHeader()})
            .then((data) => setAllRoles(data.data))
            .catch((err) => errorNotify(err.response.data.error))
    };

    const handleChange = e => {
        setSearchTerm(e);
        e !== '' ? setSearchResults(users.filter(user => user.email.toLowerCase().includes(searchTerm.toLowerCase()))) : setSearchResults(users);
    };

    const modalCreateClickHandler = () => setIsModalCreate(false);

    const modalCloseClickHandler = () => setIsModalCreate(true);

    const addUser = user => {
        setIsModalCreate(true)

        axios
            .post('http://localhost:8080/api/user', user, {headers: authHeader()})
            .then(resp => {
                setUsers([...users, resp.data]);
                successNotify("User with email: "+user.email+" created successfully!");
            })
            .catch((err) => errorNotify(err.response.data.error))
    }

    const update = user => {
        setLoading(true)
        axios
            .put('http://localhost:8080/api/user', user, {headers: authHeader()})
            .then(resp => {setUsers(users.map(user => (user.id === resp.data.id ? resp.data : user)));
                successNotify('User is update successfully!')})
            .then(() => setLoading(false))
            .catch((err) => errorNotify(err.response.data.error))
    }

    const deleteUser = id => {
        deleteUs(id);
        setUsers(users.filter(user => user.id !== id))
    }

    const updateUser = updatedUser => {
        setEditing(false)
        update(updatedUser);
        setUsers(users.map(user => (user.id === updatedUser.id ? updatedUser : user)))
    }

    const editRow = user => {
        setEditing(true)
        setCurrentUser({
            id: user.id,
            email: user.email,
            agencyId: user.agencyId,
            agency: user.agency,
            roleIds: user.roleIds,
            roles: user.roles
        })
    }
    const errorNotify = (error) => {
        toast.error(error, {position: toast.POSITION.TOP_RIGHT});
    }
    const successNotify =(message)=>{
        toast.success(message, {position: toast.POSITION.TOP_RIGHT});
    }



    return (
        <div>
            <Notification />
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
                                        <h1 className="to-animate">{agencyName}</h1>
                                        <h2 className="to-animate"> Email: {personEmail}</h2>

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
                                <h2 className="to-animate">Users</h2>
                            </div>
                        </div>
                        <br/><br/>
                        <div className="fh5co-text">
                            <div className="container">
                                {
                                    editing &&
                                    <Modal
                                        submitHandler={updateUser}
                                        id={currentUser?.id}
                                        email={currentUser?.email}
                                        agencyId={currentUser?.agencyId}
                                        roleIds={currentUser?.roleIds}
                                        roles={currentUser?.roles}
                                        agency={currentUser?.agency}
                                        allRoles={allRoles}
                                        allAgencies={allAgencies}
                                        onModalCloseClick={modalCloseClickHandler}
                                        buttonName={'Update'}
                                    />
                                }

                                <div className="flex-row">
                                    <div className="flex-large">
                                        <div>
                                            <button className="btn btn-dark" onClick={modalCreateClickHandler}>Add new
                                                user
                                            </button>
                                            {!isModalCreate &&
                                            <Modal submitHandler={addUser} onModalCloseClick={modalCloseClickHandler}
                                                   allRoles={allRoles}
                                                   allAgencies={allAgencies}
                                                   buttonName={'Create'}
                                            />}
                                        </div>
                                    </div>

                                    <Filter value={searchTerm} handleChange={e => handleChange(e.target.value)}/>
                                    <div className="flex-large">
                                        <h2>View users</h2>

                                        <UserTable users={searchResults.length < 1 ? users : searchResults}
                                                   editRow={editRow} deleteUser={deleteUser} loading={loading}
                                                   allAgencies={allAgencies}/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/><br/>
                </section>
            </div>
        </div>
    )
};
