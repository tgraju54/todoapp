package todoapp

import grails.gorm.transactions.Transactional

@Transactional
class EmailstoreService {

    def semail="todoapp@rtgstudios.in"

    def changeemail(old_email,new_email,name){
        def sub="Dear"+name+ "Your Email has been changed"
        def htmmail="<h1><center> Hi "+name+"</h1></center><center><br><br> your email has been changed to<b>&nbsp;&nbsp;"+new_email+"<br><br>"

        sendMail {
            to old_email
            from semail
            subject "Email Changed"
            html htmmail
        }
    }
    def changepass(email,name){
        def sub="Dear"+name+ "Your Password has been changed"
        def htmmail="<h1><center> Hi "+name+"</h1></center><center><br><br> your password  has been changed  and your todoapp account is fully secured<b>"

        sendMail {
            to email
            from semail
            subject "Password Changed"
            html htmmail
        }
    }
    def deactivateacc(email,name){
        def sub="Dear"+name+ "Your Account has been Deleted"
        def htmmail="<h1><center> Hi "+name+"</h1></center><center><br><br> your account  has been deleted  please signup again to continue using our services<b>"

        sendMail {
            to email
            from semail
            subject "Account Deleted"
            html htmmail
        }
    }
    def activatetsa(email,name){
        def sub="Dear"+name+ "Two Step Authntication has been activated on your account"
        def htmmail="<h1><center> Hi "+name+"</h1></center><center><br><br> Two Step authentication has been applied to your account<b>"

        sendMail {
            to email
            from semail
            subject "Two Step authentication"
            html htmmail
        }
    }
    def deactivatetsa(email,name){
        def sub="Dear"+name+ "Two Step Authntication has been deactivated on your account"
        def htmmail="<h1><center> Hi "+name+"</h1></center><center><br><br> Two Step authentication has been successfully deactivated on your account<b>"

        sendMail {
            to email
            from semail
            subject "Two Step authentication"
            html htmmail
        }
    }
    def cphoneemail(email,name,message){
        def sub="Dear"+name+ message
        def htmmail="<h1><center> Hi "+name+"</h1></center><center><br><br>"+message+ "&nbsp;successfully <b>"

        sendMail {
            to email
            from semail
            subject message
            html htmmail
        }
    }


}
