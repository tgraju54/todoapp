package todoapp

import Label.Tlabel
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class TlabelSpec extends Specification implements DomainUnitTest<Tlabel> {

    @Unroll('Tlabel.validate() with  label name: #value should have returned #expected with errorCode: #expectedErrorCode')
    void "test name validation"() {
        when:
        domain.lname = value

        then:
        expected == domain.validate(['lname'])
        domain.errors['lname']?.code == expectedErrorCode

        where:
        value                  | expected | expectedErrorCode
        'movie'                 |  true    | null
        'sea'                   |  true    | null
         null                   |  false   | 'nullable'
         'Zo'                    |  false   | 'minSize.notmet'


    }


}
