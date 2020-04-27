import React from "react"
import { fetchUrlGet } from "./Utils"
import { views } from "./App"
import { editEmployee } from "./EmployeeFunctions"

class AddEmployeeForm extends React.Component {

	constructor(props) {
		super(props)

		this.state = {
			id: this.props.id,
			employeeName: this.props.employeeName,
			technology: this.props.technology
		}
		this.appSetView = this.props.appSetView
	}

	handleChange = (event) => {
		if (event.target.name === "employeeName")
			this.setState({ employeeName: event.target.value })
		else if (event.target.name === "technology")
			this.setState({ technology: event.target.value})
	}

	editEmployee = (event) => {
		event.preventDefault()
		editEmployee(this.state.id, this.state.employeeName, this.state.technology, this.props.appSetEmployees)
		this.appSetView(views.EMPLOYEES)
	}

	render() {
		return (
			<div>
				<h1>Edit employee</h1>
				<form onSubmit={this.editEmployee}>
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
										value={this.state.employeeName}
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
										value={this.state.technology}
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
						Edit employee</button>
				</form>
			</div>
		)
	}
}

export default AddEmployeeForm