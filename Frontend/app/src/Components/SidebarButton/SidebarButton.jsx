import React from 'react';
import './SidebarButton.css'
import { Link } from 'react-router-dom';
function SidebarButton({ URL_BTN, URL_IMG, nombre }) {

  const handleClick = () => {
    console.log("pipi");
    window.location.href = "http://localhost:5173" + URL_BTN;
  };

  return (
      <div className="sidebarButton" onClick={handleClick}>
        <img src={URL_IMG} alt="" />
          <p>{nombre}</p>
        </div>
  );
}

export default SidebarButton;