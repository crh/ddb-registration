def persons = Person.getAllPersons();
html.html {
	head {
		title 'Start'
	}
	body {
		div ('id': 'header')
		div ('id': 'content'){
			div ('class': 'reg-container-secondstep'){
				persons.each {
					li "${it.id}"
				}
				form (method : 'POST' , action : 'index.groovy?action=new_organization&step=1'){
					div ('id':'salutation-container', 'class':'fields-container'){
						label ('for':'salutation', 'Anrede:')
						select ('type':'text', 'id':'salutation', 'name':'salutation'){
								option ('value':'Herr', "Herr")
							}
					}
					div ('id':'title-container', 'class':'fields-container'){
						label ('for':'title', 'Titel:')
						input ('type':'input', 'id':'title', 'name':'title')
					}
					div ('id':'forename-container', 'class':'fields-container'){
						label ('for':'forename', 'Vorname:')
						input ('type':'input', 'id':'forename', 'name':'forename')
					}
					div ('id':'surename-container', 'class':'fields-container'){
						label ('for':'surename', 'Nachname:')
						input ('type':'input', 'id':'surename', 'name':'surename')
					}
					div ('id':'email-container', 'class':'fields-container'){
						label ('for':'email', 'Email:')
						input ('type':'input', 'id':'email', 'name':'email')
					}
					div ('id':'telephonenumber-container', 'class':'fields-container'){
						label ('for':'telephonenumber', 'Telefonnummer:')
						input ('type':'input', 'id':'telephonenumber', 'name':'telephonenumber')
					}
					div ('id':'faxnumber-container', 'class':'fields-container'){
						label ('for':'faxnumber', 'Faxnummer:')
						input ('type':'input', 'id':'faxnumber', 'name':'faxnumber')
					}
					div ('id':'jobtitle-container', 'class':'fields-container'){
						label ('for':'jobtitle', 'Tötigkeitsbezeichnung:')
						input ('type':'input', 'id':'jobtitle', 'name':'jobtitle')
					}
					input ('type':'submit', 'value':'Personendaten speichern und weiter')
				}//Closing form
			}
		}
	}
}