import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { IoPersonCircle } from "react-icons/io5";
import { useRecoilState } from "recoil";
import { userState } from "../stores/userState";
import { useQuery } from "@apollo/client";
import { GET_PROFILE } from "../graphql/queries";
import UserProfilePopup from "./UserProfilePopup";

const Nav: React.FC = () => {
  const navigate = useNavigate();
  const [userId, setUserId] = useRecoilState(userState);
  const [showPopup, setShowPopup] = useState(false);

  // const { loading, error, data } = useQuery(GET_PROFILE, {
  //   variables: { userId },
  //   skip: !userId
  // });

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

  // if (error) {
  //   console.error("Error fetching profile:", error);
  //   return <div>사용자 정보를 불러올 수 없습니다.</div>; // 에러 처리
  // }

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

      <div className="m-1 relative">
        {userId && (
          <>
            <button
              aria-label="User Profile"
              className="size-10 leading-9 text-center border-2 rounded-full text-white hover:bg-slate-700 transition duration-300 flex items-center justify-center"
              onClick={() => setShowPopup(!showPopup)} // 팝업 토글
            >
              <IoPersonCircle className="text-white w-8 h-8" />
            </button>
            {showPopup && (
              <UserProfilePopup
                // userName={data.getProfile.data.userName}
                userName="test"
                onLogout={handleLogout}
              />
            )}
          </>
        )}
      </div>
    </div>
  );
};

export default Nav;
