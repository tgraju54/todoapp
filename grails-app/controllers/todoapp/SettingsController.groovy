package todoapp
import Task.Task
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
@Transactional(readOnly = false)
@Secured(['ROLE_USER'])

class SettingsController extends RestfulController {
    def getuserService
    def springSecurityService


    SettingsController(){
        super(Task)
    }
    static responseFormats = ['json']
    static allowedMethods = [create: "POST"] //Post only allowed
    def emailstoreService

    def index() { }

    //change email address
    def cemail(){
        def userid=getuserService.getuserid()
        def useremail=User.get(userid)
        if(useremail) {
            if(useremail.email!=request.JSON.email) {
                emailstoreService.changeemail(useremail.email, request.JSON.email, useremail.uname)
                useremail.email = request.JSON.email
                useremail.save()
                respond message: "Email id changed", code: "success"
            }
            else
            {
                respond message: "Please enter new email Address", code: "error"

            }
        }else{
            respond message :"Something went wrong ",code:"error"

        }
    }

    //deactivate the account
    def drec(){
        def userid=getuserService.getuserid()
        def seluser=User.get(userid)
        if(seluser) {
            emailstoreService.deactivateacc(seluser.email,seluser.uname)
            Collection<UserRole> userRoles = UserRole.findAllByUser(seluser);
            userRoles*.delete()
            seluser.delete()
            session.invalidate()
            respond message: "Account Deativated",code:"success"
        }
        else{

            respond message :"Something went wrong ",code:"error"
        }

    }

    //change the password
    def cpass(){
        def userid=getuserService.getuserid()
        def seluser=User.get(userid)
        if(seluser) {
            if(!springSecurityService.passwordEncoder.isPasswordValid(seluser.password, request.JSON.new, null))
            {
                if(springSecurityService.passwordEncoder.isPasswordValid(seluser.password, request.JSON.old, null)){
                    seluser.password = request.JSON.new
                    emailstoreService.changepass(seluser.email,seluser.uname)
                    respond message: "password changed",code:"success"
                } else {
                    respond message: "Wrong Current Password",code:"error"
                }
            }
            else{
                respond message: "Please Enter a new Password",code:"error"
            }



        }
        else {
            respond message :"Something went wrong ",code:"error"

        }


    }
    def cphone(){
        def userid=getuserService.getuserid()
        def userphone=User.get(userid)
        if(userphone) {
            if(userphone.uphone==null)
            {  if(request.JSON.pno) {
                userphone.uphone = request.JSON.pno
                userphone.save()
                emailstoreService.cphoneemail(userphone.email, userphone.uname, "New Phone Number Added")
                respond message: "New Phone Added", code: "success"
               }
                else {
                respond message: "Please add a valid phone number", code: "error"
               }
            }
            else {
                if(userphone.uphone!=request.JSON.pno) {
                    userphone.uphone = request.JSON.pno
                    userphone.save()
                    emailstoreService.cphoneemail(userphone.email, userphone.uname, "Phone Number Changed")
                    respond message: "Phone Number Changed", code: "success"
                }
                else{
                    respond message: "Phone Number Already added", code: "error"
                }
            }

        }else{
            respond message :"Something went wrong ",code:"error"

        }
    }
    def getnumber()
    {
        def userid=getuserService.getuserid()
        def userpdetails=User.get(userid)

        if(userpdetails) {
           respond message:userpdetails.uphone,code:"success"
        }
        else {
            respond message :"Something went wrong ",code:"error"
        }



    }
    def gettsa()
    {
        def userid=getuserService.getuserid()
        def userdetails=User.get(userid)

        if(userdetails) {
            respond tsa:userdetails.tsa,tsacurr:userdetails.tsacurr,code:"success",message:"fetched"
        }
        else {
            respond message :"Something went wrong ",code:"error"
        }



    }
    def activatetsa()
    {
        def userid=getuserService.getuserid()
        def usertsa=User.get(userid)

        if(usertsa) {
            usertsa.tsacurr=true
            usertsa.save()
            respond code:"success",message:"Two step authentication login flow activated"
        }
        else {
            respond message :"Something went wrong ",code:"error"
        }



    }
    def deactivatetsa()
    {
        def userid=getuserService.getuserid()
        def usertsa=User.get(userid)

        if(usertsa) {
            usertsa.tsacurr=false
            usertsa.save()
            respond code:"success",message:"Two step authentication login flow deactivated"
        }
        else {
            respond message :"Something went wrong ",code:"error"
        }



    }


}
