import React, { useState } from 'react';
import './CreditCardForm.css';

const TarjetaCredito = () => {
    const [cardInfo, setCardInfo] = useState({
        number: '',
        name: '',
        expiry: '',
        cvc: ''
    });

    const handleChange = (event) => {
        const { name, value } = event.target;
        setCardInfo(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    return (
        <div className="credit-card-form">
            <div className="credit-card-display">
                <div className="credit-card">
                    <div className="credit-card-number">{cardInfo.number.padEnd(16, '•')}</div>
                    <div className="credit-card-name">{cardInfo.name || 'YOUR NAME HERE'}</div>
                    <div className="credit-card-expiry">{cardInfo.expiry}</div>
                </div>
            </div>
            <form>
                <input type="text" name="number" placeholder="Card Number" value={cardInfo.number} onChange={handleChange} />
                <input type="text" name="name" placeholder="Name" value={cardInfo.name} onChange={handleChange} />
                <input type="text" name="expiry" placeholder="Valid Thru" value={cardInfo.expiry} onChange={handleChange} />
                <input type="text" name="cvc" placeholder="CVC" value={cardInfo.cvc} onChange={handleChange} />
            </form>
        </div>
    );
};

export default TarjetaCredito;
