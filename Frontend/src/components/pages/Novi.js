import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';



const Novi = () => {
    const navigate = useNavigate();
    const [vrste, setVrste] = useState([]);
    const [organizatori, setOrganizatori] = useState([]);
    const [objekti, setObjekti] = useState([]);
    const [formErrors, setFormErrors] = useState({});
    const [forma, setForma] = useState({
        nazivTurnira: "",
        datumOdrzavanja: "",
        vrijemeOdrzavanja: "",
        nagradniFond: "",
        brojIgraca: "",
        brojNavijaca: "",
        opis: "",
        vrstaTurnira: "",
        organizatori: "",
        nazivObjekta: "",
        imeOrganizatora: ""
    })



    useEffect(() => {dohvatiVrsteTurnira()}, []);
    useEffect(() => {dohvatiOrganizatore()}, []);
    useEffect(() => {dohvatiObjekte()}, []);

    const dohvatiVrsteTurnira = () => {
        fetch('http://localhost:8080/api/vrste')
            .then(response => response.json())
            .then(data => {
                setVrste(data);
            }).catch(error => {
                console.log(error)
        })
    }

    const dohvatiOrganizatore = () => {
        fetch('http://localhost:8080/api/organizatori')
            .then(response => response.json())
            .then(data => {
                setOrganizatori(data);
            }).catch(error => {
                console.log(error)
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
    const handleChange = (event) => {
        const { name, value } = event.target;
        console.log(name + " " + value);
        setForma((formaPrije) => ({
            ...formaPrije,
            [name]: value,
        }));
    }


    const handleSubmit = (e) => {
        e.preventDefault();
        if (validation()){
            console.log("Objekt" + forma.nazivObjekta);
        for (let element in forma) {
            if (forma.hasOwnProperty(element)) {
                console.log("element " + forma[element]);
            } else {
                console.log("nema")
            }
        }
        fetch('http://localhost:8080/api/novi', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(forma)
        }).then(response => response.json())
            .then(data => {
                console.log(data);
                navigate('/');
            }).catch(error => {
            console.log(error)
        })
    } else {
            e.preventDefault();
        }

    }

    const validation = () => {
        const errors = {};

        if (!/^[a-zA-ZčČćĆšŠđĐžŽ ]{1,50}$/.test(forma.nazivTurnira)) {
            errors.nazivTurnira = "Naziv turnira je obavezno polje i sadrži najviše 50 slova."
        }

        if ((new Date() > new Date(forma.datumOdrzavanja))) {
            errors.datumOdrzavanja = "Datum održavanja turnira ne može biti u prošlosti."
        }

        if (forma.nagradniFond && !/^\d+$/.test(forma.nagradniFond)) {
            errors.nagradniFond = "Unesena vrijednost za nagradni fond mora biti broj."
        }

        if (!/^\d+$/.test(forma.brojIgraca)) {
            errors.brojIgraca = "Broj igrača mora biti broj."
        }

        if (forma.brojNavijaca && !/^\d+$/.test(forma.brojNavijaca)) {
            errors.brojNavijaca = "Broj navijača mora biti broj."
        }


        setFormErrors(errors);
        return Object.keys(errors).length === 0;
    }

    const vrijednostiVremena = ['17:00:00', '18:00:00', '19:00:00', '20:00:00', '21:00:00'];

    return (

        <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
            <Form onSubmit={handleSubmit} style={{width: '22rem'}}>
                <div style={{fontSize: "1.5em"}}>Kreiraj novi turnir!</div>
                <Form.Group className="mb-3" controlId="formBasicTournamentName">
                    <Form.Label>Naziv turnira: </Form.Label>
                    <Form.Control value={forma.nazivTurnira} name="nazivTurnira" onChange={handleChange} type="text" placeholder="Naziv turnira" isInvalid={!!formErrors.nazivTurnira} required></Form.Control>
                    <Form.Control.Feedback type="invalid">{formErrors.nazivTurnira}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicTournamentDate">
                    <Form.Label>Datum održavanja: </Form.Label>
                    <Form.Control value={forma.datumOdrzavanja} name="datumOdrzavanja" onChange={handleChange} type="date" isInvalid={!!formErrors.datumOdrzavanja} required></Form.Control>
                    <Form.Control.Feedback type="invalid">{formErrors.datumOdrzavanja}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicTournamentTime">
                    <Form.Label>Vrijeme: </Form.Label>
                    <Form.Control value={forma.vrijemeOdrzavanja} name="vrijemeOdrzavanja" type="text" onChange={handleChange} as="select" required>
                        {vrijednostiVremena.map(vrijeme => (
                            <option>{vrijeme}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPrizeMoney">
                    <Form.Label>Nagradni fond (€):</Form.Label>
                    <Form.Control value={forma.nagradniFond} name="nagradniFond" onChange={handleChange} type="text" isInvalid={!!formErrors.nagradniFond} placeholder="Nagradni fond (neobavezno)" />
                    <Form.Control.Feedback type="invalid">{formErrors.nagradniFond}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPlayers">
                    <Form.Label>Broj igrača: </Form.Label>
                    <Form.Control value={forma.brojIgraca} name="brojIgraca" onChange={handleChange} type="text" isInvalid={!!formErrors.brojIgraca} placeholder="Broj igrača" required/>
                    <Form.Control.Feedback type="invalid">{formErrors.brojIgraca}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicFans">
                    <Form.Label>Najveći dopušten broj navijača:</Form.Label>
                    <Form.Control value={forma.brojNavijaca} name="brojNavijaca" onChange={handleChange} type="text" isInvalid={!!formErrors.brojNavijaca} placeholder="Broj navijača: (neobavezno)"/>
                    <Form.Control.Feedback type="invalid">{formErrors.brojNavijaca}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicDescription">
                    <Form.Label>Dodatni opis:</Form.Label>
                    <Form.Control value={forma.opis} name="opis" onChange={handleChange} type="text" placeholder="Dodatni opis (neobavezno)"/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicType">
                    <Form.Label>Vrsta turnira: </Form.Label>
                    <Form.Control value={forma.vrstaTurnira} name="vrstaTurnira" type="text" onChange={handleChange}  as="select" required>
                        <option></option>
                        {vrste.map(vrsta => (
                            <option>{vrsta.nazivVrsta}</option>
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
                <Form.Group value={forma.imeOrganizatora} className="mb-3" controlId="formBasicFirstOrganizators">
                    <Form.Label>Organizator: </Form.Label>
                    <Form.Control name="imeOrganizatora" onChange={handleChange} as="select" required>
                        <option></option>
                        {organizatori.map(organizator => (
                            <option>{organizator.ime} {organizator.prezime}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
                <Button variant="primary" type="submit" style={{marginRight: '1em'}}>
                    Submit
                </Button>
                <Button variant="secondary" onClick={() => navigate("/")}>Odustani</Button>

            </Form>
        </div>
    );

}

export default Novi;