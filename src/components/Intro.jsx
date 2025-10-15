import React from "react";
import avatar from "../assets/avatar.jpg";

const Intro = () => {
  return (
    <div className="intro-container m-8 p-4 border-solid border-2 rounded-md">
      <div className="work-position flex">
        <span className="wp-left flex-1">
          Fullstack Developer (Entry Level)
        </span>

        <div className="wp-right flex-1 justify-end flex">
          <div className="status border-solid border-1 rounded-full flex p-1">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="size-6"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M15.362 5.214A8.252 8.252 0 0 1 12 21 8.25 8.25 0 0 1 6.038 7.047 8.287 8.287 0 0 0 9 9.601a8.983 8.983 0 0 1 3.361-6.867 8.21 8.21 0 0 0 3 2.48Z"
              />
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M12 18a3.75 3.75 0 0 0 .495-7.468 5.99 5.99 0 0 0-1.925 3.547 5.975 5.975 0 0 1-2.133-1.001A3.75 3.75 0 0 0 12 18Z"
              />
            </svg>
            Đang tìm việc
          </div>
        </div>
      </div>

      <div className="profile-container flex justify-between m-2">
        <div className="profile-infor align-middle">
          <h1 className="name text-4xl font-bold font-sans ">
            Nguyễn Trung Kiên
          </h1>
          <span className="brief">
            Sinh viên vừa tốt nghiệp trường đại học Nông Lâm TP.HCM
          </span>
        </div>

        <div className="profile-avatar w-32 h-32 ">
          <img
            src={avatar}
            alt="anh-dai-dien"
            className="aspect-1/1 object-cover rounded-full border-2 border-solid border-white"
          />
        </div>
      </div>
    </div>
  );
};

export default Intro;
