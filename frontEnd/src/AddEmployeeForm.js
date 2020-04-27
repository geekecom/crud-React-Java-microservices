import React from "react"
import { fetchUrlGet } from "./Utils"
import { views } from "./App"
import { addEmployee } from "./EmployeeFunctions"

class AddEmployeeForm extends React.Component {

	constructor(props) {
		super(props)
		this.state = {
			employeeName: "",
			technology: ""
		}

		this.setView = this.props.setView
	}

	handleChange = (event) => {
		const { name, value } = event.target
		this.setState({
			[name]: value
		})
	}

	addEmployee = (event) => {
		event.preventDefault()
		addEmployee(this.state.employeeName, this.state.technology, this.props.appSetEmployees)
		this.setView(views.EMPLOYEES)
	}

	render() {
		return (
			<div>
				<h1>Add employee</h1>
				<form onSubmit={this.addEmployee}>
					<table>
						<thead></thead>
						<tbody>
							<tr>
								<th>
									Employee name:
							</th>
								<th>
									<input
										name="employeeName"
										required= "true"
										onChange={this.handleChange}
									/>
								</th>
							</tr>
							<tr>
								<th>
									Technology:
							</th>
								<th>
									<input
										name="technology"
										required= "true"
										onChange={this.handleChange}
									/>
								</th>
							</tr>
						</tbody>
					</table>
					<br />
					<button
						name="addEmployeeFormButton"
						type="submit">
						Add employee</button>
				</form>
			</div>
		)
	}
}

export default AddEmployeeForm