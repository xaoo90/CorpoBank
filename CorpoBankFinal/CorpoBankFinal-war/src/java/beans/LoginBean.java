package beans;
 
import dao.UzytkownikFacadeLocal;
import data.Data;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;
import model.UzytkownikInterface;
 

/**
 *
 * @author User
 */
@ManagedBean(name = "loginBean")
public class LoginBean implements Serializable {

    @EJB
    private UzytkownikFacadeLocal uzytkownikFacade;
    
    
    private String password;
    private String uname;

    public LoginBean() {
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getUname() {
        return uname;
    }
 
    public void setUname(String uname) {
        this.uname = uname;
    }
 
    public String loginProject() {
        System.out.println("LoginBean loginProject();");
        
        UzytkownikInterface user;
        
        user = uzytkownikFacade.login(uname, password);
        
        
        if(user == null){
            System.out.println("LoginBean loginProject(); user == null");
            return "toLoginPage";
        } else {
            System.out.println("LoginBean loginProject(); user != null");
            HttpSession session = Util.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", user.getImie() + " " + user.getNazwisko());
            session.setAttribute("rights", user.getUprawnienia());

            return "toAccountsPage";
        }        
    }
    
    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "login";
    }
        
}

 
    
    
 