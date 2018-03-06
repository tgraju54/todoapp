package todoapp

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.Specification

@Integration
@Rollback
class UserServiceSpec extends Specification {
    def springSecurityService
    def GetuserService
    def setup() {
        new User(username:"tgraju54",email:"tgraju54@gmail.com",password:"ohrtg312",uname:"priyanshu").save()
        new User(username:"tgkan54",email:"tgraju@gmail.com",password:"ohrtg3122",uname:"kanishka").save()
    }

    def cleanup() {
    }
    void' test for get username'(){
        when:
        setup()
        springSecurityService.reauthenticate("tgraju54")
        def gy=GetuserService.getuser()
        then:
        gy.toString()=="tgraju54"

    }
    void 'test for username failure'(){
        when:
        setup()
        springSecurityService.reauthenticate("tgraju54")
        def gy=GetuserService.getuser()
        then:
        gy.toString()!="tgkan54"


    }
    void 'test for getuserid'(){
        when:
        setup()
        springSecurityService.reauthenticate("tgraju54")
        def gy=GetuserService.getuserid()
        then:
        gy.toString()=="[1]"


    }
    void 'test for  not  user userid'(){
        when:
        setup()
        springSecurityService.reauthenticate("tgraju54")
        def gy=GetuserService.getuserid()
        then:
        gy.toString()!="[2]"


    }


}
