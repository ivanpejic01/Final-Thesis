import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import MainHeader from './components/MainHeader'
import Home from './components/pages/Home'
import Turniri from "./components/pages/Turniri";
import Request from "./components/pages/Request"
import OdrzaniTurniri from "./components/pages/OdrzaniTurniri";
import Prijava from "./components/pages/Prijava";
import Novi from "./components/pages/Novi";
import Uredi from "./components/pages/Uredi";
import Pobjednici from "./components/pages/Pobjednici";
import Forma from "./components/pages/Forma";
import Pobjednik from "./components/pages/Pobjednik";
import PrijavaU from "./components/pages/PrijavaU";
import Postojeci from "./components/pages/Postojeci";
import Grafovi from "./components/pages/Grafovi";

function App() {
  return (
    <>
     <BrowserRouter>
         <MainHeader />
         <Routes>
            <Route path="/*" element={<Home />}></Route>
             <Route path="/turniri" element={<Turniri />}></Route>
             <Route path="/request/:id" element={<Request />}></Route>
             <Route path="/odrzani" element={<OdrzaniTurniri />}></Route>
             <Route path="/pobjednici" element={<Pobjednici />}></Route>
             <Route path="/prijava" element={<Prijava />}></Route>
             <Route path="/novi" element={<Novi />}></Route>
             <Route path="/uredi" element={<Uredi />}></Route>
             <Route path="/uredi/:id" element={<Forma />}></Route>
             <Route path="/pobjednik/:id" element={<Pobjednik />}></Route>
             <Route path="prijavaU" element={<PrijavaU />}></Route>
             <Route path="/postojeci/:id" element={<Postojeci />}></Route>
             <Route path="/grafovi" element={<Grafovi />}></Route>
         </Routes>

     </BrowserRouter>
    </>
  );
}

export default App;
