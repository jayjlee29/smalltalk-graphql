import React from "react";
import { IoSearchOutline } from "react-icons/io5";

const MainPage = () => {
  return (
    <div>
      <div className="flex items-center">
        {/* main container */}
        <div className="lg:w-[55vw] md:w-[45vw] h-[90vh] overflow-y-scroll border mt-3 m-auto">
          mainContainer
        </div>

        {/* search & trend container */}
        <div className="lg:w-[40vw] h-[90vh] mt-3 m-auto overflow-y-scroll flex flex-col justify-between items-center">
          <div className="flex items-center justify-around rounded-3xl overflow-hidden bg-slate-500 lg:w-[300px] lg:h-[50px]">
            <IoSearchOutline className="w-5 h-5 text-white ml-3" />
            <input
              type="text"
              className="block text-lg outline-none bg-slate-500 text-white mr-3 lg:w-[220px]"
              placeholder="search"
            />
          </div>
          <div className="bg-slate-900 lg:w-[300px] m-auto mt-5 rounded-3xl overflow-hidden">
            <div className="text-white text-center text-[23px] font-bold border-b-2 border-dashed">
              TREND
            </div>
            <div className="lg:h-[300px]"></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MainPage;
