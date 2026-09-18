import axios from 'axios';

const API_URL = 'http://localhost:8080/cashcards';

export const getAuthHeader = (username: string, password: string) => {
    const token = btoa(`${username}:${password}`);
    return {
        headers: {
            Authorization: `Basic ${token}`,
            'Content-Type': 'application/json',
        },
    };
};

export const getCashCards = async (user: string, pass: string, page = 0, size = 10) => {
    return await axios.get(`${API_URL}?page=${page}&size=${size}`, getAuthHeader(user, pass));
};

export const createCashCard = async (user: string, pass: string, amount: number) => {
    return await axios.post(API_URL, { amount }, getAuthHeader(user, pass));
};

export const deleteCashCard = async (user: string, pass: string, id: number) => {
    return await axios.delete(`${API_URL}/${id}`, getAuthHeader(user, pass));
};