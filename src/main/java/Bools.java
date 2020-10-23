
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;

public class Bools {
    public static boolean isAdmin(Message m) {
        if(m.getGuild().getMemberById(m.getAuthor().getId()).hasPermission(Permission.ADMINISTRATOR)){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isNull(Guild g) {
        try{
            g.getAfkChannel();
            System.out.println("this guild is not null");
            return false;

        }catch(Error e) {
            System.out.println("this guild is null");
            return true;
        }
    }
}