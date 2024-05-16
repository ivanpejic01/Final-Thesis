import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import {useParams} from "react-router-dom";


const Request = () => {
    const [turnir, setTurnir] = useState({});
    const [drzave, setDrzave] = useState([]);
    const [formErrors, setFormErrors] = useState({});
    const [errorPoruka, setErrorPoruka] = useState("");
    const [forma, setForma] = useState({
        ime: "",
        prezime: "",
        nadimak: "",
        oib: "",
        datumRodenja: "",
        spol: "",
        drzava: "",
        email: "",
        brojMobitela: ""

    });

    const {id} = useParams();
    const navigate = useNavigate();

    useEffect(() => {dohvatiTurnir()}, []);
    useEffect(() => {dohvatiDrzave()}, []);

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

    const dohvatiDrzave = () => {
        fetch('http://localhost:8080/api/drzave')
            .then(response => response.json())
            .then(data => {
                setDrzave(data);
                console.log("Drzave " + data);
            })
            .catch(error => {
                console.log('Error ', error);
            });
    }


        const handleSubmit = (event) => {
        if (validation()) {
            event.preventDefault();
        fetch('http://localhost:8080/api/zahtjev/' + id, {
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
                    setErrorPoruka("Neuspješna prijava. Nadimak već postoji u bazi.");
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

        if (!/^[a-zA-ZčČćĆšŠđĐžŽ]{1,20}$/.test(forma.ime)) {
            errors.ime = "Ime je obavezno polje i sadrži najviše 20 slova."
        }

        if (!/^[a-zA-ZčČćĆšŠđĐžŽ]{1,20}$/.test(forma.prezime)) {
            errors.prezime = "Prezime je obavezno polje i sadrži najviše 20 slova."
        }

        if (!/^[a-zA-ZčČćĆšŠđĐžŽ]{1,20}$/.test(forma.nadimak)) {
            errors.nadimak = "Nadimak je obavezno polje sadrži najviše 20 slova."
        }

        if (!/^[0-9]{11}$/.test(forma.oib)) {
            errors.oib = "OIB je obavezno polje i mora biti 11-znamenkasti broj."
        }
        const danasnjiDatum = new Date();
        const datumForma = new Date(forma.datumRodenja);
        if ((danasnjiDatum < datumForma) ||
            ((danasnjiDatum.getFullYear() - datumForma.getFullYear()) < 18) ||
            (((danasnjiDatum.getFullYear() - datumForma.getFullYear()) === 18) &&
                (danasnjiDatum.getMonth() < datumForma.getMonth()))) {
            errors.datumRodenja = "Osoba ne može biti rođena nakon današnjeg datuma i mora biti punoljetna."
        }

        if (forma.email && !/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/.test(forma.email)) {
            errors.email = "Mail mora biti u propisanoj formi."
        }

        if (forma.brojMobitela && !/^\d+$/.test(forma.brojMobitela)) {
            errors.brojMobitela = "Broj mobitela sadrži samo znamenke."
        }

        setFormErrors(errors);
        return Object.keys(errors).length === 0;
    }


    return (
        <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
        <Form onSubmit={handleSubmit} style={{width: '25rem'}}>

            <div style={{fontSize: "1.5em"}}>Prijavi se na turnir <span style={{fontWeight: "bold"}}>{turnir.naziv}</span></div>
            <Form.Group className="mb-3" controlId="formBasicFirstName">
                <Form.Label>Ime: </Form.Label>
                <Form.Control name="ime" onChange={handleChange} value={forma.ime} type="text" placeholder="Ime igrača" required isInvalid={!!formErrors.ime}></Form.Control>
                <Form.Control.Feedback type="invalid">{formErrors.ime}</Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicLastName">
                <Form.Label>Prezime: </Form.Label>
                <Form.Control name="prezime" onChange={handleChange} value={forma.prezime} type="text" placeholder="Prezime igrača" required isInvalid={!!formErrors.prezime}></Form.Control>
                <Form.Control.Feedback type="invalid">{formErrors.prezime}</Form.Control.Feedback>
            </Form.Group>
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
            <Form.Group className="mb-3" controlId="formBasicFirstDate">
                <Form.Label>Datum rođenja: </Form.Label>
                <Form.Control name="datumRodenja" value={forma.datumRodenja} onChange={handleChange} type="date" required isInvalid={!!formErrors.datumRodenja}></Form.Control>
                <Form.Control.Feedback type="invalid">{formErrors.datumRodenja}</Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicGender">
                <Form.Label>Spol: </Form.Label>
                <Form.Control name="spol" value={forma.spol} onChange={handleChange} as="select" required>
                    <option>M</option>
                    <option>Ž</option>
                </Form.Control>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicFirstCountry">
                <Form.Label>Država: </Form.Label>
                <Form.Control name="drzava" onChange={handleChange} value={forma.drzava} as="select" required>
                    <option></option>
                {drzave.map(drzava => (
                    <option>{drzava.naziv}</option>
                ))}
                </Form.Control>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Email:</Form.Label>
                <Form.Control name="email" value={forma.email} onChange={handleChange} type="email" placeholder="Upisite email (neobavezno)" isInvalid={!!formErrors.email}/>
                <Form.Control.Feedback type="invalid">{formErrors.email}</Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicPhoneNum">
                <Form.Label>Broj mobitela:</Form.Label>
                <Form.Control name="brojMobitela" value={forma.brojMobitela} onChange={handleChange} type="text" placeholder="Broj mobitela: (neobavezno)" isInvalid={!!formErrors.brojMobitela}></Form.Control>
                <Form.Control.Feedback type="invalid">{formErrors.brojMobitela}</Form.Control.Feedback>
            </Form.Group>
            <div style={{color: "red", marginTop:'1rem', marginBottom: '1rem'}}>{errorPoruka}</div>
            <Button variant="primary" type="submit" style={{marginRight: '1em'}}>
                Prijava
            </Button>
            <Button variant="secondary" onClick={() => navigate("/turniri")}>Odustani</Button>
        </Form>
        </div>
    );
}

export default Request;
