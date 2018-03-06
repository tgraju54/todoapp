package todoapp

import Label.Tlabel
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

@Transactional(readOnly = false)
@Secured(['ROLE_USER'])

class LabelController extends RestfulController {
    def getuserService


    LabelController(){
        super(Tlabel)
    }

    static responseFormats = ['json']
    static allowedMethods = [create: "POST"] //Post only allowed

    def index() { }

    //display all label
    def allabel()
    {
        def uname=getuserService.getuser()
        def allabell=Tlabel.findAllByUser(User.findAllByUsername(uname))
        respond allabell
    }
    //delete a label
    def dellabel(){
      if(request.JSON.id) {
          def labeldel = Tlabel.get(request.JSON.id)
          if (labeldel) {
              labeldel.delete()
              respond message: "Deleted", code: "success"
          } else {
              respond message: "Select a valid label", code: "error"
          }
      }else {
          respond message:"Select label to delete",code:"error"
      }

    }
    // save a label
    def savelabel(){
        def userid=getuserService.getuserid()
        if(request.JSON.lname!=null) {
            def Tlab = new Tlabel(lname:request.JSON.lname ,user:userid)
            if(Tlab.validate()) {
                Tlab.save()
                respond message: "label saved", code: "success"
            }else{
                respond message: Tlab.errors.toString(), code: "error"
            }
        }
        else {
            respond message: "Please enter a label name ",code:"error"
        }
    }

}
