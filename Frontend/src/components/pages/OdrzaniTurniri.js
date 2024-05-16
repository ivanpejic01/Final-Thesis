import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import React, {useEffect, useState} from "react";


const OdrzaniTurniri = () => {
    const [turniri, setTurniri] = useState([]);
    const [prvoMjesto, setPrvoMjesto] = useState([]);

    useEffect(() => {dohvatiTurnire()}, []);
    useEffect(() => {dohvatiPlasmane()}, [turniri]);

    const dohvatiTurnire = () => {
        fetch('http://localhost:8080/api/odrzani')
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

        if (prvoMjesto.find(mjesto => mjesto.id === id) === undefined) {
            console.log("Objekt je undefined")
            return null;
        }
        else if (prvoMjesto.find(mjesto => mjesto.id === id).element.id === null) {
            console.log("Kljuc je null, nema pobjednika")
            return null;
        }
        console.log("Vraćam " + prvoMjesto.find(mjesto => mjesto.id === id))
        return prvoMjesto.find(mjesto => mjesto.id === id);
    };

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
                                            {(turnir.organizator !== null) && ` ${turnir.organizator.osoba.ime} ${turnir.organizator.osoba.prezime}`}
                                        </div>
                                        {(nadiPrvoMjesto(turnir.id) !== null) && (<div>
                                            Pobjednik
                                            <div>
                                                {nadiPrvoMjesto(turnir.id).element.osoba.ime}{' '}
                                                {nadiPrvoMjesto(turnir.id).element.osoba.prezime} -{' '}
                                                {nadiPrvoMjesto(turnir.id).element.nadimak}
                                            </div>
                                        </div>)}
                                        {/*<div>Pobjednik:
                                            {nadiPrvoMjesto(turnir.id) ? (
                                                <div>
                                                    {nadiPrvoMjesto(turnir.id).element.osoba.ime}{' '}
                                                    {nadiPrvoMjesto(turnir.id).element.osoba.prezime} -{' '}
                                                    {nadiPrvoMjesto(turnir.id).element.nadimak}
                                                </div>
                                            ) : (
                                                <div>Pobjednik not found</div>
                                            )}
                                        </div>*/}
                                    </Card.Text>
                                </Card.Body>
                            </Card>

                        ))
                    }

                </div>
            </div>
        </div>

    );

}

export default OdrzaniTurniri;