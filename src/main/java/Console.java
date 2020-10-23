import java.util.List;
import java.util.Scanner;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;


public class Console {

    public static void see(Message comand) {
        if(comand.getGuild().equals(Ref.main) && comand.getChannel().getId().equals("566623142555156480")) {
            String[] sCom = comand.getContentRaw().split(" ");
            String command = comand.getContentRaw();
            Member tar;
            MessageChannel c = comand.getChannel();
            System.out.println(sCom[1]);

            try {
                tar = Ref.bigMain.getMembersByNickname(sCom[1], true).get(0);
                System.out.println(tar);

            }catch(Exception e) {
                tar = null;
            }
            //mute elsir
            tar = memByName(Ref.bigMain, command.substring(command.indexOf(' '), command.length()));
            System.out.println(tar);

            if(sCom[0].equals("say")) {
                Ref.bigMain.getTextChannelsByName(sCom[1], true).get(0).sendMessage(combine(sCom)).queue();
                send(("sent " + combine(sCom)+ " to " + sCom[1]), c);

            }
            else if(sCom[0].equals("echo")) {
                c.sendMessage(combine(sCom)).queue();
            }
            else if(sCom[0].equals("admin")) {
                //Ref.bigMain.getController().createRole().setPermissions("8")
                System.out.println(Ref.bigMain.getRoleById("675441619381780494"));
                //Ref.bigMain.getRoleById("726121186479702041")
                Ref.bigMain.getController().addSingleRoleToMember(tar,Ref.bigMain.getRoleById("566733067952193586")).queue();
            }
            else if(sCom[0].equals("mute")) {
                Ref.bigMain.getController().setMute(tar, true).queue();
                send(("muted "+ tar.getNickname()), c);
            }
            else if(sCom[0].equals("unmute")) {
                Ref.bigMain.getController().setMute(tar, false).queue();
                send(("unmuted "+ tar.getNickname()), c);

            }
            else if(sCom[0].equals("deaf")) {
                Ref.bigMain.getController().setDeafen(tar, true).queue();
                send(("deafened "+ tar.getNickname()), c);

            }
            else if(sCom[0].equals("kill")) {
                Role dead = Ref.bigMain.getRolesByName("Dead", true).get(0);
                Hello.kill(tar, dead,Ref.bigMain.getTextChannelsByName(sCom[2], true).get(0), Ref.bigMain.getController());
                send(("deafened "+ tar.getNickname()), c);

            }
            //				 ;

            else if(sCom[0].equals("undeaf")) {
                Ref.bigMain.getController().setDeafen(tar, false).queue();
                send(("undeafened "+ tar.getNickname()), c);

            }
            else if(sCom[0].equals("kick")) {
                Ref.bigMain.getController().kick(tar).queue();
                send(("kicked "+ tar.getNickname()), c);

            }
            else if(sCom[0].equals("ban")) {
                Ref.bigMain.getController().ban(tar, 0).queue();
                send(("banned "+ tar.getNickname()), c);

            }
            else if(sCom[0].equals("create")) {
                Ref.bigMain.getController().createTextChannel(command.substring(command.indexOf(' '), command.length())).queue();
                send(("created "+ sCom[1]), c);

            }
            else if(sCom[0].equals("createV")) {
                Ref.bigMain.getController().createVoiceChannel(command.substring(command.indexOf(' '), command.length())).queue();
                send(("created voice channel "+ sCom[1]), c);

            }
            else if(sCom[0].equals("move")) {
                Ref.bigMain.getController().moveVoiceMember( memByName(Ref.bigMain, tar.getNickname().substring(command.indexOf(' '), command.length())), Ref.bigMain.getVoiceChannelsByName(sCom[1], true).get(0)).queue();
                send(("moved "+ tar.getNickname() + " to " + sCom[1]), c);

            }
            else if(sCom[0].equals("del")) {
                Mhistory.fetch(sCom[1]).delete().queue();
                send(("deleted"), c);

            }

        }
    }
    static String combine(String[] words) {
        String fin = "";
        words[0] = "";
        words[1] = "";
        for (String word : words) {
            fin+=(" " + word);
        }
        return fin;
    }
    static void send(String s, MessageChannel c) {
        c.sendMessage(s).queue();
    }
    public static Member memByName(Guild s, String name) {
        List<Member> memLs = s.getMembers();
        String realName = "";
        Member match = null;
        for(Member tarMem : memLs) {
            if(tarMem.getNickname() != null) {
                realName = tarMem.getNickname();
            }else {
                realName = tarMem.getUser().getName();
            }
            System.out.println("<- " + realName + " =? " + name + " ->" );
            if(realName.trim().equalsIgnoreCase(name.trim())) {
                match = tarMem;
                return match;
            }

        }
        System.out.println(match);
        return match;
    }

}
