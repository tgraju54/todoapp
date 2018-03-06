package todoapp

import Label.Tlabel
import Task.Task

//priyanshu kumar
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
@Transactional(readOnly = false)
class DashboardController {


    def index()
    {

    }

    def altask()
    {

    }

    def allabel()
    {

    }
    def settings()
    {

    }
    def graph()
    {

    }
    def authen()
    {

    }
    def loginservice()
    {

    }

}
