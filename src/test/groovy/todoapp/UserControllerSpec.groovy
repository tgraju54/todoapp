package todoapp

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import groovy.mock.interceptor.MockFor
import org.grails.datastore.gorm.bootstrap.support.InstanceFactoryBean
import org.junit.Test
import spock.lang.Specification
@SuppressWarnings('MethodName')

class UserControllerSpec extends  Specification implements DataTest,ControllerUnitTest<UserController> {

    def setupSpec() {
        mockDomain User
        mockDomain UserRole



    }



    void 'Controller get request security'(){
        when:
        request.method='GET'
        controller.create()

        then:
        response.status==405
    }
    void 'Controller create functionality success'(){
        when:

        request.method='POST'
        request.json='{"username":"tgraju543","email":"rtgs@gmail.com","password":"ohrtg312","uname":"Priyanshu"}'
        controller.create()

        then:
        response.status==200
        response.json.username=="tgraju543"
        response.json.errmsg==null
    }




}