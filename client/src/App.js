import React, { Suspense } from 'react';
import { Routes, Route } from 'react-router-dom';
import { path } from './pages/router';
import Loading from './components/common/Loading';
const Layout = React.lazy(() => import('./pages/Layout'));
const Root = React.lazy(() => import('./pages/Root'));
const Error = React.lazy(() => import('./pages/Error'));
const Landing = React.lazy(() => import('./pages/Landing'));
const Signup = React.lazy(() => import('./pages/Signup'));
const Login = React.lazy(() => import('./pages/Login'));
const Main = React.lazy(() => import('./pages/Main'));
const Book = React.lazy(() => import('./pages/Book'));
const BookList = React.lazy(() => import('./pages/BookList'));
const Diary = React.lazy(() => import('./pages/Diary'));
const MakeBook = React.lazy(() => import('./pages/MakeBook'));
const WriteDiary = React.lazy(() => import('./pages/WriteDiary'));
const Userpage = React.lazy(() => import('./pages/Userpage'));
const People = React.lazy(() => import('./pages/People'));
const EditDiary = React.lazy(() => import('./pages/EditDiary'));
const EditBook = React.lazy(() => import('./pages/EditBook'));

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
          path={path.signUp}
          element={
            <Layout hasCommon={false}>
              <Signup />
            </Layout>
          }
        ></Route>
        <Route
          path={path.logIn}
          element={
            <Layout hasCommon={false}>
              <Login />
            </Layout>
          }
        ></Route>
        <Route
          path={path.root}
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
          path={path.bookList}
          element={
            <Layout>
              <BookList />
            </Layout>
          }
        ></Route>
        <Route
          path={path.book}
          element={
            <Layout>
              <Book />
            </Layout>
          }
        ></Route>
        <Route
          path={path.makeBook}
          element={
            <Layout>
              <MakeBook />
            </Layout>
          }
        ></Route>
        <Route
          path={path.editBook}
          element={
            <Layout>
              <EditBook />
            </Layout>
          }
        ></Route>

        <Route
          path={path.diary}
          element={
            <Layout>
              <Diary />
            </Layout>
          }
        ></Route>
        <Route
          path={path.writeDiary}
          element={
            <Layout>
              <WriteDiary />
            </Layout>
          }
        ></Route>
        <Route
          path={path.editDiary}
          element={
            <Layout>
              <EditDiary />
            </Layout>
          }
        ></Route>
        <Route
          path={path.userpage}
          element={
            <Layout>
              <Userpage />
            </Layout>
          }
        ></Route>
        <Route
          path={path.people}
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
