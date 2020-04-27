import { token } from "./Login"
import { fetchUrlGet } from "./Utils"


export function showEmployees(appSetEmployeesFunction) {
	if (token != null) {
		fetchUrlGet("https://localhost:8764/employee/rest")
			.then(response => {
				return response.json()
			}).then(data => {
				appSetEmployeesFunction(data)
			})
	}
}



export function deleteEmployee(id, appSetEmployees) {
	console.log("Deleting employee with ID: " + id)
	if (token != null) {
		fetchUrlGet("https://localhost:8764/employee/rest/delete/" + id)
			.then(() => {
				console.log("deleted employee")
				showEmployees(appSetEmployees)
			})
	}
}

export function addEmployee(employeeName, technology, appSetEmployees) {
	if (token != null) {
		var url = "https://localhost:8764/employee/rest/addEmployee/" + employeeName + "/" + technology
		fetchUrlGet(url)
			.then(() => {
				console.log("employee added")
				showEmployees(appSetEmployees)
			})
	}
}

export function editEmployee(id, employeeName, technology, appSetEmployees) {
	if (token != null) {
		var url = "https://localhost:8764/employee/rest/editEmployee/" + id + "/" + employeeName + "/" + technology
		fetchUrlGet(url)
			.then(() => {
				console.log("employee edited")
				showEmployees(appSetEmployees)
			})
	}
}

export default showEmployees