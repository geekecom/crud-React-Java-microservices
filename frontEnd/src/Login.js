import React from "react"
import showEmployees from "./EmployeeFunctions"
import fetchUrlGet from "./Utils";

export var token = null

class Login extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            username: "",
            password: "",
            token: null
        };
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    appSetIsLoggedIn = this.props.appSetIsLoggedIn
    appSetEmployees = this.props.appSetEmployees

    componentDidMount = () => {
        var storagedToken = localStorage.getItem("token")
        if (storagedToken != null) {
            token = storagedToken

            this.validateToken().then(value => {
                if (value === true) {
                    this.loginSuccessfull()
                }
            });
        }
    }

    handleChange(event) {
        const {
            name,
            value
        } = event.target
        this.setState({
            [name]: value
        })
    }

    handleSubmit(event) {
        event.preventDefault()
        const body = {
            "username": this.state.username,
            "password": this.state.password
        }

        fetch("http://localhost:8766/auth", {
                method: 'POST',
                body: JSON.stringify(body),
            })
            .then((response) => {
                if (response.ok) {
                    let headers = response.headers;
                    let headersArray = []
                    headers.forEach(element => headersArray.push(element));
                    token = headersArray[0]
                    localStorage.setItem("token", token)
                    console.log("Token: " + token)
                    this.setState(() => {
                        return {
                            token: token
                        }
                    })
                    this.loginSuccessfull()
                } else {
                    console.error("Login error")
                }
            })
    }

    validateToken = async () => {
        var promiseResponse = false
        await fetchUrlGet("https://localhost:8764")
            .then(function(response) {
                if (response.status === 404)
                    promiseResponse = true
            });

        return promiseResponse


    }


    loginSuccessfull = () => {
        console.log(token)
        if (token != null)
            this.appSetIsLoggedIn(true)
        showEmployees(this.props.appSetEmployees)
    }

    render() {
        return (
            <div>
				<h1>Login</h1>
				<form onSubmit={this.handleSubmit}>
					<input
						name="username"
						onChange={this.handleChange}
					/>
					<br /><br />
					<input
						name="password"
						type="password"
						onChange={this.handleChange}
					/>
					<br /><br />
					<button>Log in</button>
				</form>
			</div>
        );
    }
}

export default Login