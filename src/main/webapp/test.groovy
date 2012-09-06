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
def method = request.method

if (!session) {
    session = request.getSession(true)
}
//forward 'test2.html'

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
				li "${it.id}"
			}
		}
		div "${request.getParameters().id}"
		form ('action': 'http://www.google.com'){
			input ('type': 'submit', 'value':'culo')
		}
    }
}