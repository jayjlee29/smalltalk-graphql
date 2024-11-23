import React from "react";

const Fallback = () => {
  return (
    <div className="flex items-center justify-center w-full h-full bg-black">
      <div className="relative w-12 h-12 border-2 border-light bg-transparent rounded-full animate-spin">
        <div className="absolute left-0 top-0 bg-brand w-1.5 h-1.5 rounded-full transform translate-x-1.5 translate-y-1.5"></div>
        <div className="absolute right-0 bottom-0 bg-brand w-1.5 h-1.5 rounded-full transform -translate-x-1.5 -translate-y-1.5"></div>
      </div>
    </div>
  );
};

export default Fallback;
