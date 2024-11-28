import React, { Suspense } from "react";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import SigninPage from "./pages/SigninPage";
import MainPage from "./pages/MainPage";
import Fallback from "./components/Fallback";
import Greeting from "./pages/GraphQlCheck";
import Layout from "./Layout/Layout";

function App(): JSX.Element {
  return (
    <Suspense fallback={<Fallback />}>
      <div className="App">
        <Routes>
          <Route path="/" index element={<SigninPage />} />
          <Route path="/main" element={<Layout />}>
            <Route index element={<MainPage />} />
            <Route path="greeting" element={<Greeting />} />
          </Route>
        </Routes>
      </div>
    </Suspense>
  );
}

export default App;
