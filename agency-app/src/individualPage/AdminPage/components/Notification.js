import React from "react";

export const Notification=({errorMessage})=>{
    return(

       <div className='animate__animated animate__backInRight' style={{textAlign:'left'}}>
           <form style={{width:'20%',height:'10%',backgroundColor:"whitesmoke",textAlight:'left'}}>
           <label style={{color:'black'}}>{errorMessage}</label>
           </form>
       </div>


    )
}