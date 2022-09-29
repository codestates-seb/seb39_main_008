import axios from 'axios';

const headers = { authorization: 'bearer test' };

// eslint-disable-next-line no-undef
const url = process.env.REACT_APP_API_URL;

export const siginup = async (data) => {
  try {
    const res = await axios.post(`${url}/api/v1/auth/signup`, data);
    return res.data;
  } catch (error) {
    console.log('signup', error);
  }
};

export const login = async (data) => {
  try {
    const res = await axios.post(`${url}/api/v1/auth/signin`, data);
    return res.data;
  } catch (error) {
    console.log('signup', error);
  }
};

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

export const getDiary = async (diaries_id) => {
  try {
    const res = await axios.get(`/api/v1/diaries/${diaries_id}`);
    return res.data;
  } catch (err) {
    console.log('getDiary', err);
  }
};

export const DeletDiary = async (diaries_id = 1) => {
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

export const getComment = async (diaryId, page = 1, size = 10) => {
  try {
    return await axios.get(
      `/api/v1/comments/?diaryId=${diaryId}&page=${page}&size=${size}`
    );
  } catch (error) {
    console.log('getComment', error);
  }
};

export const addComment = async (data) => {
  try {
    return await axios.post(`/api/v1/comments/`, data);
  } catch (error) {
    console.log('addComment', error);
  }
};

export const DeleteComment = async (comment_id) => {
  try {
    return await axios.delete(`/api/v1/comments/${comment_id}`);
  } catch (error) {
    console.log('DeleteComment', error);
  }
};

export const patchComment = async (comment_id, data) => {
  try {
    return await axios.patch(`api/v1/comments/${comment_id}`, data);
  } catch (error) {
    console.log('patchComment', error);
  }
};

export const likeDiary = async (diayId) => {
  try {
    return await axios.post(`/api/v1/likes`, { diayId });
  } catch (error) {
    console.log('likeDiary', error);
  }
};

export const followUser = async (followingMemberId) => {
  try {
    return await axios.post(
      `/api/vi/follow`,
      { followingMemberId },
      {
        headers: {
          'content-type': 'text/json',
        },
      }
    );
  } catch (error) {
    console.log('followUser', error);
  }
};
