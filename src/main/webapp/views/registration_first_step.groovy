html.html {
	head {
		title 'Start'
	}
	body {
		div ('id': 'header')
		div ('id': 'content'){
			div ('class': 'reg-container-firststep'){
				form (method : 'POST' , action : 'index.groovy?action=new_registration&step=2'){
					div ('id':'nickname-container', 'class':'fields-container'){
						label ('for':'nickname', 'Benutzername:')
						input ('type':'text', 'id':'nickname', 'name':'nickname')
					}
					div ('id':'password-container', 'class':'fields-container'){
						label ('for':'password', 'Passwort:')
						input ('type':'password', 'id':'password', 'name':'password')
					}
					div ('id':'passwordc-container', 'class':'fields-container'){
						label ('for':'passwordc', 'Passwort (wiedrholen):')
						input ('type':'password', 'id':'passwordc', 'name':'passwordc')
					}
					input ('type':'submit', 'value':'Account anlegen und weiter')
				}//Closing form
			}
		}
	}
}