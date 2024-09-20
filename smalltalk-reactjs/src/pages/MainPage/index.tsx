import React from "react";
import { IoSearchOutline } from "react-icons/io5";

const MainPage = () => {
  /*
    FIXME: 반응형 CSS
    * signin할 경우 본인 아이콘 대체
    1. 휴대폰 기준: nav 상단 위치(click 시 화면에 nav 보여짐)
    2. 태블릿 기준: nav 좌측 위치
    3. 데스크탑 기준 : nav 좌측 위치, main-container / search bar 위치 고려
  */
  return (
    <div>
      <div className="flex items-center">
        {/* main container */}
        <div className="lg:w-[60vw] border">mainContainer</div>

        {/* search & trend container */}
        <div className="mt-1 lg:w-[40vw] flex flex-col justify-between items-center">
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
