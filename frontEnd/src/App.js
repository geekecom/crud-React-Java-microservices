import React from "react";
import Login from "./Login"
import AddEmployeeForm from "./AddEmployeeForm";
import EditEmployeeForm from "./EditEmployeeForm";
import Employee from "./Employee";

export var views = {
    LOGIN_PAGE: "loginPage",
    EMPLOYEES: "employees",
    ADD_EMPLOYEE_FORM: "addEmployeeForm",
    EDIT_EMPLOYEE_FORM: "editEmployeeForm"
}

class App extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            isLoggedIn: false,
            currentView: views.LOGIN_PAGE,
            employees: [],
            employeeToEdit: [
                ["id", null],
                ["employeeName", null],
                ["technology", null]
            ]
        }
    }

    setIsLoggedIn = (booleanInput) => {
        this.setState({
            isLoggedIn: booleanInput
        })
        if (booleanInput === true) {
            console.log("login")
            this.setState({
                currentView: views.EMPLOYEES
            })
        } else {
            console.log("logout")
            this.setState({
                currentView: views.LOGIN_PAGE
            })
        }
    }

    setEmployees = (data) => {
        this.setState({
            employees: data
        })
    }

    showAddEmployeeForm = () => {
        this.setState({
            currentView: views.ADD_EMPLOYEE_FORM
        })
    }

    setView = (view) => {
        this.setState({
            currentView: view
        })
        console.log("Changed view to: " + this.state.currentView)
    }

    editEmployee = (id, employeeName, technology) => {
        this.setState({
            currentView: views.EDIT_EMPLOYEE_FORM,
            employeeToEdit: [id, employeeName, technology]
        })
    }

    logout = () => {
		Login.token = null
		localStorage.setItem("token",null)
        this.setIsLoggedIn(false)
    }

    render() {
        if (!this.state.isLoggedIn) {
            return (
                <div>
					<Login
						appSetIsLoggedIn={this.setIsLoggedIn.bind(this)}
						appSetEmployees={this.setEmployees.bind(this)}
					/>
				</div>
            )
        } else if (this.state.isLoggedIn && (this.state.currentView === views.EMPLOYEES)) {
            return (
                <div>
					<h1>Employees</h1>
					<button name="addEmployeeButton" onClick={this.showAddEmployeeForm}>Add employee</button>
					<button name="logoutButton" onClick={this.logout} style={{marginLeft:10}}>Logout</button>
					
					{this.state.employees.map(employee => (
						<Employee
							key={employee.id}
							id={employee.id}
							name={employee.name}
							technology={employee.technology.name}
							appSetEmployees={this.setEmployees.bind(this)}
							appEditEmployee={this.editEmployee.bind(this)}
						/>
					))}
				</div>
            )
        } else if (this.state.isLoggedIn && (this.state.currentView === views.ADD_EMPLOYEE_FORM)) {
            return (
                <div>
					<AddEmployeeForm
						setView={this.setView}
						appSetEmployees={this.setEmployees.bind(this)}
					/>
				</div>
            )
        } else if (this.state.isLoggedIn && (this.state.currentView === views.EDIT_EMPLOYEE_FORM)) {
            return (
                <div>
					<EditEmployeeForm
						appSetView={this.setView}
						appSetEmployees={this.setEmployees}
						id={this.state.employeeToEdit[0]}
						employeeName={this.state.employeeToEdit[1]}
						technology={this.state.employeeToEdit[2]}
					/>
				</div>
            )
        } else {
            return (
                <div>
					<p>Undefined state</p>
					<p>Current view state: {this.state.currentView}</p>
				</div>
            )
        }
    }

}

export default App;