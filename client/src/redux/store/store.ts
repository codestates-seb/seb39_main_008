import { configureStore } from '@reduxjs/toolkit';
import { userSlice } from '../slices/userSlice';

export const store = configureStore({
  reducer: {},
});

export type RootState = ReturnType<typeof store.getState>;
