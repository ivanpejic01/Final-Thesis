import React, {useState, useEffect} from "react";
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import {useNavigate} from "react-router-dom";
import '../styles/turniri.css';


const Turniri = () => {

    const [turniri, setTurniri] = useState([]);
    const [prekrivena, setPrekrivena] = useState(false);
    const [saljiZahtjeve, setSaljiZahtjeve] = useState(false);
    const [klikMisem, setKlikMisem] = useState(false);
    const [igraci, setIgraci] = useState([])
    useEffect(() => {dohvatiTurnire()}, []);

    const dohvatiTurnire = () => {
        fetch('http://localhost:8080/api/turniri')
            .then(response => response.json())
            .then(data => {

                const azuriraniTurniri = data.map(turnir => ({
                    ...turnir,
                    pun: turnir.igraci.length === turnir.brojIgraca
                }));
                setTurniri(azuriraniTurniri);
                console.log('Turniri = ' + data)
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }





    const navigate = useNavigate();
    return (
        <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
            <div className="centered-flex"  style={{alignItems: 'center', justifyContent: 'center'}}>
                {
                    turniri.map(turnir => (
                        <Card style={{ width: '30rem', margin: '1rem' }}>
                            <Card.Body>
                                <Card.Title>{turnir.naziv}</Card.Title>
                                <Card.Text>
                                   <div>Datum: {turnir.datum}</div>
                                    <div>Vrijeme: {turnir.vrijeme}</div>
                                    <div>
                                        Broj natjecatelja: {turnir.igraci.length}/{turnir.brojIgraca}
                                    </div>
                                    <div>Mjesto održavanja: {turnir.objekt.nazivObjekt}</div>
                                    <div>Vrsta turnira: {turnir.vrstaTurnira.nazivVrsta}</div>
                                    <div>Organizator: {(turnir.organizator !== null) && `${turnir.organizator.osoba.ime} ${turnir.organizator.osoba.prezime}`}
                                    </div>
                                </Card.Text>

                                {!turnir.pun &&
                                    <>
                                    <Button variant="primary" onClick={() => navigate("/request/"+turnir.id)}>
                                        Novi igrač</Button>
                                <Button variant="primary" style={{marginLeft: '1rem'}} onClick={() => navigate("/postojeci/"+turnir.id)}>
                                    Postojeći igrač</Button>
                                    </>}
                                {turnir.pun &&
                                <div style={{color: 'red', marginTop: '-0.5rem'}}>Prijava je onemogućena jer je turnir dosegnuo maksimalni kapacitet!</div>}
                            </Card.Body>
                        </Card>

                    ))
                }

            </div>
        </div>
    );
};

export default Turniri;