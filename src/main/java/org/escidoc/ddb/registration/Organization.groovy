package org.escidoc.ddb.registration
import groovyx.net.http.*
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.URLENC
import groovy.json.*

//TODO: Create method for the interpretation of the errors


class Organization{
	String id
	String displayName
	String abbreviation
	String description
	String legalStatus
	String category                                         
	//Address
	String street
	String houseIdentifier
	String addressSupplement
	String postalCode
	String city
	String country
	String latitude
	String longitude
	//
	String telephone
	String fax
	String email
	String url
	String logo
	String orgParent
	String created
	String modified
	String status
	
	void fillOrganizationData(Map orgData){
		this.id = orgData.id
		this.displayName = orgData.displayName
		this.abbreviation = orgData.abbreviation
		this.description = orgData.description
		this.legalStatus = orgData.legalStatus
		this.category = orgData.category
		this.street = orgData.address.street
		this.houseIdentifier = orgData.address.houseIdentifier
		this.addressSupplement = orgData.address.addressSupplement
		this.postalCode = orgData.address.postalCode
		this.city = orgData.address.city
		this.country = orgData.address.country
		this.latitude = orgData.address.latitude
		this.longitude = orgData.address.longitude
		this.telephone = orgData.telephone
		this.fax = orgData.fax
		this.email = orgData.email
		this.url = orgData.url
		this.logo = orgData.logo
		this.orgParent = orgData.orgParent
		this.created = orgData.created
		this.modified = orgData.modified
		this.status = orgData.status
	}
	
	List getAllOrganizations() {
		def http = new HTTPBuilder("http://141.66.8.240:8080")
		def res = []
		http.get( path: '/aas/organizations/', query: [id: 'httpbuilder']){ resp, json ->

			println resp.status
			println json.size()
			println json.oid
			json.oid.each{
				res.add(getOrganization(it))
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
	Organization getOrganization(id){
		def http = new HTTPBuilder("http://141.66.8.240:8080")
		Organization res = new Organization()
		http.get( path: '/aas/organizations/'+id, query: [id: 'httpbuilder']){ resp, json ->

			println resp.status
			println json
			res.fillOrganizationData(json)
			println "---------"
		}
		return res
	}
	void postOrganization(){
		def http = new HTTPBuilder("http://141.66.8.240:8080")
		def postBody = [
					id : this.id,
					displayName : this.displayName,
					abbreviation: this.abbreviation,
					description: this.description,
					legalStatus: this.legalStatus,
					category: this.category,
					address: [
						street: this.street,
						houseIdentifier: this.houseIdentifier,
						addressSupplement: this.addressSupplement,
						postalCode: this.postalCode,
						city: this.city,
						country: this.country,
						latitude: this.latitude,
						longitude: this.longitude
					],
					telephone : this.telephone,
					fax : this.fax,
					email : this.email,
					url: this.url,
					logo: this.logo,
					orgParent: this.orgParent,
					created: this.created,
					modified: this.modified,
					status: this.status
				]
		http.post(path: '/aas/organizations', body: postBody, requestContentType: JSON){ resp ->
			println "Response status: ${resp.statusLine}"
			if(resp.statusLine.statusCode == 201) println 'Organization created correctly'
			if(resp.statusLine.statusCode == 409) println 'Error while creating organization: conflict'
			if(resp.statusLine.statusCode == 401) println 'Error while creating organization: unauthorized'
		}
	}
	void updateOrganization(id){
		HTTPBuilder http = new HTTPBuilder("http://141.66.8.240:8080")
		def putBody = [
					id : this.id,
					displayName : this.displayName,
					abbreviation: this.abbreviation,
					description: this.description,
					legalStatus: this.legalStatus,
					category: this.category,
					address: [
						street: this.street,
						houseIdentifier: this.houseIdentifier,
						addressSupplement: this.addressSupplement,
						postalCode: this.postalCode,
						city: this.city,
						country: this.country,
						latitude: this.latitude,
						longitude: this.longitude
					],
					telephone : this.telephone,
					fax : this.fax,
					email : this.email,
					url: this.url,
					logo: this.logo,
					orgParent: this.orgParent,
					created: this.created,
					modified: this.modified,
					status: this.status
				]
		http.request(Method.PUT, JSON) {
			uri.path = '/aas/organizations/'+id
			body = putBody
			response.success = {resp, json ->
				println resp.status
				if(resp.statusLine.statusCode == 200) println 'Organization updated correctly'
				if(resp.statusLine.statusCode == 409) println 'Error while updating organization: conflict'
				if(resp.statusLine.statusCode == 401) println 'Error while updating organization: unauthorized'
				if(resp.statusLine.statusCode == 404) println 'Error while updating organization: not found'
			}
		}
	}
	/*
	void postOrganization(){
		def http = new HTTPBuilder("http://141.66.8.240:8080")
		def postBody = [
					id : '99900399',
					displayName : 'Masi and Co',
					abbreviation: '',
					description: '',
					legalStatus:'',
					category:'Archiv',
					address: [
						street: 'Eilper Strasse',
						houseIdentifier: '71-75',
						addressSupplement:'',
						postalCode: '58091',
						city: 'Hagen',
						country: 'de',
						latitude: '51.3474',
						longitude: '7.4904'
					],
					telephone : '+49 123',
					fax : '+49 123',
					email : 'emiliano@masi.it',
					url: 'http://www.historisches-centrum.de',
					logo: '',
					orgParent: '00001475',
					created: '2012-08-16T08:31:47+0200',
					modified: '2012-08-24T16:02:41+0200',
					status: 'pending'
				]
		http.post(path: '/aas/organizations', body: postBody, requestContentType: JSON){ resp ->
			println "Response status: ${resp.statusLine}"
			assert resp.statusLine.statusCode == 201
		}
	}
	*/
	void deleteOrganization(id){
		http.request(Method.DELETE, JSON) {
			uri.path = '/aas/organizations/'+id
			
			response.success = {resp, json ->
				println resp.status
			}
		}
	}
	Map getAddress(){
		Map address = [street:this.street, houseIdentifier:this.houseIdentifier, addressSupplement:this.addressSupplement, postalCode:this.postalCode, city:this.city, country:this.country, latitude:this.latitude, longitude:this.longitude]
		return address;
	}
	static void main(def args){
		def test = new Organization()
		def orgs = test.getAllOrganizations();
		//def orgs = test.updateOrganization("99900299,00001475")
		println orgs.size()
		//test.postOrganization()
	}
}