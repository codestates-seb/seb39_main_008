import React, { Suspense } from 'react';
import { Routes, Route } from 'react-router-dom';
import Loading from './components/common/Loading';
import Layout from './pages/Layout';
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

function App() {
  return (
    <Suspense fallback={<Loading />}>
      <Routes>
        <Route
          path="*"
          element={
            <Layout>
              <Error />
            </Layout>
          }
        ></Route>
        <Route
          path="/"
          element={
            <Root>
              <Layout>
                <Main />
              </Layout>
              <Layout hasCommon={false}>
                <Landing />
              </Layout>
            </Root>
          }
        ></Route>
        <Route
          path="/signin"
          element={
            <Layout hasCommon={false}>
              <Signin />
            </Layout>
          }
        ></Route>
        <Route
          path="/book/:bookId"
          element={
            <Layout>
              <Book />
            </Layout>
          }
        ></Route>
        <Route
          path="/books/:memberId"
          element={
            <Layout>
              <BookList />
            </Layout>
          }
        ></Route>
        <Route
          path="/book/:bookId/:diaryId"
          element={
            <Layout>
              <Diary />
            </Layout>
          }
        ></Route>
        <Route
          path="/makebook"
          element={
            <Layout>
              <MakeBook />
            </Layout>
          }
        ></Route>
        <Route
          path="/writediary"
          element={
            <Layout>
              <WriteDiary />
            </Layout>
          }
        ></Route>
        <Route
          path="/user/:memberId"
          element={
            <Layout>
              <Userpage />
            </Layout>
          }
        ></Route>
        <Route
          path="/people"
          element={
            <Layout>
              <People />
            </Layout>
          }
        ></Route>
      </Routes>
    </Suspense>
  );
}

export default App;
