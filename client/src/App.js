import React, { Suspense, useEffect } from 'react';
import { Routes, Route } from 'react-router-dom';
import Loading from './components/common/Loading';
const Root = React.lazy(() => import('./pages/Root'));
const Error = React.lazy(() => import('./pages/Error'));
const Landing = React.lazy(() => import('./pages/Landing'));
const Signin = React.lazy(() => import('./pages/Signin'));
const Main = React.lazy(() => import('./pages/Main'));
const Book = React.lazy(() => import('./pages/Book'));
const BookList = React.lazy(() => import('./pages/BookList'));
const Diary = React.lazy(() => import('./pages/Diary'));
const MakeBook = React.lazy(() => import('./pages/MakeBook'));
const WriteDiary = React.lazy(() => import('./pages/WriteDiary'));
const Userpage = React.lazy(() => import('./pages/Userpage'));
const People = React.lazy(() => import('./pages/People'));
import axios from 'axios';
function App() {
  useEffect(() => {
    axios.get('/api/v1/members', {
      headers: { msg: 'hello world!', aa: 'aaaa' },
    });
  }, []);
  return (
    <Suspense fallback={<Loading />}>
      <Routes>
        <Route path="*" element={<Error />}></Route>
        <Route
          path="/"
          element={
            <Root>
              <Main />
              <Landing />
            </Root>
          }
        ></Route>
        <Route path="/signin" element={<Signin />}></Route>
        <Route path="/book/:bookId" element={<Book />}></Route>
        <Route path="/books/:memberId" element={<BookList />}></Route>
        <Route path="/book/:bookId/:diaryId" element={<Diary />}></Route>
        <Route path="/makebook" element={<MakeBook />}></Route>
        <Route path="/writediary" element={<WriteDiary />}></Route>
        <Route path="/user/:memberId" element={<Userpage />}></Route>
        <Route path="/people" element={<People />}></Route>
      </Routes>
    </Suspense>
  );
}

export default App;
