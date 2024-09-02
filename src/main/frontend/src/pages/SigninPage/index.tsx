import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./index.css";

// icons
import { FaDeleteLeft } from "react-icons/fa6";
import { IoEyeSharp } from "react-icons/io5"; // 눈 O
import { BsEyeSlashFill } from "react-icons/bs"; // 눈 X

/*
  TODO: 
  
  - label 태그 ui는 naver login 참고해서 변경(input 태그 focus일 때 위치 조정)
  - 소셜 로그인(네이버, 구글)
*/

const SigninPage = () => {
  // pwd input
  const [pwdView, setPwdView] = useState<Boolean>(false);
  const [pwdValue, setPwdValue] = useState<string>("");

  // 지우기 버튼 => 아이콘 사용(해당 input의 value만 삭제)
  const handleInputVal = (inputId: string, event: React.MouseEvent) => {
    event.stopPropagation();

    const inputTag: HTMLInputElement = document.getElementById(
      inputId
    ) as HTMLInputElement;

    if (inputTag) {
      inputTag.value = "";
      inputTag.focus(); // focus 유지
    }
  };

  const handleFocus = (focusTagId: string, event: React.MouseEvent): void => {
    event.stopPropagation();

    const inputs = ["input_id", "input_pwd"];
    inputs.forEach((id) => {
      const el = document.getElementById(id);
      if (el) el.classList.remove("focus");
    });

    const focusEl = document.getElementById(focusTagId);
    if (focusEl) focusEl.classList.add("focus");
  };

  // 비밀번호 보기
  const handlePwdView = (pwdElId: string, event: React.MouseEvent) => {
    event.stopPropagation();

    const pwdEl: HTMLInputElement = document.getElementById(
      pwdElId
    ) as HTMLInputElement;

    if (pwdEl) {
      pwdEl.type = pwdEl.type === "password" ? "text" : "password";
      setPwdView(!pwdView);
      pwdEl.focus(); // focus 유지
    }
  };

  // 전체 input tag focus 삭제용
  const handlePageClick = (): void => {
    const inputs = ["input_id", "input_pwd"];
    inputs.forEach((id) => {
      const el = document.getElementById(id);
      if (el) el.classList.remove("focus");
    });
  };

  return (
    <div onClick={handlePageClick}>
      <div className="m-auto mt-16 lg:w-[650px] md:w-[500px] sm:w-[370px] border border-slate-400 bg-slate-200 rounded-md">
        <form className="m-auto lg:w-[600px] md:w-[450px] sm:w-[340px] h-[550px]">
          <div className="w-52 m-auto text-[22px] mt-8 mb-8">
            Welcome Small Talk
          </div>
          <div className="m-auto mb-4 h-[300px] flex flex-col justify-center">
            <div
              id="input_id"
              className="m-auto mt-1 mb-0 w-[450px] h-[50px] relative border border-slate-100 bg-white"
            >
              <input
                id="id"
                aria-label="ID"
                name="id"
                maxLength={41}
                onClick={(e) => handleFocus("input_id", e)}
                className="absolute top-2 left-[68px] m-auto mt-1 mb-2 w-[300px] outline-none text-[17px] z-[5]"
              />
              <label
                htmlFor="id"
                className="absolute bottom-5 left-1 text-gray-500"
              >
                아이디
              </label>
              <FaDeleteLeft
                onClick={(e: React.MouseEvent) => handleInputVal("id", e)}
                className="absolute top-2 right-2 z-10"
              />
            </div>
            <div
              id="input_pwd"
              className="m-auto mt-0 mb-3 w-[450px] h-[50px] relative border border-slate-100 bg-white"
            >
              <input
                type={pwdView ? "text" : "password"}
                id="pwd"
                aria-label="Password"
                name="pwd"
                maxLength={21}
                onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                  setPwdValue(e.target.value)
                }
                onClick={(e: React.MouseEvent) => handleFocus("input_pwd", e)}
                className="absolute top-2 left-[68px] m-auto mt-1 mb-2 w-[300px] outline-none text-[17px] z-[5]"
              />
              <label
                htmlFor="pwd"
                className="absolute bottom-5 left-1 text-gray-500"
              >
                비밀번호
              </label>
              <FaDeleteLeft
                onClick={(e: React.MouseEvent) => handleInputVal("pwd", e)}
                className="absolute top-2 right-2 z-10"
              />
              {pwdValue.length > 0 &&
                (pwdView ? (
                  <IoEyeSharp
                    onClick={(e: React.MouseEvent) => handlePwdView("pwd", e)}
                    className="absolute top-2 right-8 z-10"
                  />
                ) : (
                  <BsEyeSlashFill
                    onClick={(e: React.MouseEvent) => handlePwdView("pwd", e)}
                    className="absolute top-2 right-8 z-10"
                  />
                ))}
            </div>
          </div>

          <div className="m-auto flex justify-center items-center">
            <Link
              to="#"
              className="pr-3 pl-3 m-1 border-r border-gray-300 hover:underline"
            >
              아이디 찾기
            </Link>
            <Link
              to="#"
              className="pr-3 pl-3 m-1 border-r border-gray-300 hover:underline"
            >
              비밀번호 찾기
            </Link>
            <Link to="#" className="pr-3 pl-3 m-1 hover:underline">
              회원가입
            </Link>
          </div>
        </form>
      </div>
    </div>
  );
};

export default SigninPage;
