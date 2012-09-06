/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
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