package todoapp

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class DateopServiceSpec extends  Specification implements DataTest,ServiceUnitTest<DateopService>{

    void 'test date format'(){
        when:'define a date'
        def datef=new Date(118,00,27)
        def rdate=service.dateform(datef)
        then:'checking date format'
        rdate=='2018-01-27'
    }

}
