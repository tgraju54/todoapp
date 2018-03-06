package todoapp

import grails.gorm.transactions.Transactional
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
//for user related services
@Transactional
class GetuserService {

  def getuser()
  {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String uname = auth.getName();
      return uname
  }
    def getuserid()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uname = auth.getName()
        def user=User.findAllByUsername(uname)
        return user.id
    }
    def checkuser( String usernamee)
    {
        def useobj=User.findAllByUsername(usernamee)
        if(useobj)
        {
            return 2
        }
        else
        {
            return 3
        }

    }
}
