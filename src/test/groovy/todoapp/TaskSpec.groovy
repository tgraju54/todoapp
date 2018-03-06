package todoapp

import Task.Task
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class TaskSpec extends Specification implements DomainUnitTest<Task> {


    @Unroll('Task.validate() with  label name: #value should have returned #expected with errorCode: #expectedErrorCode')
    void "test name validation"() {
        when:
        domain.name = value

        then:
        expected == domain.validate(['name'])
        domain.errors['name']?.code == expectedErrorCode

        where:
        value                  | expected | expectedErrorCode
        'playing'              |  true    | null
        'sea'                  |  true    | null
        null                   |  false   | 'nullable'
        'Zo'                   |  false   | 'minSize.notmet'


    }
    @Unroll('Task.validate() with  priority name: #value should have returned #expected with errorCode: #expectedErrorCode')
    void "test priority validation"() {
        when:
        domain.tpriority = value

        then:
        expected == domain.validate(['tpriority'])
        domain.errors['tpriority']?.code == expectedErrorCode

        where:
        value                  | expected | expectedErrorCode
        0                      |  true    | null
        4                      |  true    | null
        null                   |  false   | 'nullable'
        5                      |  false  | 'range.toobig'
        -2                      |  false  | 'range.toosmall'


    }


}
