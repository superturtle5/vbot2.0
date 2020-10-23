import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.channel.priv.PrivateChannelCreateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;


public class Hello extends ListenerAdapter {
    public static void hi(Message m) {


        //System.out.println(Mhistory.fetch(m.getId()));
        String[] words  = m.getContentRaw().split(" ");
        // System.out.println(Ref.votes);
        if(words[0].equalsIgnoreCase(Ref.prefix+"hello")
                || words[0].equalsIgnoreCase(Ref.prefix+"hi")
                || words[0].equalsIgnoreCase(Ref.prefix+"sup")
                || words[0].equalsIgnoreCase(Ref.prefix+"yo")
                || words[0].equalsIgnoreCase(Ref.prefix+"good moring")) {
            m.getChannel().sendMessage("Greetings!").queue();

        }
        if(m.getGuild().getTextChannelById("482384287513968665") != null) {
            Ref.here = m.getGuild().getTextChannelById("482384287513968665");
        } if(m.getContentRaw().equalsIgnoreCase(Ref.prefix + "die")) {
            m.getChannel().sendMessage("Beatings!").queue();
        } if(m.getContentRaw().equalsIgnoreCase(Ref.prefix + "spam100 21234")) {
            m.delete().queue();
            for(int I = 0; I <= 100; I++) {
                m.getChannel().sendMessage("spam: " + I).queue();
            }
        }

        if(words[0].equalsIgnoreCase(Ref.prefix + "unkill")) {
            Role dead = m.getGuild().getRolesByName("Dead", true).get(0);
            Member tar = m.getMentionedMembers().get(0);
            m.getGuild().getController().removeSingleRoleFromMember(tar, dead).queue();
            m.getChannel().sendMessage("Fine- ill revive them... for now").queue();
        }
        if(words[0].equalsIgnoreCase(Ref.prefix + "kill")) {
            Role dead = m.getGuild().getRolesByName("Dead", true).get(0);
            Member tar = m.getMentionedMembers().get(0);
            if(Bools.isAdmin(m) || m.getId().equals("305503078889684993")) {
                kill(tar, dead, m.getTextChannel(), m.getGuild().getController());

            }else if(m.getMember().getRoles().contains(dead)){
                m.getChannel().sendMessage("You are dead. Dead people can't kill people").queue();
            }else if(tar.getRoles().contains(dead)) {
                m.getChannel().sendMessage("They're already dead- should I kill you instead? hmmmm... i'll spare you for now").queue();
            }else if(tar.getUser().getId().equals("352567610379862026")) {
                m.getChannel().sendMessage("Dont worry i'll kill Preston so he cant abuse his admin").queue();
                kill(tar, dead, m.getTextChannel(), m.getGuild().getController());
            }else if(tar.getUser().getId().equals("305503078889684993")) {
                m.getChannel().sendMessage("Gladly!").queue();
                kill(tar, dead, m.getTextChannel(), m.getGuild().getController());
            }else if(tar.getUser().getId().equals("387784979578486785")) {
                m.getChannel().sendMessage("I've always wanted to kill them!").queue();
                kill(tar, dead, m.getTextChannel(), m.getGuild().getController());
            }

            else {
                m.getChannel().sendMessage("You do not have permission to use this comand. You will now be exterminated >").queue();
                kill(m.getMember(), dead, m.getTextChannel(), m.getGuild().getController());

            }


        }
    }

    public static void kill(net.dv8tion.jda.core.entities.Member m, Role d, TextChannel c, GuildController ch) {

        ch.addSingleRoleToMember(m, d).queue();
        ch.removeSingleRoleFromMember(m, d).queueAfter(5, TimeUnit.MINUTES);
        try {
            if(m.getUser().getId().equals("352567610379862026")) {
                Role g = m.getGuild().getRolesByName("Hand", true).get(0);
                ch.removeSingleRoleFromMember(m, g).queue();
                ch.addSingleRoleToMember(m, g).queueAfter(5, TimeUnit.MINUTES);
            }

        }catch (Exception e){

        }
        c.sendMessage("<@!" + m.getUser().getId() + "> has been terminated").queue();
    }
    public static void t(PrivateChannelCreateEvent evt) {
        System.out.println("hello");
        evt.getChannel().sendMessage("hi").queue();
        evt.getChannel().close().queue();

    }


}
