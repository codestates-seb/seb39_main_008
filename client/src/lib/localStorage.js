export const setToLocalStorage = (key, value) => {
  window.localStorage.setItem(key, JSON.stringify(value));
};

export const getFromLocalStorage = (key) => {
  const value = window.localStorage.getItem(key);
  if (value) return JSON.parse(value);
};

export const removeFromLocalStorage = (key) => {
  window.localStorage.removeItem(key);
};
