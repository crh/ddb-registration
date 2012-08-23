package org.escidoc.ddb.registration
import groovyx.net.http.*
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.URLENC
import groovy.json.*

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
	
	List getAllPersons() {
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
	Person getPerson(id){
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
			surName : this.sureName,
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
					surname : this.surname,
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
	static void main(def args){
		def test = new Person([id: "uid1342438318062", title: "Master Degree Sw Ing.", salutation: "Herr", sureName: "Emiliano", foreName: "Masi", telephoneNumber: "+49 888", faxNumber: "+49 777", email: "emiliano@masi.it"])
		def persons = test.getAllPersons();
		println persons.size()
		test.postPerson()
	}
}
