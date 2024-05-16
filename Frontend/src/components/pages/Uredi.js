import Card from "react-bootstrap/Card";
import React, {useEffect, useState} from "react";
import Button from "react-bootstrap/Button";
import {Link} from "react-router-dom";


const Uredi = () => {

    const [turniri, setTurniri] = useState([]);
    const [prvoMjesto, setPrvoMjesto] = useState([]);


    useEffect(() => {dohvatiTurnire()}, []);
    useEffect(() => {dohvatiPlasmane()}, [turniri]);

    const dohvatiTurnire = () => {
        fetch('http://localhost:8080/api/sviTurniri')
            .then(response => response.json())
            .then(data => {
                setTurniri(data);
                console.log('Turniri = ' + data)
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    const dohvatiPlasmane = () => {
        turniri.map(turnir => (fetch('http://localhost:8080/api/odrzani/' + turnir.id)
            .then(response => {
                if (!response.ok) {
                    console.log("NIJE DOBAR ODGOVOR")
                    throw new Error("Error: " + response.status);
                }
                return response.json();}
            ).then(data => {
                const noviElement = {id: turnir.id, element: data}
                console.log("Turnir id " + turnir.id);
                setPrvoMjesto(staraLista => [...staraLista, noviElement]);
            }))

        )
    }

    const nadiPrvoMjesto = (id) => {
        console.log("ID = " + id)
        console.log("Vraćam " + prvoMjesto.find(mjesto => mjesto.id === id))
        if (prvoMjesto.find(mjesto => mjesto.id === id) === undefined) {
            console.log("Objekt je undefined")
            return null;
        }
        else if (prvoMjesto.find(mjesto => mjesto.id === id).element.id === null) {
            console.log("Kljuc je null, nema pobjednika")
            return null;
        }
        return prvoMjesto.find(mjesto => mjesto.id === id);
    };


    const obrisiTurnir = (id) => {
        const confirmed = window.confirm("Sigurno želite obrisati ovaj turnir?");
        if (confirmed) {
            fetch('http://localhost:8080/api/obrisi/' + id, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(response => {
                if (response.ok) {
                    console.log('Obrisan')
                } else {
                    console.log('Nije obrisan')
                }
            }).then(data => {
                dohvatiTurnire();
            })
        }

    }




    return (
        <div>
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
                                        <div>Broj natjecatelja: {turnir.igraci.length}/{turnir.brojIgraca}</div>
                                        <div>Mjesto održavanja: {turnir.objekt.nazivObjekt}</div>
                                        <div>Vrsta turnira: {turnir.vrstaTurnira.nazivVrsta}</div>
                                        <div>Organizator:
                                            {(turnir.organizator !== null) && `${turnir.organizator.osoba.ime} ${turnir.organizator.osoba.prezime}`
                                            }
                                        </div>
                                    </Card.Text>
                                    <Link to={"/uredi/" + turnir.id}>
                                        <Button style={{marginRight: '0.5rem'}} variant="outline-warning">Uredi</Button>
                                    </Link>
                                    <Button style={{marginRight: '0.5rem'}} variant="outline-danger"  onClick={() => obrisiTurnir(turnir.id)}>Obriši</Button>
                                    {(new Date() >= new Date(turnir.datum)) && nadiPrvoMjesto(turnir.id) === null &&  ((
                                        <Link to={"/pobjednik/" + turnir.id}>
                                            <Button variant="outline-success">Postavi pobjednika</Button>
                                        </Link>))}
                                </Card.Body>
                            </Card>

                        ))
                    }

                </div>
            </div>
        </div>

    );
}
export default Uredi;