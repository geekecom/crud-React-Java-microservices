import { token } from "./Login"

export async function fetchUrlGet(url) {
	return await fetch(url, {
		headers: {
			Authorization: token
		}
	})
}

export default fetchUrlGet