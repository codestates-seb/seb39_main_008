import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Oscar from './pages/Oscar';
import Swan from './pages/Swan';
const Router = () => {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Oscar />}></Route>
          <Route path="/1" element={<Swan />}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default Router;
