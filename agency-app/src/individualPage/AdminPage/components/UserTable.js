import React from 'react'

export const UserTable = ({users,editRow,deleteUser,loading}) => {
    let i =1;
    const handleDeleteUser = id => {
        let answer = window.confirm("Are you sure?")
        if(answer){
            deleteUser(id)
        }
    }

    const handlerEditUser =(user) =>{
        editRow(user);
    }



    if(loading) {
        return (
            <div className="spinner-border text-primary spinner" role="status">
                <span className="sr-only">Loading...</span>
            </div>
        )
    }

    return (

        <table className="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Email</th>

                <th scope="col">Agency</th>
                <th scope="col">Roles</th>

                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            {users.length > 0 ? (
                users.map(user => (
                    <tr key={user.id}>
                        <td>{i++}</td>
                        <td>{user.email}</td>
                        {/*fix it*/}
                        <td>{user.agencyName}</td>
                        <td>{user.roles.map(role=>
                            <p style={{color:"black"}} key={role.id}>{role.name}</p>)}
                        </td>
                        <td>


                            <button  style={{marginRight:'10px'}} className="btn btn-dark"
                                     onClick={() => {handlerEditUser(user)}}

                            >
                                Edit
                            </button>
                            <button className="btn btn-dark"
                                    onClick={() => handleDeleteUser(user.id)}>Delete</button>

                        </td>
                    </tr>
                ))
            ) : (
                <tr>
                    <td colSpan={3}>No users</td>
                </tr>
            )}
            </tbody>
        </table>

    )
}
