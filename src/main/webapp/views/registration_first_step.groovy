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