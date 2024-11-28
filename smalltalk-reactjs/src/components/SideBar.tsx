// src/components/Sidebar.tsx
import React from "react";
import SearchBar from "./SearchBar";
import TrendSection from "./TrendSection";

const Sidebar: React.FC = (): JSX.Element => {
  return (
    <aside className="hidden md:flex flex-col lg:w-[25vw] md:w-[40vw] h-[90vh] mt-3 mr-2 overflow-y-scroll">
      <SearchBar />
      <TrendSection />
    </aside>
  );
};

export default Sidebar;
