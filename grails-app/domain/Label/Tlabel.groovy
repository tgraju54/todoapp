package Label

import Task.Task
import grails.rest.Resource
import todoapp.User

class Tlabel {
    String lname
    static hasMany = [task:Task]
    static belongsTo = [user:User]

    static constraints = {
        lname nullable: false ,minSize: 3
    }
}
