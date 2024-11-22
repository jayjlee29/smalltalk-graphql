import React from "react";
import { Link } from "react-router-dom";

const Nav = () => {
  return (
    // FIXME: 로그인 한 경우 My / SignOut, 아닌 경우 SignIn / SignUp
    <div className="sticky top-0 flex justify-between items-center bg-slate-400 h-[45px]">
      <div className="m-1">
        <Link
          to="main"
          className="text-white text-[18px] leading-[18px] font-bold"
        >
          SMALL TALK
        </Link>
      </div>
    </div>
  );
};

export default Nav;
