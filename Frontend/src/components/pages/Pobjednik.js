import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";


const Pobjednik = () => {
    const {id} = useParams();

    useEffect(() => {dohvatiIgrace()}, []);
    useEffect(() => {dohvatiTurnir(id)}, []);
    const navigate = useNavigate();


    const [imePobjednika, setImePobjednika] = useState({
        nadimakPobjednika: ""
    })
    const [igraci, setIgraci] = useState([]);
    const [turnir, setTurnir] = useState({});

    const dohvatiIgrace = () => {
        fetch('http://localhost:8080/api/igraci/turnir/' + id)
            .then(response => {
                if(response.ok) {
                    console.log("Imam igrace")
                    return response.json();

                } else {
                    console.log("Nemam igrace")
                }
            }).then(data => {
                setIgraci(data);
                console.log("Igraci nisu prazni")
        }).catch(error => {
            console.log("Imam error " + error);
        })
    }

    const dohvatiTurnir = (id) => {
        fetch('http://localhost:8080/api/turniri/' + id)
            .then(response => {
                return response.json()
            }).then(data => {
            setTurnir(data);
        })
    }

    const handleChange = (e) => {
        setImePobjednika({
            nadimakPobjednika: e.target.value
        });
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('http://localhost:8080/api/pobjednik/' + id, {
            method : 'POST',
            headers:  {'Content-Type': 'application/json'},
            body: JSON.stringify(imePobjednika)
        }).then(response => {
            if (response.ok) {
                navigate("/uredi");
                return response.json();
            } else {
                console.log("Error")
            }

        }).then(data => {
            console.log(data);
        }).catch(error => {
            console.log(error);
        })
    }

    return (
        <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
            <Form onSubmit={handleSubmit}>
                <div style={{fontSize: "1.5em"}}>Dodaj pobjednika turnira <span style={{fontWeight:'bold'}}>{turnir.naziv}</span>!</div>
                <Form.Group className="mb-3" controlId="formBasicWinner">
                    <Form.Label>Pobjednik: </Form.Label>
                    <Form.Control value={imePobjednika.nadimakPobjednika} name="imePobjednika" type="text" onChange={handleChange}  as="select" required>
                        <option></option>
                        {igraci.map(igrac => (
                            <option>{igrac.nadimak}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
                <Button variant="primary" type="submit" style={{marginRight: '1em'}}>
                    Submit
                </Button>
                <Button variant="secondary" onClick={() => navigate("/uredi")}>Odustani</Button>
            </Form>
        </div>
    );
}

export default Pobjednik;