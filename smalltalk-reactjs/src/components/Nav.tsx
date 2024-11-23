import React, { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { IoPersonCircle } from "react-icons/io5";
import { useRecoilState } from "recoil";
import { userState } from "../stores/userState";

const Nav: React.FC = () => {
  const [userId, setUserId] = useRecoilState(userState); // Recoil 상태 사용
  const navigate = useNavigate();

  useEffect(() => {
    const S_TOKEN: string | null = localStorage.getItem("S_TOKEN");

    if (!S_TOKEN) {
      navigate("/");
      return;
    }

    try {
      const decodedId: string = atob(S_TOKEN);

      if (!decodedId) {
        throw new Error("Decoded ID is empty");
      }

      setUserId(decodedId);
    } catch (error: unknown) {
      console.error("Decoding error:", error);
      navigate("/");
    }
  }, [navigate, setUserId]);

  return (
    <div className="sticky top-0 flex justify-between items-center bg-slate-400 h-[45px]">
      <div className="m-1">
        <Link
          to="main"
          className="text-white text-[18px] leading-[18px] font-bold"
        >
          SMALL TALK
        </Link>
      </div>

      <div className="m-1">
        {userId && (
          <Link
            to="#"
            aria-label="User Profile"
            className="size-10 leading-9 text-center border-2 rounded-full text-white hover:bg-slate-700 transition duration-300 flex items-center justify-center"
          >
            <IoPersonCircle className="text-white w-8 h-8" />
          </Link>
        )}
      </div>
    </div>
  );
};

export default Nav;
