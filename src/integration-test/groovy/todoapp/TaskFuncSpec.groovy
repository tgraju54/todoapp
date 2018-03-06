package todoapp

import Label.Tlabel
import Task.Task
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.testing.mixin.integration.Integration
import grails.transaction.*
import org.junit.Test
import spock.lang.*
import geb.spock.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback

class TaskFuncSpec extends GebSpec {
    def springSecurityService
    def GetuserService
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

    void 'test TaskController rest  functions'(){

        when:'Testing Login Functionality'
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

        when:'Test Save Task Function'
        response = rest.post("http://localhost:${serverPort}/api/task/savetask") {
            accept('application/json')
            header('Authorization', "Bearer ${accessToken}")contentType('application/json')
            json {
                name = 'tgraju54'
                tdur = '02-02-2018'
                tpriority=1
                label{id=1}
            }
        }
        then:
        response.json.code=="success"

        when:'Test updatetask function'
        response = rest.post("http://localhost:${serverPort}/api/task/updatetask") {
            accept('application/json')
            header('Authorization', "Bearer ${accessToken}")contentType('application/json')
            json {
                id=1
            }
        }
        then:
        response.json.code=="success"

        when:'Test updatetask function'
        response = rest.post("http://localhost:${serverPort}/api/task/chlabel") {
            accept('application/json')
            header('Authorization', "Bearer ${accessToken}")contentType('application/json')
            json {
                taskno=1
                newlabel=1
            }
        }
        then:
        response.json.code=="success"

        when:'Test todays task function'
        response = rest.post("http://localhost:${serverPort}/api/task/ttask") {
            accept('application/json')
            header('Authorization', "Bearer ${accessToken}")contentType('application/json')

        }
        then:
        response.json.ttasks==[]

        when:'Test all task function'
        response = rest.post("http://localhost:${serverPort}/api/task/atask") {
            accept('application/json')
            header('Authorization', "Bearer ${accessToken}")contentType('application/json')

        }
        then:
        response.json.atasks.name.toString()=="[tgraju54]"

        when:'Test deletetask function'
        response = rest.post("http://localhost:${serverPort}/api/task/deltask") {
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



