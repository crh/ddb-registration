import groovyx.net.http.*
import javax.servlet.http.*
import org.apache.http.impl.cookie.BasicClientCookie

def hasCookies = false
def session_id = ""
if(request.getCookies()){
	hasCookies = true
	session_id = checkCookies(request.getCookies(),'session_id')
}
//def http = new HTTPBuilder("http://localhost:8080/ddb-registration")
/*
cookie.setVersion(1)
cookie.setDomain("localhost")
cookie.setPath("/ddb-registration")
cookie.setAttribute(cookie.VERSION_ATTR, "1");
cookie.setAttribute(cookie.DOMAIN_ATTR, "localhost");
cookie.setAttribute(cookie.PORT_ATTR, "80,8080");
//cookie.setAttribute(cookie.VERSION_ATTR, "1")
//cookie.setAttribute(cookie.DOMAIN_ATTR, "localhost")
http.client.cookieStore.addCookie(cookie)
def html = http.get(path: '/index.groovy', query: [q:'groovy'])
*/
def params = request.getParameters()

if(params.action != null && params.size() == 1 && params.action == "new_registration"){
	forward 'views/registration_first_step.groovy'
}
//We check the data inserted in the first step, we create a new instance of person in the system
//and then we pass to the second step
else if(params.action !=null && params.size() > 2 && params.action == "new_registration" && params.step == "2"){
	//Check if the second step is already done. If not let it enter
	//if(session && session.uid){
	if(hasCookies && session_id !=""){
		def person = Person.getPerson(session_id)
		if(person.title == null ||  person.foreName == null ||  person.jobTitle == null)
			forward 'views/registration_second_step.groovy'
	}else{
		//session = request.getSession(true)
		try{
			def person = new Person([id: params.nickname, sureName: params.nickname, email: "itititit@ititit.it"])
			//person.postPerson()
			def cookie = new Cookie('session_id', params.nickname)
			response.addCookie(cookie)
			//session.uid = person.id
		}
		catch (Throwable e){
			errorHTML(e.message)
		}
	}
	forward 'views/registration_second_step.groovy'
}
//We check the data inserted in the second step, we update the instance of the same person in the system
//and then we pass to the third step
else if(hasCookies && session_id && params.action == "new_organization" && params.step == "1"){
	try{
		def person = new Person([id: session_id, title: params.title, salutation: params.salutation, sureName: params.surename, foreName: params.forename, telephoneNumber: params.telephonenumber, faxNumber: params.faxnumber, email: params.email])
		person.updatePerson(session_id)
	}
	catch (Throwable e){
		errorHTML(e.message)
	}
	forward 'views/organization_first_step.groovy'
}
else if(hasCookies && session_id && params.action == "new_organization" && params.step == "2" ){
	try{
		def post = [
			id : session_id,
			displayName : 'Masi and Co',
			abbreviation: '',
			description: '',
			legalStatus:'',
			category:'Archiv',
			street: 'Eilper Strasse',
			houseIdentifier: '71-75',
			addressSupplement:'',
			postalCode: '58091',
			city: 'Hagen',
			country: 'de',
			latitude: '51.3474',
			longitude: '7.4904',
			telephone : '+49 123',
			fax : '+49 123',
			email : 'emiliano@masi.it',
			url: 'http://www.historisches-centrum.de',
			logo: '',
			orgParent: '',
			created: '2012-08-16T08:31:47+0200',
			modified: '2012-08-24T16:02:41+0200',
			status: 'pending'
		]
		def organization = new Organization([id: session.uid, title: params.title, salutation: params.salutation, sureName: params.surename, foreName: params.forename, telephoneNumber: params.telephonenumber, faxNumber: params.faxnumber, email: params.email])
		person.updatePerson(session.uid)
	}
	catch (Throwable e){
		errorHTML(e.message)
	}
	forward 'views/organization_first_step.groovy'
}
else{
	html.html {
				head {
					title 'Start'
				}
				body {
					div ('id': 'header')
					div ('id': 'content'){
						div "Mit bestehendem Account onmelden"
						div {
							a ('href':'index.groovy?action=new_registration', 'Neu Registrieren')
						}
					}
				}
	}
}

public errorHTML(String error_msg){
	def dfhtml = html.html{}
	dfhtml += html.header{title 'error'}
	dfhtml += html.body{
							div ('id':'header')
							div('id':'content'){
								div ('class':'error', error_msg)
							}
						}
}
public checkCookies(cookies,name){
	res = ""
	cookies.each {
		if(it.name==name){
			res=it.value
		}	
	}
	return res
}