package zuul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    String token;

    @RequestMapping("/login")
    public String loginPage(Model model) {
	return "login.jsp";
    }

    @PostMapping("/login")
    public String loginToken(@ModelAttribute("username") String username, @ModelAttribute("password") String password)
	    throws Exception {
//	URL url = new URL("http://localhost:8764/auth");
//	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//	connection.setRequestMethod("POST");
//	connection.setRequestProperty("Content-Type", "application/json; utf-8");
//	connection.setDoOutput(true);
//	String jsonInputString = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
//	
//	try(OutputStream os = connection.getOutputStream()) {
//	    byte[] input = jsonInputString.getBytes("utf-8");
//	    os.write(input, 0, input.length);           
//	}
//	
//	try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
//	    StringBuilder response = new StringBuilder();
//	    String responseLine = null;
//	    while ((responseLine = br.readLine()) != null) {
//		response.append(responseLine.trim());
//	    }
//	    
//	}
//	String authorizationHeader = connection.getHeaderField("Authorization");
//	token = authorizationHeader.substring(7);
	return "gallery";
    }
}
