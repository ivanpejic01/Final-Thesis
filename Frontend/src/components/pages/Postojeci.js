import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";


const Postojeci = () => {

    const {id} = useParams();
    const [turnir, setTurnir] = useState({});
    const [formErrors, setFormErrors] = useState({});
    const [errorPoruka, setErrorPoruka] = useState("");
    const [forma, setForma] = useState({
        nadimak: "",
        oib: ""
    });

    const navigate = useNavigate();

    useEffect(() => {dohvatiTurnir()}, []);

    const dohvatiTurnir = () => {
        fetch('http://localhost:8080/api/turniri/' + id)
            .then(response => response.json())
            .then(data => {
                setTurnir(data);
                console.log("Id turnira " + id);
                console.log('Turnir = ' + data)
            })
            .catch(error => {
                console.error('Error:', error);
            });

    }

    const handleSubmit = (event) => {
        if (validation()) {
            event.preventDefault();
            fetch('http://localhost:8080/api/postojeci/' + id, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(forma)
            }).then(response => {
                if (!response.ok) {
                    throw new Error(response.statusText);
                }
                return response.json();
            })
                .then(data => {
                    console.log(data);
                    navigate('/turniri');
                }).catch(error => {
                setErrorPoruka("Igrač ili ne postoji u bazi ili su podaci pogrešno uneseni.");
            })
        }

        else {
            event.preventDefault();
        }
    }

    const handleChange = (event) => {
        const { name, value } = event.target;
        setForma((formaPrije) => ({
            ...formaPrije,
            [name]: value,
        }));
    };

    const validation = () => {
        const errors = {};

        if (!/^[a-zA-ZčČćĆšŠđĐžŽ]{1,20}$/.test(forma.nadimak)) {
            errors.nadimak = "Nadimak je obavezno polje sadrži najviše 20 slova."
        }

        if (!/^[0-9]{11}$/.test(forma.oib)) {
            errors.oib = "OIB je obavezno polje i mora biti 11-znamenkasti broj."
        }

        setFormErrors(errors);
        return Object.keys(errors).length === 0;
    }


    return(
        <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
            <Form onSubmit={handleSubmit}>

                <div style={{fontSize: "1.5em"}}>Prijavi se na turnir <span style={{fontWeight: "bold"}}>{turnir.naziv}</span></div>
                <Form.Group className="mb-3" controlId="formBasicNickname">
                    <Form.Label>Nadimak: </Form.Label>
                    <Form.Control name="nadimak" onChange={handleChange} value={forma.nadimak} type="text" placeholder="Nadimak igrača" required isInvalid={!!formErrors.nadimak}></Form.Control>
                    <Form.Control.Feedback type="invalid">{formErrors.nadimak}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicFirstOIB">
                    <Form.Label>OIB: </Form.Label>
                    <Form.Control name="oib" onChange={handleChange} value={forma.oib} type="text" placeholder="OIB igrača" isInvalid={!!formErrors.oib} required></Form.Control>
                    <Form.Control.Feedback type="invalid">{formErrors.oib}</Form.Control.Feedback>
                </Form.Group>
                <div style={{color: "red", marginTop:'1rem', marginBottom: '1rem'}}>{errorPoruka}</div>
                <Button variant="primary" type="submit" style={{marginRight: '1em'}}>
                    Prijava
                </Button>
                <Button variant="secondary" onClick={() => navigate("/turniri")}>Odustani</Button>
            </Form>
        </div>
    )
}

export default Postojeci