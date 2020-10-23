import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.core.entities.Message;

public class Count {
    public static void num(Message m) {
        boolean lookAtIt = true;
        //if(!Bools.isAdmin(m)) {
        if(m.getContentRaw().equalsIgnoreCase(Ref.prefix + "c reset")) {
            Ref.prev = null;
            m.getChannel().sendMessage("I will now stop being mean and deleting your messages ( ͡° ͜ʖ ͡°)").queue();
        }
        if(!Poll.isNum(m.getContentRaw(), 0, 99999) && m.getChannel().getName().equalsIgnoreCase("counting")) {
            m.delete().queueAfter(2, TimeUnit.SECONDS);
        }
        //cSystem.out.println(Ref.prev);
        if(Poll.isNum(m.getContentRaw(), 0, 99999) && m.getChannel().getName().equalsIgnoreCase("counting")) {
            if(Ref.prev != null) {
                System.out.println(m.getContentRaw() + "=?" + (Integer.parseInt(Ref.prev.getContentRaw()) + 1) + "");
                if(Integer.parseInt(m.getContentRaw()) != Integer.parseInt(Ref.prev.getContentRaw()) + 1) {
                    m.delete().queue();
                    Ref.main.getTextChannelsByName("general", true).get(0).sendMessage(m.getAuthor().getAsMention() + " you can't count").queue();
                    lookAtIt = false;
                }else {

                }
            }
            //System.out.println(Ref.prev);
            if(lookAtIt) {
                Ref.prev = m;
            }

        }
        //}
    }
}