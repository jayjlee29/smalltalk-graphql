import React, { useEffect, useLayoutEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";
import "./index.css";

// icons
import { FaDeleteLeft } from "react-icons/fa6";
import { IoEyeSharp } from "react-icons/io5"; // 눈 O
import { BsEyeSlashFill } from "react-icons/bs"; // 눈 X

const SigninPage = () => {
  // input values
  const [idValue, setIdValue] = useState<string>("");
  const [pwdView, setPwdView] = useState<boolean>(false);
  const [pwdValue, setPwdValue] = useState<string>("");

  const idInputRef = useRef<HTMLInputElement>(null);
  const pwdInputRef = useRef<HTMLInputElement>(null);

  // 지우기 버튼 => 아이콘 사용(해당 input의 value만 삭제)
  const handleInputClear = (
    setter: React.Dispatch<React.SetStateAction<string>>,
    inputRef: React.RefObject<HTMLInputElement>
  ) => {
    setter("");
    if (inputRef.current) {
      inputRef.current.focus();
    }
  };

  // 비밀번호 보기
  const togglePwdView = () => {
    setPwdView((prev) => !prev);
  };

  // 커서 위치 설정
  useLayoutEffect(() => {
    if (pwdInputRef.current) {
      pwdInputRef.current.focus();
      const length = pwdInputRef.current.value.length;
      pwdInputRef.current.setSelectionRange(length, length);
    }
  }, [pwdView]);

  return (
    <div>
      <div className="m-auto mt-16 w-[370px] lg:w-[650px] md:w-[500px] border border-slate-400 bg-slate-200 rounded-md">
        <form className="m-auto w-[230px] lg:w-[600px] md:w-[450px] sm:w-[340px] h-[550px]">
          <div className="w-52 m-auto text-[22px] mt-8 mb-8">
            Welcome Small Talk
          </div>
          <div className="m-auto mb-4 h-[300px] flex flex-col justify-center">
            <div className="m-auto mt-1 mb-0 w-[300px] md:w-[450px] h-[50px] relative border border-slate-100 bg-white">
              <input
                ref={idInputRef}
                aria-label="ID"
                name="id"
                maxLength={41}
                onChange={(e) => setIdValue(e.target.value)}
                value={idValue}
                className="absolute top-2 left-[68px] m-auto mt-1 mb-2 w-[200px] md:w-[300px] outline-none text-[17px] z-[5]"
              />
              <label
                htmlFor="id"
                className="absolute bottom-5 left-1 text-gray-500"
              >
                아이디
              </label>
              {idValue.length > 0 && (
                <FaDeleteLeft
                  onClick={() => handleInputClear(setIdValue, idInputRef)}
                  className="absolute top-2 right-2 z-10"
                />
              )}
            </div>
            <div className="m-auto mt-0 mb-3 w-[300px] md:w-[450px] h-[50px] relative border border-slate-100 bg-white">
              <input
                ref={pwdInputRef}
                type={pwdView ? "text" : "password"}
                aria-label="Password"
                name="pwd"
                maxLength={21}
                onChange={(e) => setPwdValue(e.target.value)}
                value={pwdValue}
                className="absolute top-2 left-[68px] m-auto mt-1 mb-2 w-[200px] md:w-[300px] outline-none text-[17px] z-[5]"
              />
              <label
                htmlFor="pwd"
                className="absolute bottom-5 left-1 text-gray-500"
              >
                비밀번호
              </label>
              {pwdValue.length > 0 && (
                <FaDeleteLeft
                  onClick={() => handleInputClear(setPwdValue, pwdInputRef)}
                  className="absolute top-2 right-2 z-10"
                />
              )}
              {pwdValue.length > 0 &&
                (pwdView ? (
                  <IoEyeSharp
                    onClick={togglePwdView}
                    className="absolute top-2 right-8 z-10"
                  />
                ) : (
                  <BsEyeSlashFill
                    onClick={togglePwdView}
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
