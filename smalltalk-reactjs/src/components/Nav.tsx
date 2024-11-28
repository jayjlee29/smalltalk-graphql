import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import {
  IoHome,
  IoSearch,
  IoNotifications,
  IoMail,
  IoBookmark,
  IoList,
  IoPersonCircle
} from "react-icons/io5"; // 필요한 아이콘 import
import { useRecoilState } from "recoil";
import { userState } from "../stores/userState";
import UserProfilePopup from "./UserProfilePopup";

const Nav: React.FC = (): JSX.Element => {
  const navigate = useNavigate();
  const [userId, setUserId] = useRecoilState(userState);
  const [showPopup, setShowPopup] = useState(false);

  useEffect(() => {
    const S_TOKEN: string | null = localStorage.getItem("S_TOKEN");

    if (!S_TOKEN) {
      navigate("/");
      return;
    }

    try {
      const decodedId: string = atob(S_TOKEN);
      if (!decodedId) throw new Error("Decoded ID is empty");
      setUserId(decodedId);
    } catch (error: unknown) {
      console.error("Decoding error:", error);
      navigate("/");
    }
  }, [navigate, setUserId]);

  const handleLogout = () => {
    localStorage.removeItem("S_TOKEN");
    setUserId(null);
    navigate("/");
  };

  return (
    <div className="w-48 md:w-36 lg:w-48 flex flex-col justify-between bg-slate-400 h-screen p-3">
      <div className="mb-4">
        <Link
          to="main"
          className="text-white text-lg font-bold hover:text-slate-900 transition duration-200"
        >
          SMALL TALK
        </Link>
      </div>

      <nav className="flex-grow">
        <ul className="flex flex-col space-y-4">
          {/* 세로 정렬 및 간격 설정 */}
          <li className="h-12 flex items-center">
            <Link
              to="/home"
              className="flex items-center text-white hover:text-slate-800 transition duration-200 w-full h-full"
            >
              <IoHome className="mr-2 w-6 h-6" /> 홈
            </Link>
          </li>
          <li className="h-12 flex items-center">
            <Link
              to="/explore"
              className="flex items-center text-white hover:text-slate-800 transition duration-200 w-full h-full"
            >
              <IoSearch className="mr-2 w-6 h-6" /> 탐색
            </Link>
          </li>
          <li className="h-12 flex items-center">
            <Link
              to="/notifications"
              className="flex items-center text-white hover:text-slate-800 transition duration-200 w-full h-full"
            >
              <IoNotifications className="mr-2 w-6 h-6" /> 알림
            </Link>
          </li>
          <li className="h-12 flex items-center">
            <Link
              to="/messages"
              className="flex items-center text-white hover:text-slate-800 transition duration-200 w-full h-full"
            >
              <IoMail className="mr-2 w-6 h-6" /> 메시지
            </Link>
          </li>
          <li className="h-12 flex items-center">
            <Link
              to="/bookmarks"
              className="flex items-center text-white hover:text-slate-800 transition duration-200 w-full h-full"
            >
              <IoBookmark className="mr-2 w-6 h-6" /> 북마크
            </Link>
          </li>
          <li className="h-12 flex items-center">
            <Link
              to="/lists"
              className="flex items-center text-white hover:text-slate-800 transition duration-200 w-full h-full"
            >
              <IoList className="mr-2 w-6 h-6" /> 리스트
            </Link>
          </li>
          {/* 프로필 버튼 */}
          <li className="h-12 flex items-center">
            {userId && (
              <div className="relative">
                <button
                  aria-label="User Profile"
                  className="leading-9 text-white hover:text-slate-800 transition duration-300 flex items-center w-full h-full"
                  onClick={() => setShowPopup(!showPopup)} // 팝업 토글
                >
                  <IoPersonCircle className="mr-2 border rounded-full w-6 h-6" />
                  프로필
                </button>

                {showPopup && (
                  <UserProfilePopup userName="test" onLogout={handleLogout} />
                )}
              </div>
            )}
          </li>
        </ul>
      </nav>
    </div>
  );
};

export default Nav;
