import axios from 'axios';
const headers = { authorization: 'bearer test' };

export const getDiaries = async (
  page = 1,
  size = 10,
  sortby = 'recentdesc'
) => {
  try {
    const res = await axios.get(
      `/api/v1/diaries/?page=${page}&size=${size}&sortby=${sortby}`
    );
    return res.data;
  } catch (err) {
    console.log('getDiaries', err);
  }
};

export const getMembers = async (
  page = 1,
  size = 10,
  sortby = 'recentdesc'
) => {
  try {
    const res = await axios.get(
      `/api/v1/members/?page=${page}&size=${size}&sortby=${sortby}`
    );
    return res.data;
  } catch (err) {
    console.log('getMembers', err);
  }
};

export const getMember = async (member_id) => {
  try {
    return (await axios.get(`/api/v1/members/${member_id}`)).data;
  } catch (err) {
    console.log('getMember', err);
  }
};

export const patchMember = async (data) => {
  try {
    return await axios.patch('/api/v1/members', { data }, { headers });
  } catch (err) {
    console.log('patchMember', err);
  }
};
export const getDiary = async (diaries_id = 1) => {
  try {
    return await axios.get(`/api/v1/diaries/${diaries_id}`);
  } catch (err) {
    console.log(err);
  }
};

export const DltDiary = async (diaries_id = 1) => {
  try {
    return await axios.delete(`/api/vi/diaries/${diaries_id}`);
  } catch (err) {
    console.log('Dltdiary', err);
  }
};

export const getBookList = async (member_id) => {
  try {
    const res = await axios.get(`/api/v1/books/?memberid=${member_id}`);
    return res.data;
  } catch (err) {
    console.log('getBookList', err);
  }
};
export const getBook = async (book_id) => {
  try {
    return await axios.get(`/api/v1/books/${book_id}`);
  } catch (err) {
    console.log('getBookList', err);
  }
};
