package Task

import Label.Tlabel
import grails.rest.Resource
import todoapp.User

class Task {
    String name
    Date tdate =new Date()
    String tdur
    String tstatus="Pending"
    Integer tpriority=0
    static belongsTo = [user:User, label:Tlabel]


    static constraints = {
        name nullable: false ,minSize: 3
        tdate nullable: true
        tstatus nullable: true
        tpriority nullable:false, range: 0..4
        tdur nullable: false
    }
    Task()
    {
        tdate =new Date()
    }
}
