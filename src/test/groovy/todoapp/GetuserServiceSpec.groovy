package todoapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import groovy.mock.interceptor.MockFor


import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import groovy.sql.DataSet
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Shared
import spock.lang.Specification

@SuppressWarnings('MethodName')



class GetuserServiceSpec extends Specification implements DataTest, ServiceUnitTest<GetuserService>{

    @Shared User nuser

    def setupSpec() {
        mockDomain User
        mockDomain UserRole

    }

    def setup() {
        nuser=new User(uname: "priyansu",password: "ohrtg312",username:"tgraju54",email:"tgraju54@gmail.com")
        nuser.save()
    }

    void 'test get userservice checkuser function'(){
        when:'send username to function'
        def cuser=service.checkuser("tgraju54")
        then:'check weather user valid or not'
        cuser==2


    }




}
