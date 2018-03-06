package todoapp
import Label.Tlabel
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
@Secured(['permitAll'])
class UserController extends RestfulController {
    UserController(){
        super(UserRole)
    }

    static responseFormats = ['json']
    static allowedMethods = [create: "POST"] //Post only allowed

    def index(){

    }
    def signup()
    {

    }

    def create() {

        def gy = new User(request.JSON) //get params
        if(gy.validate()){// validate credentials
            gy.save()
            def adminRole = Role.findOrSaveWhere(authority: 'ROLE_USER')
            UserRole.create gy, adminRole                   // default authority
            new Tlabel(user: gy.id, lname: 'Genral').save()//create default label
            respond gy
        }
        else { //respond errors

            if(gy.errors.hasFieldErrors("username")){
                respond (id:"error",errmsg:"Enter Unique Username")As JSON
            }
            if(gy.errors.hasFieldErrors("email")) {
                respond (id:"error",errmsg:"Enter Unique email") As JSON
            }



        }



    }

}
