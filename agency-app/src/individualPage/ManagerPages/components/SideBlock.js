import React, {useState,useEffect} from "react"
import axios from'axios'


export default function SideBlock(props){
    const [users,setUsers] = useState({users:[],isLoading:true})

    useEffect(() => {

    }, [])

    return(
        <div id='sideBlock' style={{display: props.display, position: 'fixed', right: '10px', top: '50%', color: 'black'}} onClick={()=> {
            if (document.getElementById('sideBlockMainInfo').style.display === 'none'){
                document.getElementById('sideBlockMainInfo').style.display = 'block'
            }else {
                document.getElementById('sideBlockMainInfo').style.display = 'none'
            }
        }}>
            <h2>Eto malenkaya knopochka</h2>
            <div style={{display: 'block'}} id='sideBlockMainInfo'>
                <p>Employer: {props.data.employer.name}</p>
                <p>Profession: {props.data.profession.name}</p>
                <p>Salary: {props.data.salary}</p>
                <p>Features:</p>
                {props.data.features.map((feature) => {
                    return <p>{feature.name}</p>
                })}
            </div>
        </div>
    )

}