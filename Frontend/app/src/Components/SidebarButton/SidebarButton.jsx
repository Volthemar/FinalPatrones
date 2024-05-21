import React from 'react';
import './SidebarButton.css'
import { Link } from 'react-router-dom';
function SidebarButton({ URL_BTN, URL_IMG, nombre }) {

  const handleClick = () => {
    window.location.href = "http://localhost:3241/" + URL_BTN;
  };

  return (
    <>
      <Link to={URL_BTN}>
        <div className="sidebarButton" >
          <img src={URL_IMG} alt="" />
          <p>{nombre}</p>
        </div>
      </Link>

    </>

  );
}

export default SidebarButton;