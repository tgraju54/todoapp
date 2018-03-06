package todoapp

import Label.Tlabel
import Task.Task
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
@Secured(['ROLE_USER'])
@Transactional(readOnly = false)
class TaskController extends RestfulController {
    def getuserService
    def dateopService
    def emailstoreService

    TaskController(){
        super(Task)
    }
    static responseFormats = ['json']
    static allowedMethods = [create: "POST"] //Post only allowed

    def index() {

    }
    //display today's tasks
    def ttask() {
        Date datetd=new Date()
        String datetoday= dateopService.dateform(datetd)
        def uname=getuserService.getuser()
        def Todaytask=Task.findAllByTdurAndUser(datetoday,User.findAllByUsername(uname))
        render(contentType: "application/json") {
            ttasks(Todaytask) { Task b ->
                id b.id
                name b.name
                label b.label.lname
                status b.tstatus
                priority b.tpriority

            }
        }

    }
    //display all the task
    def atask(){

        def uname=getuserService.getuser()
        def altask=Task.findAllByUser(User.findAllByUsername(uname))
        render(contentType: "application/json") {
            atasks(altask) { Task b ->
                id b.id
                name b.name
                date b.tdur
                label b.label.lname
                status b.tstatus
                priority b.tpriority
            }
        }

    }
    //delete task
    def deltask()
    {   if(request.JSON.id) {
           def tam = Task.get(request.JSON.id)
           if (tam) {
              tam.delete()
              respond message: "Task Has Been Deleted", code: "success"
           } else {
              respond message: "Task not found", code: "error"
           }
        }else{
          respond message: "Select a task to delete", code: "error"
        }
    }
    //update task
    def updatetask(){
        if(request.JSON.id) {
            def upt = Task.get(request.JSON.id)

            if (upt) {
                if (upt.tstatus == "Complete") {
                    respond message: "Task already marked completed", code: "info"
                } else {
                    upt.tstatus = "Complete"
                    upt.save()
                    respond message: "Hurray! Task Completed ", code: "success"
                }
            } else {
                respond message: "Task not found", code: "error"
            }

        }else {
            respond message: "Select a task to update", code: "error"
        }
    }
    //save tasks
    def savetask(){
        def userid=getuserService.getuserid()
        if(request.JSON.name!=null){
            def Tnew=new Task(name:request.JSON.name,tdur:request.JSON.tdur,tpriority: request.JSON.tpriority,label:request.JSON.label.id,user:userid)
            if(Tnew.validate()) {
                Tnew.save()
                respond message: "Task Saved", code: "success"
            }else
            {
                respond message:Tnew.errors.toString(),code:"error"
            }
        }
        else {
            respond message: "Something went wrong ",code:"error"
        }
    }

    def chlabel()
    { if(request.JSON.taskno) {
        def taskla = Task.get(request.JSON.taskno)

        if (taskla) {
            def ulabel = Tlabel.get(request.JSON.newlabel)
            taskla.label = ulabel;
            taskla.save();
            respond message: "Task Updated", code: "success"
        } else {
            respond message: "Select a valid label ", code: "error"
        }
    }else {
        respond message:"Enter the taskno to change label",code:"error"
    }
    }
    def chpriority()
    { if(request.JSON.taskno) {
        def taskPri = Task.get(request.JSON.taskno)
        if (taskPri) {
            def npriority=request.JSON.npriority
            if(request.JSON.npriority) {
                taskPri.tpriority = npriority.toInteger();
                taskPri.save()
                respond message: "Task Updated", code: "success"
            }
            else{
                respond message: "Invalid Priority", code: "success"
            }

        } else {
            respond message: "Select a valid Priority ", code: "error"
        }
     }else {
         respond message:"Enter the taskno to change Priority",code:"error"
     }
    }

    def gettsa() {
        def userid = getuserService.getuserid()
        def tstatus = User.findAllById(userid)
        if(tstatus) {
            respond status: tstatus.tsa
        }
        else {
            respond message: "Something went wrong ",code:"error"
        }
    }

    def changetsastat()
    {
        def userid=getuserService.getuserid()
        def tstatus=User.get(userid)
        if(tstatus)
        {   if(tstatus.uphone==null){
            respond message:"Please add your phone number to activate this feature",code:"info"

        }else{
            if(tstatus.tsa==false)
            {
                tstatus.tsa=true
                tstatus.save()
                emailstoreService.activatetsa(tstatus.email,tstatus.uname)

            }
            else{
                tstatus.tsa=false
                tstatus.save()
                emailstoreService.deactivatetsa(tstatus.email,tstatus.uname)
            }

            respond message:"Settings Applied",code:"success"

        }}
        else {
            respond message: "Something went wrong ",code:"error"
        }
    }



}
