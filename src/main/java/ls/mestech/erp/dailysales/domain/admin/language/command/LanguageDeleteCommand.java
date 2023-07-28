

package ls.mestech.erp.dailysales.domain.admin.language.command;


public class LanguageDeleteCommand {
    private String languageCd;
    private String name;

    public LanguageDeleteCommand() {
    }

    public LanguageDeleteCommand(String language_cd, String name) {
        this.languageCd = language_cd;
        this.name = name;
    }

    public LanguageDeleteCommand(String language_cd) {
        this.languageCd = language_cd;
    }

    public String getLanguageCd() {
        return languageCd;
    }

    public void setLanguageCd(String languageCd) {
        this.languageCd = languageCd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
