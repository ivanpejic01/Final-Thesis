import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";


const Prijava = () => {

    const [forma, setForma] = useState({})
    const [errorPoruka, setErrorPoruka] = useState("");
    const navigate = useNavigate();
    const handleSubmit = (event) => {

        event.preventDefault();
        const username = event.target.korisnickoIme.value;
        const password = event.target.lozinka.value;
        console.log(username + ":" + password);
        const header = 'Basic ' + btoa(username + ':' + password);
        fetch('http://localhost:8080/api/welcome', {
            method: 'GET',
            headers: {
                'Authorization': header,
                'Content-Type': 'application/json'
            },
        }).then(response => {
            if (response.status !== 200) {
                setErrorPoruka("Podaci za prijavu nisu točni")
                throw new Error("Nije dobro");

            } else {
                console.log("Dobro je");
                console.log(response.status)
                navigate('/novi');
                //return response.json();
            }
        })
            .then(data => {
                console.log(data);

            }).catch(error => {
                setTimeout(() => {
                    navigate('/prijava')
                }, 0)

                console.log(error)

        })

    }



return (
        <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
        <Form onSubmit={handleSubmit}>
            <div style={{fontSize: "1.5em", width: "22rem"}}>Potrebna je prijava!</div>
            <Form.Group className="mb-3" controlId="formBasicUsername">
                <Form.Label>Ime: </Form.Label>
                <Form.Control name="korisnickoIme" type="text" placeholder="Korisničko ime" required></Form.Control>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Lozinka: </Form.Label>
                <Form.Control name="lozinka" type="password" placeholder="Lozinka" required></Form.Control>
            </Form.Group>
            <div style={{marginTop:'1rem', marginBottom: '1rem', color:'red'}}>{errorPoruka}</div>
            <Button variant="primary" type="submit">
                Prijavi se
            </Button>
        </Form>
        </div>
    );
}

export default Prijava;