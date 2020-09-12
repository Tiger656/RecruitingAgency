import React, {useState,useEffect} from "react"
import axios from'axios'
export const UsersTable = () => {
    const [users,setUsers] = useState({users:[],isLoading:true})

    useEffect(() => {
        getUsers();
    }, [])
    const getUsers = () => {
        axios
            .get("http://localhost:8080/api/user")
            .then(data => {
                console.log(data.data)
                setUsers({users:data.data,isLoading: false})
            })
            .catch(err => alert(err))

    }

    if(users.isLoading){
        return (
            <div className="spinner-border text-primary spinner" role="status">
                <span className="sr-only">Loading...</span>
            </div>
        )
    }
    return(
        <div className="container">
            <table className="table list">
                <thead className="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Email</th>
                    <th scope="col">Password</th>
                    <th scope="col">Agency</th>

                </tr>
                </thead>
                <tbody>
                {users.users.map(user => (
                    <tr key={user.email}>
                        <th scope="row">{user.id}</th>
                        <td>{user.email}</td>
                        <td>{user.password}</td>
                        <td>{user.agencyName}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )

}