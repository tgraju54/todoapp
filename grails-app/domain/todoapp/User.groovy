package todoapp

import Label.Tlabel
import Task.Task
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String uname
    String email
    Long   uphone
    boolean tsa=false
    boolean tsacurr=false
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    static  hasMany = [task:Task, llabel:Tlabel]

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true ,minSize: 8
        username nullable: false, blank: false, unique: true ,minSize: 3
        uname nullable: false,minSize: 2
        email nullable: false,unique: true, email: true
        uphone nullable: true
        tsa nullable:true
        tsacurr nullable:true
    }

    static mapping = {
	    password column: '`password`'
    }
}
