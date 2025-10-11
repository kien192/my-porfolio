import React from 'react'


const Header = () => {
  return (
   <nav className="relative bg-gray-800 rounded-full w-1/2 mx-auto flex justify-center">
  <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
    <div className="relative flex h-16 items-center justify-between">
      <div className="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
        <div className="flex shrink-0 items-center">
          <img src="https://tailwindcss.com/plus-assets/img/logos/mark.svg?color=indigo&shade=500" alt="Your Company" className="h-8 w-auto" />
        </div>
        <div className="sm:ml-6 sm:block">
          <div className="flex space-x-4">
            <a href="#" aria-current="page" className="rounded-md bg-gray-900 px-3 py-2 text-sm font-medium text-white">Giới thiệu</a>
            <a href="#" className="rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-white/5 hover:text-white">Team</a>
            <a href="#" className="rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-white/5 hover:text-white">Dự án cá nhân</a>
            <a href="#" className="rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-white/5 hover:text-white">Sự nghiệp</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</nav>
  )
}

export default Header





