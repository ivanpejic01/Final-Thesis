import {useNavigate, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";


const Forma = () => {

    const {id} = useParams();
    const navigate = useNavigate();
    const [objekti, setObjekti] = useState([]);
    const [formErrors, setFormErrors] = useState({});
    const [turnir, setTurnir] = useState({});

    useEffect(() => {dohvatiObjekte()}, []);
    useEffect(() => dohvatiTurnir(id), []);

    const [forma, setForma] = useState({
        vrijemeOdrzavanja: "",
        datumOdrzavanja: "",
        nazivObjekta: ""
    });

    const vrijednostiVremena = ["17:00:00", "18:00:00"];
    const handleChange = (e) => {
        setForma({
            ...forma,
            [e.target.name]: e.target.value
        });
    }

    const dohvatiTurnir = (id) => {
        fetch('http://localhost:8080/api/turniri/' + id)
            .then(response => {
                return response.json()
            }).then(data => {
                setTurnir(data);
        })
    }

    const dohvatiObjekte = () => {
        fetch('http://localhost:8080/api/objekti')
            .then(response => response.json())
            .then(data => {
                setObjekti(data);
            }).catch(error => {
            console.log(error);
        })
    }

    const handleSubmit = (e) => {
        if (validation()){
            e.preventDefault();
        fetch('http://localhost:8080/api/uredi/' + id, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(forma)
        }).then(response => {
            if (response.ok) {
                console.log("Uspješno ažuriran turnir")
                navigate("/uredi");
            } else {
                console.log("Neuspješno ažuriran turnir")
            }
        }).catch(error => {
            console.log(error);
        })
    } else {
            e.preventDefault();
        }

    }

    const validation = () => {
        const errors = {};

        if ((new Date() > new Date(forma.datumOdrzavanja))) {
            errors.datumOdrzavanja = "Datum održavanja turnira ne može biti u prošlosti."
        }

        setFormErrors(errors);
        return Object.keys(errors).length === 0;
    }


    return(
        <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
            <Form onSubmit={handleSubmit} style={{width: '22rem'}}>
                <div style={{fontSize: "1.5em"}}>Uređivanje turnira <span style={{fontWeight: 'bold'}}>{turnir.naziv}</span></div>
                <Form.Group className="mb-3" controlId="formBasicTournamentDate">
                    <Form.Label>Datum održavanja: </Form.Label>
                    <Form.Control value={forma.datumOdrzavanja} name="datumOdrzavanja" onChange={handleChange} type="date" isInvalid={!!formErrors.datumOdrzavanja} required></Form.Control>
                    <Form.Control.Feedback type="invalid">{formErrors.datumOdrzavanja}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicTournamentTime">
                    <Form.Label>Vrijeme: </Form.Label>
                    <Form.Control value={forma.vrijemeOdrzavanja} name="vrijemeOdrzavanja" type="text" onChange={handleChange} as="select" required>
                        <option></option>
                        {vrijednostiVremena.map(vrijeme => (
                            <option>{vrijeme}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicObject">
                    <Form.Label>Objekt: </Form.Label>
                    <Form.Control name="nazivObjekta" value={forma.nazivObjekta} type="text" onChange={handleChange} as="select" required>
                        <option></option>
                        {objekti.map(objekt => (
                            <option>{objekt.nazivObjekt}</option>
                        ))}

                    </Form.Control>
                </Form.Group>
                <Button variant="primary" type="submit" style={{marginRight: '1em'}}>
                    Submit
                </Button>
                <Button variant="secondary" onClick={() => navigate("/uredi")}>Odustani</Button>
            </Form>
        </div>
    )
}

export default Forma;