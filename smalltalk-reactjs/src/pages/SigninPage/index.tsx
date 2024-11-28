import React, { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useForm, SubmitHandler } from "react-hook-form";
import "./index.css";
import { FaDeleteLeft } from "react-icons/fa6";
import { FormValues } from "../../interface";

const SigninPage: React.FC = (): JSX.Element => {
  const navigate = useNavigate();

  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue
  } = useForm<FormValues>();

  useEffect(() => {
    const S_TOKEN = localStorage.getItem("S_TOKEN");
    if (S_TOKEN) {
      navigate("/main");
    }
  }, [navigate]);

  const onSubmit: SubmitHandler<FormValues> = (data) => {
    const encodedIdentifier = btoa(data.identifier);

    const userName = `${data.lastName} ${data.firstName}`.trim();

    const formDataToSend = {
      ...data,
      userName
    };

    localStorage.setItem("S_TOKEN", encodedIdentifier);

    // 서버에 formDataToSend를 보내는 로직 추가 필요
    console.log(formDataToSend);

    navigate("/main");
  };

  /*
    * temp submit function
    const onSubmit: SubmitHandler<FormValues> = async (data) => {
    const encodedIdentifier = btoa(data.identifier);
    const userName = `${data.lastName} ${data.firstName}`.trim();

    const formDataToSend = {
        identifier: data.identifier,
        userName: userName,
    };

    localStorage.setItem("S_TOKEN", encodedIdentifier);

    try {
        * 서버에 formDataToSend를 POST 요청으로 전송 대략
        const response = await axiosInstance.post('/api/login', formDataToSend);
        
        if (response.data.success) {
            navigate("/main"); // 성공적으로 로그인하면 메인 페이지로 이동
        } else {
            console.error("Login failed:", response.data.message);
        }
    } catch (error) {
        console.error("Error during login:", error);
    }
  };
  */

  return (
    <div className="m-auto mt-32 w-[370px] lg:w-[650px] md:w-[500px] border border-slate-400 bg-slate-200 rounded-md">
      <form
        onSubmit={handleSubmit(onSubmit)}
        className="m-auto w-[230px] lg:w-[600px] md:w-[450px] sm:w-[340px] h-[400px] p-3"
      >
        <div className="w-52 m-auto text-[22px] mt-8 mb-8">
          Welcome Small Talk
        </div>
        <div>
          <div className="relative m-auto mb-4 border border-slate-100 bg-white w-[300px] md:w-[400px]">
            <input
              {...register("identifier", {
                required: "아이디 또는 닉네임을 입력해주세요.",
                pattern: {
                  value: /^[가-힣a-zA-Z0-9\-@_]+$/,
                  message:
                    "유효하지 않은 형식입니다. 한글, 영대소문자, 특수문자(-, @, _)만 사용 가능합니다."
                }
              })}
              type="text"
              aria-label="아이디 또는 닉네임"
              placeholder="아이디 또는 닉네임을 입력하세요."
              className="ml-[6px] h-[50px] border-none w-[280px] md:w-[380px] outline-none text-[17px] z-[5]"
            />
            {errors.identifier && (
              <p className="text-red-500">{errors.identifier.message}</p>
            )}
            <FaDeleteLeft
              onClick={() => setValue("identifier", "")}
              className="absolute top-2 right-2 z-10 cursor-pointer"
            />
          </div>

          <div className="flex m-auto justify-between items-center w-[300px] md:w-[400px] gap-4">
            {/* 성 입력 필드 */}
            <div className="relative mb-4 border border-slate-100 bg-white w-[150] md:w-[200]">
              <input
                {...register("lastName", {
                  required: "성을 입력해주세요.",
                  pattern: {
                    value: /^[가-힣a-zA-Z]+$/,
                    message:
                      "유효하지 않은 형식입니다. 한글 또는 영문만 사용 가능합니다."
                  }
                })}
                type="text"
                aria-label="성"
                placeholder="성"
                className="ml-[6px] h-[50px] border-none w-[120px] md:w-[170px] outline-none text-[17px] z-[5]"
              />
              {errors.lastName && (
                <p className="text-red-500">{errors.lastName.message}</p>
              )}
              <FaDeleteLeft
                onClick={() => setValue("lastName", "")}
                className="absolute top-2 right-2 z-10 cursor-pointer"
              />
            </div>

            {/* 이름 입력 필드 */}
            <div className="relative mb-4 border border-slate-100 bg-white w-[150] md:w-[200]">
              <input
                {...register("firstName", {
                  required: "이름을 입력해주세요.",
                  pattern: {
                    value: /^[가-힣a-zA-Z]+$/,
                    message:
                      "유효하지 않은 형식입니다. 한글 또는 영문만 사용 가능합니다."
                  }
                })}
                type="text"
                aria-label="이름"
                placeholder="이름"
                className="ml-[6px] h-[50px] border-none w-[120px] md:w-[170px] outline-none text-[17px] z-[5]"
              />
              {errors.firstName && (
                <p className="text-red-500">{errors.firstName.message}</p>
              )}
              <FaDeleteLeft
                onClick={() => setValue("firstName", "")}
                className="absolute top-2 right-2 z-10 cursor-pointer"
              />
            </div>
          </div>

          <div className="text-center">
            <button
              type="submit"
              className="w-[300px] md:w-[400px] h-[50px] bg-blue-500 text-white py-2 rounded"
            >
              입장
            </button>
          </div>
        </div>

        <div className="m-auto flex justify-center items-center mt-4">
          <Link to="#" className="pr-3 pl-3 m-1 text-slate-500 hover:underline">
            닉네임 찾기
          </Link>
        </div>
      </form>
    </div>
  );
};

export default SigninPage;
