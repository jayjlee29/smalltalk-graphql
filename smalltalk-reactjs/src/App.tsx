import React from "react";
import "./App.css";
import { Outlet, Route, Routes } from "react-router-dom";
import Nav from "./components/Nav";
import SigninPage from "./pages/SigninPage";
import MainPage from "./pages/MainPage";

const Layout = () => {
  return (
    <div>
      <Nav />
      <Outlet />
    </div>
  );
};

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<MainPage />} />
          <Route path="signin" element={<SigninPage />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
