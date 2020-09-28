import React, {useState,useEffect} from "react"


export default function SideBlock(props){
    const [users,setUsers] = useState({users:[],isLoading:true})

    useEffect(() => {

    }, [])

    return(
        <div id='sideBlock' style={{display: props.display, position: 'fixed', right: '10px', top: '50%', color: 'black'}} onClick={()=> {
            if (document.getElementById('sideBlockMainInfo').style.display === 'none'){
                document.getElementById('side-block-button').style.display = 'none';
                document.getElementById('sideBlockMainInfo').style.display = 'block'
            }else {
                document.getElementById('side-block-button').style.display = 'block';
                document.getElementById('sideBlockMainInfo').style.display = 'none'
            }
        }}>
            <div id='side-block-button' style={{height: '50px', width: '50px', backgroundColor: 'black', right: '10px', marginLeft: 'auto'}}></div>
            <div style={{display: 'none'}} id='sideBlockMainInfo'>
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