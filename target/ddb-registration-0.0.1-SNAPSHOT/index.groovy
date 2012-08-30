def params = request.getParameters()

if(params.action != null && params.size() == 1 && params.action == "new_registration"){
	forward 'views/registration_first_step.groovy'
}
//We check the data inserted in the first step, we create a new instance of person in the system
//and then we pass to the second step
else if(params.action !=null && params.size() > 2 && params.action == "new_registration" && params.step == "2"){
	//Check if the second step is already done. If not let it enter
	if(session && session.uid){
		def person = Person.getPerson(session.uid)
		if(person.title == null ||  person.foreName == null ||  person.jobTitle == null)
			forward 'views/registration_second_step.groovy'
	}else{
		session = request.getSession(true)
		try{
			def person = new Person([id: params.nickname, sureName: params.nickname, email: "itititit@ititit.it"])
			person.postPerson()
			session.uid = person.id
		}
		catch (Throwable e){
			errorHTML(e.message)
		}
	}
	forward 'views/registration_second_step.groovy'
}
//We check the data inserted in the second step, we update the instance of the same person in the system
//and then we pass to the third step
else if(session && session.uid && params.action == "new_organization" && params.step == "1" ){
	try{
		def person = new Person([id: session.uid, title: params.title, salutation: params.salutation, sureName: params.surename, foreName: params.forename, telephoneNumber: params.telephonenumber, faxNumber: params.faxnumber, email: params.email])
		person.updatePerson(session.uid)
	}
	catch (Throwable e){
		errorHTML(e.message)
	}
	forward 'views/organization_first_step.groovy'
}else{
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