import React from "react"
import { deleteEmployee, editEmployee } from "./EmployeeFunctions"
import { views } from "./App"

class Employee extends React.Component {
	render() {
		return (
			<div>
				<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

				</link>
				<h4>Employee</h4>
				<p>{this.props.name}</p>
				<p>{this.props.technology}</p>

				<button
					onClick={() => {
						console.log(this.props.id,this.props.name,this.props.technology)
						this.props.appEditEmployee(this.props.id,this.props.name,this.props.technology)
					}}>
					<i className="fas fa-user-edit"></i>
					Edit
					</button>

				<br /><br />

				<button onClick={() => {
					deleteEmployee(this.props.id, this.props.appSetEmployees)
				}}>
					<i className="fa fa-trash"></i>
					Delete
					</button>
				<hr />
			</div>
		)
	}
}

export default Employee