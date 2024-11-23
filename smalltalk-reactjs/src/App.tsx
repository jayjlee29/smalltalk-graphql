import React, { Suspense } from "react";
import "./App.css";
import { Outlet, Route, Routes } from "react-router-dom";
import Nav from "./components/Nav";
import SigninPage from "./pages/SigninPage";
import MainPage from "./pages/MainPage";
import Fallback from "./components/Fallback";

const Layout = () => {
  return (
    <div>
      <Nav />
      <Outlet />
    </div>
  );
};

function App(): JSX.Element {
  return (
    <Suspense fallback={<Fallback />}>
      <div className="App">
        <Routes>
          <Route path="/" index element={<SigninPage />} />
          <Route path="/main" element={<Layout />}>
            <Route index element={<MainPage />} />
          </Route>
        </Routes>
      </div>
    </Suspense>
  );
}

export default App;
