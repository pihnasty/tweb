package beans;

import java.io.Serializable;
import java.util.Locale;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named ("locale")
@RequestScoped
public class LocaleChanger implements Serializable {

    private Locale currentLocale ;

    public LocaleChanger() {
        currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    public void changeLocale() {
        currentLocale = new Locale("en");
        System.out.println("public void changeLocale(String localeCode)");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));

    }

    public void changeLocaleR() {
        currentLocale = new Locale("ru");
        System.out.println("public void changeLocale(String localeCode)");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("ru"));

    }
    public Locale getCurrentLocale() {
        System.out.println("public Locale getCurrentLocale() ");
        return currentLocale;
    }


}
