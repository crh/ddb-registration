def method = request.method

if (!session) {
    session = request.getSession(true)
}

def test = new Person([id: "uid1342438318062", title: "Master Degree Sw Ing.", salutation: "Herr", sureName: "Emiliano", foreName: "Masi", telephoneNumber: "+49 888", faxNumber: "+49 777", email: "emiliano@masi.it"])
def persons = test.getAllPersons();

if (!session.groovlet) {
    session.groovlet = 'Groovlets rock!'
}

html.html {
    head {
        title 'Groovlet info'
    }
    body {
        h1 'General info'
        ul {
            li "Method: ${method}"
            li "RequestURI: ${request.requestURI}"
            li "session.groovlet: ${session.groovlet}"
            li "application.version: ${context.version}"
        }
        
        h1 'Headers'
        ul {
            headers.each {
                li "${it.key} = ${it.value}"
            }
        }
		
		h1 'Persons'
		ul {
			persons.each {
				li "${it.uid}"
			}
		}
    }
}