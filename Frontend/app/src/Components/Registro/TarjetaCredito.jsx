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
        <div className="credit-card-form" >
            <div className='credit-card-card'>
                <div className='tarjetas'>
                    <div className="credit-card-front" >
                        <div className="credit-card">
                            <div className='tipo-tarjeta'>
                                <div className='rccs__chip'></div>
                                <div className='rccs__card--visa'></div>
                            </div>
                            <div className="rccs__number">{cardInfo.number.padEnd(16, '•')}</div>
                            <div className='parte-abajo'>
                                <div className="rccs__name">{cardInfo.name || 'YOUR NAME HERE'}</div>
                                <div className='rccs__exp' >
                                    <p>valid true</p>
                                    <div className="rccs__expiry__valid">{cardInfo.expiry}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="credit-card-back">
                        <div class="rccs__cardback">
                            <div class="rccs__card__background">
                                <div class="rccs__issuer"></div>
                                <div class="rccs__stripe"></div>
                                <div class="rccs__signature">
                                    <div class="rccs__cvc-text">CVC</div>
                                    <div className="rccs__cvc">{cardInfo.cvc}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className='formulario'>
                    <form>
                        <input type="text" name="number" placeholder="Card Number" value={cardInfo.number} onChange={handleChange} />
                        <input type="text" name="name" placeholder="Name" value={cardInfo.name} onChange={handleChange} />
                        <input type="text" name="expiry" placeholder="Valid Thru" value={cardInfo.expiry} onChange={handleChange} />
                        <input type="text" name="cvc" placeholder="CVC" value={cardInfo.cvc} onChange={handleChange} />
                    </form>
                </div>
            </div>
        </div>

    );
};

export default TarjetaCredito;
