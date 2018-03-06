package todoapp

import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat
//format the date
@Transactional
class DateopService {

    def dateform(fdate)
    {
       def nfdate= new SimpleDateFormat("yyyy-MM-dd").format(fdate)
        return nfdate
    }
}
