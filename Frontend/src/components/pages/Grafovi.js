
import { Bar } from 'react-chartjs-2';
import {useEffect, useState} from "react";
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';


ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
);

const Grafovi = () => {

    const [podaci, setPodaci] = useState(new Map());
    const [turniri, setTurniri] = useState(new Map());
    const [potrebniKljuc, setPotrebniKljuc] = useState(0);
    const [kljucTurniri, setKljucTunriri] = useState(1);
    useEffect(() => {dohvatiPodatke()}, []);
    useEffect(() => {dohvatiTurnire()}, []);


    const dohvatiPodatke = () => {
        fetch('http://localhost:8080/api/turnirOmjeri')
            .then(response => {
                return response.json();
            }).then(data => {
            const turnirMap = new Map();

            for (const turnir of Object.keys(data)) {
                const innerMap = new Map(Object.entries(data[turnir]));
                turnirMap.set(turnir, innerMap);
            }
                setPodaci(turnirMap);

        }).catch(error => {
            console.log(error)
        })
    }

    const dohvatiTurnire = () => {
        fetch('http://localhost:8080/api/turniriPoMjesecima')
            .then(response => {
                return response.json()
            }).then(data => {
            const turniriMap = new Map(Object.entries(data));
                setTurniri(turniriMap);
        })
    }



    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Odnos muškaraca i žena sudionika po pojedinom turniru',
            },
        },
    };

    const optionsTurniri = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Broj turnira po mjesecima',
            },
        },
    };

    const formatirajPodatke = () => {
        const labels = [];
        const valuesMuskarci = [];
        const valuesZene = [];


        for (const  [turnir, innerMap] of podaci) {
            labels.push(turnir);
            console.log("Turnir " + turnir)

            // Iterate over the inner map
            let muskarci = 0;
            let zene = 0;

            for (const [spol, brojac] of innerMap) {
                if (spol === "M") {
                    muskarci += brojac;
                } else if (spol === "Ž") {
                    zene += brojac;
                }
            }
            valuesMuskarci.push(muskarci);
            valuesZene.push(zene);
        }

        return {
            labels: labels,
            datasets: [
                {
                    label: 'Muškarci',
                    data: valuesMuskarci,
                    backgroundColor: 'blue',
                },
                {
                    label: 'Žene',
                    data: valuesZene,
                    backgroundColor: 'red',
                }
            ],
        };
    }

    const formatirajTurnire = () => {
        const labels = [];
        const values = [];
        turniri.forEach((value, key) => {
            labels.push(key);
            values.push(value);
        });

        return {
            labels: labels,
            datasets: [
                {
                    label: 'Turniri po mjesecima',
                    data: values,
                    backgroundColor: 'blue',
                },
            ],
        };
    }

    return(

    <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
        <div style={{width: '50rem', display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
            <h2></h2>
            <Bar key={potrebniKljuc}
                 data={formatirajPodatke()}
                 options={options}/>
            <Bar key={kljucTurniri}
                 data={formatirajTurnire()}
                 options={optionsTurniri}

            />
        </div>
    </div>
    )
}

export default Grafovi;