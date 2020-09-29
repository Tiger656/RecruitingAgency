import axios from "axios";
import authHeader from "../auth/header";


const API_URL = "http://localhost:8080/api/";

const getPublicContent = () => {
    return axios.get(API_URL + "user");
};

const getUserBoard = () => {
    return axios.get(API_URL + "user/23", { headers: authHeader() });
};

const getModeratorBoard = () => {
    return axios.get(API_URL + "agencies", { headers: authHeader() });
};

const getAdminBoard = () => {
    return axios.get(API_URL + "admin", { headers: authHeader() });
};

export default {
    getPublicContent,
    getUserBoard,
    getModeratorBoard,
    getAdminBoard,
};