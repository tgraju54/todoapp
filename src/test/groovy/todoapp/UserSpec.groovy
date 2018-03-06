package todoapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class UserSpec extends Specification implements DomainUnitTest<User> {
    @Unroll('User.validate() with email: #value should have returned #expected with errorCode: #expectedErrorCode')
    void "test email validation"() {
        when:
        domain.email = value

        then:
        expected == domain.validate(['email'])
        domain.errors['email']?.code == expectedErrorCode

        where:
        value                  | expected | expectedErrorCode
        'priyanshu@gmail.com'  |  true    | null
        'priyanshu'            |  false   | 'email.invalid'
         null                  |  false   | 'nullable'

    }

    @Unroll('User.validate() with username: #value should have returned #expected with errorCode: #expectedErrorCode')
    void "test username validation"() {
        when:
        domain.username = value

        then:
        expected == domain.validate(['username'])
        domain.errors['username']?.code == expectedErrorCode

        where:
        value                  | expected | expectedErrorCode
        'priyan'               |  true    | null
        'pri'                  |  true    | null
         null                  |  false   | 'nullable'
        'pr'                   |  false    | 'minSize.notmet'


    }
    @Unroll('User.validate() with password: #value should have returned #expected with errorCode: #expectedErrorCode')
    void "test password validation"() {
        when:
        domain.password = value

        then:
        expected == domain.validate(['password'])
        domain.errors['password']?.code == expectedErrorCode

        where:
        value                  | expected | expectedErrorCode
        'ohrtg312'             |  true    | null
        'priyanshupass'        |  true    | null
         null                  |  false   | 'nullable'
        'priyan'               |  false   | 'minSize.notmet'


    }
    @Unroll('User.validate() with name: #value should have returned #expected with errorCode: #expectedErrorCode')
    void "test name validation"() {
        when:
        domain.uname = value

        then:
        expected == domain.validate(['uname'])
        domain.errors['uname']?.code == expectedErrorCode

        where:
        value                  | expected | expectedErrorCode
        'Priyanshu'            |  true    | null
        'Pk'                   |  true    | null
        null                   |  false   | 'nullable'
        'Z'                    |  false   | 'minSize.notmet'


    }

    @Unroll('User.validate() with phone nuber: #value should have returned #expected with errorCode: #expectedErrorCode')
    void "test phone validation"() {
        when:
        domain.uphone = value

        then:
        expected == domain.validate(['uphone'])
        domain.errors['uphone']?.code == expectedErrorCode

        where:
        value                  | expected | expectedErrorCode
        19661065757            |  true    | null
        919661065757           |  true    | null
        null                   |  true    | null



    }

    def "User's username unique constraint"() {

        when: 'You instantiate a User with username and an email address which has been never used before'
        def vuser = new User(uname: "priyansu",password: "ohrtg312",username:"tgraju54",email: "tgraju54@gmail.com")

        then: 'vuser is valid instance'
        vuser.validate()

        and: 'saving the user'
        vuser.save()

        and: 'additional user'
        User.count() == old(User.count()) + 1

        when: 'instanting user with same username'
        def cuser = new User(uname: "priyansu",password: "ohrtg312",username:"tgraju54",email: "tgraju54@gmail.com")

        then: 'the cuser instance is not valid'
        !cuser.validate(['username'])

        and: 'unique error code is populated'
        cuser.errors['username']?.code == 'unique'

        and: ' save fails'
        !cuser.save()

        and: 'no User has been added'
        User.count() == old(User.count())
    }
    def "User's email unique constraint"() {

        when: 'You instantiate a User with username and an email address which has been never used before'
        def euser = new User(uname: "priyansu",password: "ohrtg312",username:"tgraju54",email: "tgraju54@gmail.com")

        then: 'vuser is valid instance'
        euser.validate()

        and: 'saving the user'
        euser.save()

        and: 'additional user'
        User.count() == old(User.count()) + 1

        when: 'instanting user with same username'
        def guser = new User(uname: "priyans",password: "ohrtg312",username:"tgraju54",email: "tgraju54@gmail.com")

        then: 'the cuser instance is not valid'
        !guser.validate(['email'])

        and: 'unique error code is populated'
        guser.errors['email']?.code == 'unique'

        and: ' save fails'
        !guser.save()

        and: 'no User has been added'
        User.count() == old(User.count())
    }


}
