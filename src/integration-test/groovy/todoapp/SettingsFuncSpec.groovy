package todoapp

import Label.Tlabel
import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration
import grails.transaction.*

import spock.lang.*
import geb.spock.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback
class SettingsFuncSpec extends GebSpec {
    @Shared RestBuilder rest = new RestBuilder()
    def setup() {
        def nuser= new User("username":"tgraju54","email":"tgraju54@gmail.com","uname":"priyanshu","password":"ohrtg312").save()
        def urole=new Role(authority: 'ROLE_USER')
        urole.save()
        UserRole.create(nuser,urole,true)


    }

    def cleanup() {
    }

 void'Testing Settings Controller'()
 {
     when:'Testing Login Creating Access tokens '
     def response = rest.post("http://localhost:${serverPort}/api/login") {
         accept('application/json')
         contentType('application/json')
         json {
             username = 'tgraju54'
             password = 'ohrtg312'

         }
     }
     then:

     response.status==200

     when:'Check Access Token'
     def accessToken = response.json.access_token

     then:
     accessToken



     when:'settings change email'
     response = rest.post("http://localhost:${serverPort}/api/settings/cemail") {
         accept('application/json')
         header('Authorization', "Bearer ${accessToken}")contentType('application/json')
         json {
            email = 'tgkanishka54@gmail.com'

         }
     }
     then:
     response.json.code=="success"

     when:'settings change phone'
     response = rest.post("http://localhost:${serverPort}/api/settings/cphone") {
         accept('application/json')
         header('Authorization', "Bearer ${accessToken}")contentType('application/json')
         json {
            pno=919661065757

         }
     }
     then:
     response.json.code=="success"

     when:'settings get phonenumber'
     response = rest.post("http://localhost:${serverPort}/api/settings/getnumber") {
         accept('application/json')
         header('Authorization', "Bearer ${accessToken}")contentType('application/json')

     }
     then:
     response.json.message==919661065757

     when:'Test activate two step authentication'
     response = rest.post("http://localhost:${serverPort}/api/settings/activatetsa") {
         accept('application/json')
         header('Authorization', "Bearer ${accessToken}")contentType('application/json')

     }
     then:
     response.json.code=="success"


     when:'Test deactivate two step authentication'
     response = rest.post("http://localhost:${serverPort}/api/settings/activatetsa") {
         accept('application/json')
         header('Authorization', "Bearer ${accessToken}")contentType('application/json')

     }
     then:
     response.json.code=="success"

     when:'Test get two step authentication current value'
     response = rest.post("http://localhost:${serverPort}/api/settings/gettsa") {
         accept('application/json')
         header('Authorization', "Bearer ${accessToken}")contentType('application/json')

     }
     then:
     response.json.code=="success"



 }


}
