def persons = Person.getAllPersons();
html.html {
	head {
		title 'Start'
	}
	body {
		div ('id': 'header')
		div ('id': 'content'){
			div "Hauotstandort anlegen"
			div ('class': 'org-container-firststep'){
				persons.each {
					li "${it.id}"
				}
				form (method : 'POST' , action : 'index.groovy?action=new_organization&step=2'){
					div ('id':'salutation-container', 'class':'fields-container'){
						label ('for':'salutation', 'Anrede:')
						select ('type':'text', 'id':'salutation', 'name':'salutation'){
								option ('value':'Herr', "Herr")
							}
					}
					div ('id':'displayname-container', 'class':'fields-container'){
						label ('for':'displayname', 'Bezeichnung / Name:')
						input ('type':'input', 'id':'displayname', 'name':'displayname')
					}
					div ('id':'abbreviation-container', 'class':'fields-container'){
						label ('for':'abbreviation', 'Kurzname:')
						input ('type':'input', 'id':'abbreviation', 'name':'abbreviation')
					}
					div ('id':'description-container', 'class':'fields-container'){
						label ('for':'description', 'Beschreibung:')
						textarea ('id':'description', 'name':'description')
					}
					div ('id':'addresssupplement-container', 'class':'fields-container'){
						label ('for':'addresssupplement', 'Addresszusatz:')
						input ('type':'input', 'id':'addresssupplement', 'name':'addresssupplement')
					}
					div ('id':'street-container', 'class':'fields-container'){
						label ('for':'street', 'Stra&szlig;e, Hausnummer:')
						input ('type':'input', 'id':'street', 'name':'street')
					}
					div ('id':'postalcode-container', 'class':'fields-container'){
						label ('for':'postalcode', 'Posteleitzahl:')
						input ('type':'input', 'id':'postalcode', 'name':'postalcode')
					}
					div ('id':'city-container', 'class':'fields-container'){
						label ('for':'city', 'Ort:')
						input ('type':'input', 'id':'city', 'name':'city')
					}
					input ('type':'submit', 'value':'Hauptstandort anlegen und weiter')
				}//Closing form
			}
		}
	}
}