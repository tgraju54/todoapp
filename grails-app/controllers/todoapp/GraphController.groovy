package todoapp

import Task.Task
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
@Transactional(readOnly = false)
@Secured(['ROLE_USER'])

class GraphController extends RestfulController {
    def productivityService
    GraphController(){
        super(Task)
    }
    //get graphs days data
    static responseFormats = ['json']
    static allowedMethods = [create: "POST"] //Post only allowed

    def index() {
        def datew=new Date()
        ArrayList<Date>dlist= new ArrayList<Date>()
        def x=datew.getDay()
        datew=datew-x;
        for(int i=0;i<7;i++) {
            dlist.add(datew+i)
        }
        def grdata=productivityService.ecal(dlist)
        respond grdata
    }
}
