import React from "react";
import { Link } from "react-router-dom";

interface UserProfilePopupProps {
  userName: string;
  onLogout: () => void;
}

const UserProfilePopup: React.FC<UserProfilePopupProps> = ({
  userName,
  onLogout
}: UserProfilePopupProps): JSX.Element => {
  return (
    <div className="absolute left-0 top-8 mt-2 w-40 bg-white border rounded shadow-lg">
      <div className="p-2 hover:bg-gray-200">
        {/* 프로필 페이지로 이동 임시 */}
        <Link
          to="#"
          // to="/profile"
          className="block w-full h-full font-bold"
        >
          {userName}
        </Link>
      </div>
      <div className="border-t">
        <button
          onClick={onLogout}
          className="w-full text-left p-2 hover:bg-gray-200"
        >
          로그아웃
        </button>
      </div>
    </div>
  );
};

export default UserProfilePopup;
