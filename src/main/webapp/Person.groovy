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
import groovyx.net.http.*
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.URLENC
import groovy.json.*
import org.apache.http.impl.cookie.BasicClientCookie

//TODO: Create method for the interpretation of the errors


class Person{
	String id
	String title
	String salutation
	String sureName
	String foreName
	String jobTitle
	String telephoneNumber
	String faxNumber
	String email
	
	void fillPersonData(Map personData){
		this.id = personData.id
		this.title = personData.title
		this.salutation = personData.salutation
		this.sureName = personData.sureName
		this.foreName = personData.foreName
		this.jobTitle = personData.jobTitle
		this.telephoneNumber = personData.telephoneNumber
		this.faxNumber = personData.faxNumber
		this.email = personData.email
	}
	
	static List getAllPersons() {
		def http = new HTTPBuilder("http://141.66.8.240:8080")
		def res = []
		http.get( path: '/aas/persons/', query: [id: 'httpbuilder']){ resp, json ->

			println resp.status
			println json.size()
			println json.uid
			json.uid.each{
				res.add(getPerson(it))
			}
		}
		/*
		http.request(Method.GET, JSON) {
			uri.path = '/aas/organizations'
			
			response.success = {resp, json ->
				json.oid.each {
					println it
					res.add(getOrganization(it))
				}
			}
		}*/
		return res
	}
	static Person getPerson(id){
		def http = new HTTPBuilder("http://141.66.8.240:8080")
		Person res = new Person()
		http.get( path: '/aas/persons/'+id, query: [id: 'httpbuilder']){ resp, json ->

			println resp.status
			println json
			res.fillPersonData(json)
			println "---------"
		}
		return res
	}
	void postPerson(){
		def http = new HTTPBuilder("http://141.66.8.240:8080")
		def postBody = [
			id : this.id,
			title : this.title,
			salutation : this.salutation,
			sureName : this.sureName,
			foreName : this.foreName,
			jobTitle : this.jobTitle,
			telephoneNumber : this.telephoneNumber,
			faxNumber : this.faxNumber,
			email : this.email
			]
		http.post(path: '/aas/persons', body: postBody, requestContentType: JSON){ resp ->
			println "Response status: ${resp.statusLine}"
			if(resp.statusLine.statusCode == 201) println 'Person created correctly'
			if(resp.statusLine.statusCode == 409) println 'Error while creating person: conflict'
			if(resp.statusLine.statusCode == 401) println 'Error while creating person: unauthorized'
		}
	}
	void updatePerson(id){
		HTTPBuilder http = new HTTPBuilder("http://141.66.8.240:8080")
		def putBody = [
					id : this.id,
					title : this.title,
					salutation : this.salutation,
					sureName : this.sureName,
					foreName : this.foreName,
					jobTitle : this.jobTitle,
					telephoneNumber : this.telephoneNumber,
					faxNumber : this.faxNumber,
					email : this.email
				]
		http.request(Method.PUT, JSON) {
			uri.path = '/aas/persons/'+id
			body = putBody
			response.success = {resp, json ->
				println resp.status
			if(resp.statusLine.statusCode == 201) println 'Person created correctly'
			if(resp.statusLine.statusCode == 409) println 'Error while creating person: conflict'
			if(resp.statusLine.statusCode == 401) println 'Error while creating person: unauthorized'
			}
		}
	}
	void deletePerson(id){
		http.request(Method.DELETE, JSON) {
			uri.path = '/aas/persons/'+id
			
			response.success = {resp, json ->
				println resp.status
			}
		}
	}
	
	void setCookieID(){
		def http = new HTTPBuilder("http://localhost:8080/ddb-registration/")
		def cookie = new BasicClientCookie("culo", "cane")
		http.client.cookieStore.addCookie cookie
	}
	static void main(def args){
		def test = new Person([id: "uid1342438318062", title: "Master Degree Sw Ing.", salutation: "Herr", sureName: "Emiliano", foreName: "Masi", telephoneNumber: "+49 888", faxNumber: "+49 777", email: "emiliano@masi.it"])
		def persons = test.getAllPersons();
		println persons.size()
		test.postPerson()
	}
}