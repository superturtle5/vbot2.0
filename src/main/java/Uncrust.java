
import net.dv8tion.jda.core.entities.Message;

public class Uncrust {
    public static void bul(Message m) {
        int num;
        String[] words = m.getContentRaw().split(" ");
        if(words[0].equalsIgnoreCase(Ref.prefix + "uncrusticalc") || words[0].equalsIgnoreCase(Ref.prefix + "uncrustable") && Poll.isNum(words[1], 0, 999999999)) {
            num = Integer.parseInt(words[1]);
            m.getChannel().sendMessage(num + " uncrustables will cost you: $" + (num * 1.25)).queue();

        }
    }
}