import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Trivia {
    public static void guess(MessageReceivedEvent evt) {
        String[] words = evt.getMessage().getContentRaw().split(" ");
        String answere = "";
        if(words[0].equalsIgnoreCase(Ref.prefix + "Submit")) {
            Ref.answeres.add(evt.getMessage());
            evt.getChannel().sendMessage(evt.getAuthor().getAsMention() + " your respose has been recorded").queue();;
        }
        if(words[0].equalsIgnoreCase(Ref.prefix + "result")) {
            if(evt.getGuild().getMemberById(evt.getAuthor().getId()).hasPermission(Permission.ADMINISTRATOR)) {
                for(int i = 0; i < Ref.answeres.size(); i++) {
                    answere = Ref.answeres.get(i).getContentRaw() + " | " + Ref.answeres.get(i).getAuthor().getName() + "\n ";
                }
                evt.getChannel().sendMessage("**Here's what people said: ** \n" + answere).queue();
            } else {
                evt.getChannel().sendMessage("You must be an admin to do that!").queue();
            }
        }
    }
}