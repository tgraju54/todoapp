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
class LabelFuncSpec extends GebSpec {
    @Shared RestBuilder rest = new RestBuilder()
    def setup() {
        def nuser= new User("username":"tgraju54","email":"tgraju54@gmail.com","uname":"priyanshu","password":"ohrtg312").save()
        def urole=new Role(authority: 'ROLE_USER')
        def gh=new Tlabel(lname:'Genral',user: 1).save()
        urole.save()
        UserRole.create(nuser,urole,true)
    }

    def cleanup() {
    }


    void 'Test Label Controller'(){

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

        when:'Test save label Function'
        response = rest.post("http://localhost:${serverPort}/api/label/savelabel") {
            accept('application/json')
            header('Authorization', "Bearer ${accessToken}")contentType('application/json')
            json {
                lname = 'sunday'

            }
        }
        then:
        response.json.code=="success"


        when:'Test save label Function'
        response = rest.post("http://localhost:${serverPort}/api/label/allabel") {
            accept('application/json')
            header('Authorization', "Bearer ${accessToken}")contentType('application/json')

        }
        then:
        response.json[2].lname=="sunday"

        when:'Test Delete label Function'
        response = rest.post("http://localhost:${serverPort}/api/label/dellabel") {
            accept('application/json')
            header('Authorization', "Bearer ${accessToken}")contentType('application/json')
            json {
                id=1

            }
        }
        then:
        response.json.code=="success"





    }


}
