package todoapp

import Task.Task
import grails.gorm.transactions.Transactional
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

@Transactional
class ProductivityService {
    def dateopService
    def ecal(dlist){
    ArrayList<Float> eff= new ArrayList<Float>()
    int ic=0

    int ct=0

    float yt=0
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uname = auth.getName();
    for(int i=0;i<7;i++) {
        ic=0
        ct=0
        String datetoday = dateopService.dateform(dlist[i])
        def tefo = Task.findAllByTdurAndUser(datetoday,User.findAllByUsername(uname))
        tefo.each {gtask ->
            if (gtask.tstatus == "Pending") {
                ic++

            }
            if (gtask.tstatus == "Complete") {
                ct++
                ic++
            }}
        if(ic==0)
        {
            yt =0;
        }
        else {
            yt = ct / ic
        }
        eff.add(yt)
    }
    return   eff

}

}



