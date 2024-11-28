import React from "react";
import { IoSearchOutline } from "react-icons/io5";

const SearchBar: React.FC = () => {
  return (
    <div className="flex items-center justify-around rounded-3xl overflow-hidden bg-slate-500 w-full h-[50px]">
      <IoSearchOutline className="w-5 h-5 text-white ml-3" />
      <input
        type="text"
        className="block text-lg outline-none bg-slate-500 text-white mr-3 w-full"
        placeholder="search"
      />
    </div>
  );
};

export default SearchBar;
