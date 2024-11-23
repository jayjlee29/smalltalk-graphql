// src/api/axiosInstance.ts
import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/api" // API의 기본 URL
});

// 요청 인터셉터를 사용하여 Authorization 헤더 추가
axiosInstance.interceptors.request.use((config) => {
  const encodedIdentifier = localStorage.getItem("userIdentifier");
  if (encodedIdentifier) {
    config.headers["Authorization"] = `USER ${encodedIdentifier}`;
  }
  return config;
});

export default axiosInstance;
