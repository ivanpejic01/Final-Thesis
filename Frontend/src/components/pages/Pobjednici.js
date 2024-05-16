import React, {useEffect, useState} from "react";
import Table from "react-bootstrap/Table";


const Pobjednici = () => {

    const [tablica, setTablica] = useState(new Map());


    useEffect(() => {dohvatiTablicu()}, []);




    const dohvatiTablicu = () => {
        fetch("http://localhost:8080/api/pobjedniciTablica")
            .then(response => {
                    console.log("Dohvacena tablica");
                    return response.json();
            }).then(data => {
                console.log("Postavljena varijabla tablica");
                const mapa = new Map(Object.entries(data));
                setTablica(mapa);

        }).catch(error => {
            console.log("Neuspjesan dohvat tablice " + error)
        })
    }






    return(


        <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
        <div className="centered-flex"  style={{alignItems: 'center', justifyContent: 'center', width: '50rem'}}>
        <Table striped bordered hover
               style={{
                   display: 'table',
                   backgroundColor: 'white',
                   border: '1px solid #ccc'
               }}
        >
            <thead>
            <tr>
                <th style={{textAlign: 'center'}}>Igrac</th>
                <th style={{textAlign: 'center'}}>Broj pobjeda</th>
            </tr>
            </thead>

            <tbody>
            {Array.from(tablica).map(([kljuc, vrijednost]) => (
                <tr key={kljuc} style={{textAlign: 'center'}}>
                    <td>{kljuc}</td>
                    <td>{vrijednost}</td>
                </tr>

            ))
            }
            </tbody>
        </Table>
        </div>
        </div>


    );
}

export default Pobjednici;