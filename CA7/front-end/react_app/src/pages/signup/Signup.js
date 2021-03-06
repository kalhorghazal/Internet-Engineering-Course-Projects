import "./signup-styles.css"
import * as React from "react";
import {toast} from "react-toastify";
import API from '../../apis/api';
import { Link } from "react-router-dom";
import validateToken from "../../services/validate-tokens";


export default class SignUp extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            secondName: '',
            birthDate: '',
            studentId: '',
            field: '',
            faculty: '',
            level: '',
            email: '',
            password: '',
        }
        this.handleNameChange = this.handleNameChange.bind(this)
        this.handleSecondNameChange = this.handleSecondNameChange.bind(this)
        this.handleStudentIdChange = this.handleStudentIdChange.bind(this)
        this.handleBirthDateChange = this.handleBirthDateChange.bind(this)
        this.handleFieldChange = this.handleFieldChange.bind(this)
        this.handleFacultyChange = this.handleFacultyChange.bind(this)
        this.handleLevelChange = this.handleLevelChange.bind(this)
        this.handleEmailChange = this.handleEmailChange.bind(this)
        this.handlePasswordChange = this.handlePasswordChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)

        if(validateToken()) {
            window.location.href = "http://localhost:3000/"
        }
    }

    handleNameChange(event) {
        this.setState({
            name: event.target.value
        });
    }

    handleSecondNameChange(event) {
        this.setState({
            secondName: event.target.value
        });
    }

    handleStudentIdChange(event) {
        this.setState({
            studentId: event.target.value
        });
    }

    handleFieldChange(event) {
        this.setState({
            field: event.target.value
        });
    }

    handleFacultyChange(event) {
        this.setState({
            faculty: event.target.value
        });
    }

    handleLevelChange(event) {
        this.setState({
            level: event.target.value
        });
    }

    handleEmailChange(event) {
        this.setState({
            email: event.target.value
        });
    }

    handlePasswordChange(event) {
        this.setState({
            password: event.target.value
        });
    }

    handleBirthDateChange(event) {
        this.setState({
            birthDate: event.target.value
        });
    }

    componentDidMount() {
        document.title = "Sign up - Bolbolestan";
        document.body.classList.add("main-bg")
        toast.configure({rtl: true, className: "text-center", position: "top-right"});

    }

    handleSubmit(e) {
        e.preventDefault();
        var shouldReturn = false;
        if(!this.state.email){
            console.log('email empty -_-')
            toast.warning('???????? ?????????? ???????? ???? ????????')
            shouldReturn = true;
        }
        if(!this.state.password){
            console.log('password empty -_-')
            toast.warning('???????? ?????????????? ???????? ???? ????????')
            shouldReturn = true;
        }
        if(!this.state.name){
            console.log('name empty -_-')
            toast.warning('???????? ?????? ???????? ???? ????????')
            shouldReturn = true;
        }
        if(!this.state.secondName){
            console.log('secondName empty -_-')
            toast.warning('???????? ?????? ???????????????? ???????? ???? ????????')
            shouldReturn = true;
        }
        if(!this.state.birthDate){
            console.log('birthDate empty -_-')
            toast.warning('???????? ?????????? ???????? ???????? ???? ????????')
            shouldReturn = true;
        }
        if(!this.state.studentId){
            console.log('studentId empty -_-')
            toast.warning('???????? ?????????? ???????????????? ???????? ???? ????????')
            shouldReturn = true;
        }
        if(!this.state.field){
            console.log('field empty -_-')
            toast.warning('???????? ???????? ???????? ???? ????????')
            shouldReturn = true;
        }
        if(!this.state.faculty){
            console.log('faculty empty -_-')
            toast.warning('???????? ?????????????? ???????? ???? ????????')
            shouldReturn = true;
        }
        if(!this.state.level){
            console.log('level empty -_-')
            toast.warning('???????? ???????? ???????????? ???????? ???? ????????')
            shouldReturn = true;
        }
        if(shouldReturn)
            return;
        API.post('auth/signup/', {
            name: this.state.name,
            secondName: this.state.secondName,
            studentId: this.state.studentId,
            birthDate: this.state.birthDate,
            field: this.state.field,
            faculty: this.state.faculty,
            level: this.state.level,
            email: this.state.email,
            password: this.state.password
        }).then((resp) => {
            if(resp.status === 200) {
                console.log(resp.data);
                console.log('???? ????')
                toast.success('???????? ?????????? ???? ???????????? ?????????? ???? - ???????? ????????')
            }
        }).catch(error => {
            console.log('??????')
            toast.error('???????? ?????????? ???????????? ???????? ???????? - ?????????? ????????????')
        })
    }

    validateToken() {
        if (typeof this.state.email != "string") return false
        console.log('!isNaN(this.state.email)')
        return !isNaN(this.state.email)
    }

    render() {
        return (
            <div className="login-container text-c animated flipInX">
                <h3 className="text-whitesmoke">???????? ???? ???????????? ????????????????</h3>
                <div className="container-content">
                    <form className="margin-t" onSubmit={this.handleSubmit}>
                        <div className="form-group">
                                <input type="text" className="form-control" onChange={this.handleNameChange} placeholder="??????" required=""/>
                        </div>
                        <div className="form-group">
                            <input type="text" className="form-control" onChange={this.handleSecondNameChange} placeholder="?????? ????????????????" required=""/>
                        </div>
                        <div className="form-group">
                            <input type="text" className="form-control" onChange={this.handleBirthDateChange} placeholder="?????????? ????????" required=""/>
                        </div>
                        <div className="form-group">
                            <input type="text" className="form-control" onChange={this.handleStudentIdChange} placeholder="?????????? ????????????????" required=""/>
                        </div>
                        <div className="form-group">
                            <input type="text" className="form-control" onChange={this.handleFieldChange} placeholder="????????" required=""/>
                        </div>
                        <div className="form-group">
                            <input type="text" className="form-control" onChange={this.handleFacultyChange} placeholder="??????????????" required=""/>
                        </div>
                        <div className="form-group">
                            <input type="text" className="form-control" onChange={this.handleLevelChange} placeholder="???????? ????????????" required=""/>
                        </div>
                        <div className="form-group">
                            <input type="text" className="form-control" onChange={this.handleEmailChange} placeholder="??????????" required=""/>
                        </div>
                        <div className="form-group">
                            <input type="password" className="form-control" onChange={this.handlePasswordChange} placeholder="??????????????" required=""/>
                        </div>
                        <button type="submit" className="form-button button-l margin-b">???????? ??????????</button>
                        {this.state.isLoading &&
                        <span className="spinner-border mr-2" role="status" aria-hidden="true"/>
                        }
                        <p class="text-whitesmoke text-center"><small>?????????? ?????????? ????????</small></p>
                        <Link to='/login'><small>???????? ????????</small></Link>
                    </form>
                </div>
            </div>
        );
    }
}
