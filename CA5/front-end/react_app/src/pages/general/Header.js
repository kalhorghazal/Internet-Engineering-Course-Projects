import React from "react";
import "../../assets/styles/header.css";
import Modal from 'react-bootstrap/Modal'
import Exit from "./modals/Exit";
import {Link} from "react-router-dom";

export default class Header extends React.Component{
    constructor(props) {
        super(props);
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.state = {
            show: false,
            firstOption: this.props.firstOption,    
            secondOption: this.props.secondOption,
            firstRoute: this.props.firstRoute,
            secondRoute: this.props.secondRoute
        };
    }

    handleClose() {
        this.setState({ show: false });

    }

    handleShow() {
        this.setState({ show: true });
    }

    render() {
        return (
            <div>
                <header>
                <div className="header">
                    <div className="m-3"> 
                    </div>
                    <div className="bolbolestan-logo m-1">
                        <Link to= "/">
                            <img src={require("../../assets/images/logo.png")} alt=""/>
                        </Link>
                    </div>
                    <div className="option m-2">
                        <Link to= {this.state.firstRoute}>
                            {this.state.firstOption}
                        </Link>
                    </div>
                    <div className="option m-2">
                        <Link to= {this.state.secondRoute}>
                        {this.state.secondOption}
                        </Link>
                    </div>
                    <div className="logout m-1" onClick={this.handleShow}>
                        <span className="logout-text clickable">
                            خروج
                        </span>
                        <span>
                            <i className="flaticon clickable flaticon-log-out"></i>
                        </span>
                    </div>

                    <div className="m-3">
                    </div>
                    </div>
                </header>
                <Modal show={this.state.show} onHide={this.handleClose}>
                   <Exit/>
                </Modal>
            </div>
        );
    }
}