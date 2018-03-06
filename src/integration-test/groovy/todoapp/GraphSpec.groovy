package todoapp

import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration
import grails.transaction.*


import spock.lang.Specification

@Integration
@Rollback
class GraphSpec extends Specification {
    def springSecurityService
    def ProductivityService
    def setup() {
        def nuser= new User("username":"tgraju54","email":"tgraju54@gmail.com","uname":"priyanshu","password":"ohrtg312").save()
        def urole=new Role(authority: 'ROLE_USER')
        urole.save()
        UserRole.create(nuser,urole,true)
    }

    def cleanup() {
    }

    void "Task create"() {

        when:
        setup()
        def gy=User.findAllByUsername("tgraju54")
        springSecurityService.reauthenticate("tgraju54")
        def datew=new Date()
        ArrayList<Date>dlist= new ArrayList<Date>()
        def x=datew.getDay()
        datew=datew-x;
        for(int i=0;i<7;i++) {
            dlist.add(datew+i)
        }
        def grdata=productivityService.ecal(dlist)

        then:

        grdata.toString()=='[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]'








    }
}
