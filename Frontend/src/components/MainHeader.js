import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {useNavigate} from "react-router-dom";
import {NavDropdown} from "react-bootstrap";

const MainHeader = () =>  {

    const navigate = useNavigate();

    return (
        <Navbar bg="light" expand="lg">
            <Container style={{margin:0}}>
                <Navbar.Brand>  <a>
                    <img src="https://img.freepik.com/free-vector/dart-board-realistic_1284-4663.jpg?w=2000" style={{width: '5.5rem'}} />
                </a></Navbar.Brand>
                <Navbar.Brand >Aplikacija za pikado</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link onClick={() => navigate("/")}>Početna</Nav.Link>
                        <Nav.Link onClick={() => navigate("/turniri")}>Turniri</Nav.Link>
                        <Nav.Link onClick={() => navigate("/odrzani")}>Održani turniri</Nav.Link>
                        <Nav.Link onClick={() => navigate("/pobjednici")}>Tablica pobjednika</Nav.Link>
                        <Nav.Link onClick={() => navigate("/grafovi")}>Grafovi</Nav.Link>
                        <Nav.Link onClick={() => navigate("/prijava")}>Kreiraj novi(admin)</Nav.Link>
                        <Nav.Link onClick={() => navigate("/prijavaU")}>Uredi/obriši postojeći turnir(admin)</Nav.Link>
                    </Nav>
                    {/* <Nav className="ms-auto">
                        <Nav.Link onClick={() => navigate("/prijava")} >Prijava-administrator</Nav.Link>
                    </Nav>*/}
                </Navbar.Collapse>
           </Container>
        </Navbar>
    );
}

export default MainHeader;