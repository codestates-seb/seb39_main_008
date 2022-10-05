import apiInstance from './instance';
import { removeFromLocalStorage, setToLocalStorage } from './localStorage';

export const signup = async (data) => {
  try {
    const res = await apiInstance.post(`/api/v1/auth/signup`, data);
    return res.data;
  } catch (error) {
    console.log('signup', error);
  }
};

export const login = async (data) => {
  try {
    const res = await apiInstance.post(`/api/v1/auth/signin`, data);
    return res.data;
  } catch (error) {
    console.log('signup', error);
  }
};
export const getRefreshToken = async () => {
  try {
    const res = await apiInstance.get(`/api/v1/auth/reissue`);
    const { accessToken, refreshToken } = res.data;
    setToLocalStorage('accessToken', accessToken);

    if (refreshToken) {
      setToLocalStorage('refreshToken', refreshToken);
    }

    return accessToken;
  } catch (e) {
    removeFromLocalStorage('accessToken');
    removeFromLocalStorage('refreshToken');
  }
};

export const getDiaries = async (
  page = 1,
  size = 10,
  sortby = 'recentdesc'
) => {
  try {
    const res = await apiInstance.get(
      `/api/v1/diaries/?page=${page}&size=${size}&sortby=${sortby}`
    );
    return res.data;
  } catch (err) {
    console.log('getDiaries', err);
  }
};
export const reIssue = async () => {};

export const getMembers = async (
  page = 1,
  size = 10,
  sortby = 'recentdesc'
) => {
  try {
    const res = await apiInstance.get(
      `/api/v1/members/?page=${page}&size=${size}&sortby=${sortby}`
    );
    return res.data;
  } catch (err) {
    console.log('getMembers', err);
  }
};

export const getMember = async (member_id) => {
  try {
    const res = await apiInstance.get(`/api/v1/members/${member_id}`);
    return res.data;
  } catch (err) {
    console.log('getMember', err);
  }
};

export const patchMember = async (data) => {
  try {
    return await apiInstance.patch('/api/v1/members', data);
  } catch (err) {
    console.log('patchMember', err);
  }
};

export const getDiary = async (diaries_id) => {
  try {
    const res = await apiInstance.get(`/api/v1/diaries/${diaries_id}`);
    return res.data;
  } catch (err) {
    console.log('getDiary', err);
  }
};

export const addDiary = async (data) => {
  try {
    const res = await apiInstance.post(`/api/v1/diaries`, data);
    return res.data;
  } catch (error) {
    console.log('addDiary', error);
  }
};

export const DeletDiary = async (diaries_id = 1) => {
  try {
    return await apiInstance.delete(`/api/vi/diaries/${diaries_id}`);
  } catch (err) {
    console.log('Dltdiary', err);
  }
};

export const getBookList = async (member_id) => {
  try {
    const res = await apiInstance.get(`/api/v1/books/?memberid=${member_id}`);
    return res.data;
  } catch (err) {
    console.log('getBookList', err);
  }
};

export const addBook = async (data) => {
  try {
    const res = await apiInstance.post(`/api/v1/books`, data);
    return res;
  } catch (error) {
    console.log('addBook', error);
  }
};
export const getBook = async (book_id) => {
  try {
    const res = await apiInstance.get(`/api/v1/books/${book_id}`);
    return res.data;
  } catch (err) {
    console.log('getBookList', err);
  }
};

export const getComment = async (diaryId, page = 1, size = 10) => {
  try {
    const res = await apiInstance.get(
      `/api/v1/comments/?diaryId=${diaryId}&page=${page}&size=${size}`
    );
    return res.data;
  } catch (error) {
    console.log('getComment', error);
  }
};

export const addComment = async (data) => {
  try {
    return await apiInstance.post(`/api/v1/comments/`, data);
  } catch (error) {
    console.log('addComment', error);
  }
};

export const DeleteComment = async (comment_id) => {
  try {
    return await apiInstance.delete(`/api/v1/comments/${comment_id}`);
  } catch (error) {
    console.log('DeleteComment', error);
  }
};

export const patchComment = async (comment_id, data) => {
  try {
    return await apiInstance.patch(`/api/v1/comments/${comment_id}`, data);
  } catch (error) {
    console.log('patchComment', error);
  }
};

export const likeDiary = async (diayId) => {
  try {
    return await apiInstance.post(`//api/v1/likes`, { diayId });
  } catch (error) {
    console.log('likeDiary', error);
  }
};

export const followUser = async (followingMemberId) => {
  try {
    return await apiInstance.post(`/api/vi/follow`, { followingMemberId });
  } catch (error) {
    console.log('followUser', error);
  }
};
