import React, { useState, useEffect } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import SearchBar from "../../components/SearchBar";
import TrendSection from "../../components/TrendSection";

const MainPage: React.FC = (): JSX.Element => {
  const [items, setItems] = useState([]); // 데이터 항목 배열
  const [hasMore, setHasMore] = useState(true); // 더 가져올 데이터 여부
  const [page, setPage] = useState(1); // 현재 페이지

  // const fetchData = async () => {
  //   try {
  //     const response = await fetch(`https://api.example.com/data?page=${page}`);
  //     const newData = await response.json();

  //     // 데이터를 업데이트하고 페이지 수 증가
  //     setItems((prevItems) => [...prevItems, ...newData]);

  //     // 페이지 수 증가
  //     setPage((prevPage) => prevPage + 1);

  //     // 더 이상 데이터가 없으면 hasMore를 false로 설정
  //     if (newData.length === 0) {
  //       setHasMore(false);
  //     }
  //   } catch (error) {
  //     console.error("Error fetching data:", error);
  //   }
  // };

  // useEffect(() => {
  //   fetchData(); // 초기 데이터 로드
  // }, []);

  return (
    <div className="overflow-y-scroll h-[100vh]">
      {/* 높이를 설정하여 레이아웃 유지 */}
      <div className="w-full h-full border mx-auto">
        {/* InfiniteScroll 사용 부분 주석 처리됨 */}
        {/* <InfiniteScroll
          dataLength={items.length}
          next={fetchData}
          hasMore={hasMore}
          loader={<h4>Loading...</h4>}
          scrollableTarget="scrollableDiv"
        >
          {items.map((item, index) => (
            <article key={index}>{item.name}</article>
          ))}
        </InfiniteScroll> */}
        main-container
      </div>
    </div>
  );
};

export default MainPage;
