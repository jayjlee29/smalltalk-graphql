import React from "react";
import { Outlet } from "react-router-dom";
import Nav from "../components/Nav";
import Sidebar from "../components/SideBar";

const Layout: React.FC = (): JSX.Element => {
  return (
    <div className="flex">
      <nav className="bg-gray-200 h-screen fixed w-48 md:w-36 lg:w-48">
        <Nav />
      </nav>

      <main className="flex-grow ml-[144px] md:ml-[192px] p-4 flex flex-col md:flex-row gap-4">
        <section className="flex-grow w-[calc(100%-12rem)] md:w-[calc(100%-9rem)] h-[100vh] overflow-y-scroll mt-3 mx-auto">
          <Outlet />
        </section>

        <Sidebar />
      </main>
    </div>
  );
};

export default Layout;
