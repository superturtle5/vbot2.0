import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.entities.MessageReaction.ReactionEmote;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;

public class Poll {
    public static void vote(Message m) {
        String[] words = m.getContentRaw().split(" ");
        int num;
        String[] alpha = {"🇦", "🇧", "🇨", "🇩", "🇪", "🇫", "🇬", "🇭", "🇮", "🇯", "🇰", "🇱", "🇲", "🇳", "🇴", "🇵", "🇶", "🇷", "🇸", "🇹", "🇺", "🇼", "🇽", "🇾", "🇿"};
        if(m.getContentRaw().contains(Ref.prefix+"m ") || m.getContentRaw().contains(Ref.prefix+"multiple")) {
            if(isNum(words[1], 2, 22)) {
                num = Integer.parseInt(words[1]);
                for(int i = 0; i < num; i ++) {
                    m.addReaction(alpha[i]).queue();
                }
            } else
                m.getChannel().sendMessage("Please use `<m (number of choices, 2 - 10) (your poll)`").queue();
        }else if(m.getContentRaw().contains(Ref.prefix+"p ") ||m.getContentRaw().contains(Ref.prefix+"poll")) {
            m.addReaction("✅").queue();
            m.addReaction("🔶").queue();
            m.addReaction("❌").queue();
        }else if( m.getContentRaw().contains(Ref.prefix+"y ") ||m.getContentRaw().contains(Ref.prefix+"yesno")){
            m.addReaction("✅").queue();
            m.addReaction("❌").queue();
        }else if(m.getContentRaw().contains(Ref.prefix+"ap ") ||m.getContentRaw().contains(Ref.prefix+"apoll")) {
            m.addReaction("✅").queue();
            m.addReaction("🔶").queue();
            m.addReaction("❌").queue();
            Ref.votes.add(new Vote (m.getContentRaw(), m.getId()));
            m.delete().queue();
            m.getChannel().sendMessage(m.getContentRaw() + " &" + findVote(m.getId()).getIndex()).queue();
        } else if(words[0].equalsIgnoreCase(Ref.prefix + "result")) {
            Vote vote = findVote(words[1]);
            m.getChannel().sendMessage("**Here's what people said:**\n" + "Results of: __**" + vote.getVote() + "**__"
                    + "\n Yes: **" + vote.getYes() + "**" + "\n Maybe: **" + vote.getMaybe()
                    + "**\n No: **"+ vote.getNo() + "**").queue();
        } else if(m.getChannel().getId().equals("509375073958887424")) {
            m.addReaction("✅").queue();
            m.addReaction("🔶").queue();
            m.addReaction("❌").queue();

        }
        if(m.getContentRaw().equals("")) {
//			m.addReaction("🔺").queue();
//			m.addReaction("🔻").queue();
        }

    }
    public static void ree(GuildMessageReceivedEvent evt) {
        Message m = evt.getMessage();
        if(m.getContentRaw().split(" ")[0].equals("**🆙") && m.getAuthor().getId().equals("172002275412279296")) {
            m.delete();

        }


        if(m.getContentRaw().substring(0, 4).contains(Ref.prefix+"ap ") ||m.getContentRaw().contains(Ref.prefix+"apoll")) {
            m.addReaction("✅").queue(); m.addReaction("🔶").queue(); m.addReaction("❌").queue();
        }
    }
    static boolean isNum(String num, int min, int max) {
        boolean n = false;
        for(int i = min; i < max; i++) {
            if(num.equals(Integer.toString(i))) {
                n = true;
            }
        }
        if(n) {
            return true;
        } else {
            return false;
        }
    }
    static void rec(GuildMessageReactionAddEvent evt) {

        try {
            User u = evt.getUser();
            String m = evt.getReaction().getReactionEmote().getName();
            Message mm = Mhistory.fetch(evt.getMessageId());
            String[] words = mm.getContentRaw().split(" ");
            Vote vote = findVote(words[words.length - 1]);
            if(words[0].contains(Ref.prefix+"ap ") ||mm.getContentRaw().contains(Ref.prefix+"apoll")) {
            }	if(m.equals("✅")) {
                vote.addYes(evt.getUser());
            }else if (m.equals("🔶")) {
                vote.addMaybe(evt.getUser());
            }else if(m.equals("❌")) {
                vote.addNo(evt.getUser());
            }
            clear(mm, vote, evt.getReaction(), u);
        } catch(Exception e){
            System.out.println("we ran out of history :O {Poll: 90}");
        }

    }
    static void clear(Message mm, Vote vote, MessageReaction r, User u) {
        //String send = mm.getContentRaw() + " &" + vote.getIndex();
        r.removeReaction(u).queue();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mm.addReaction("✅").queue(); mm.addReaction("🔶").queue(); mm.addReaction("❌").queue();
        //mm.getChannel().sendMessage(mm.getContentRaw()).queue();
    }
    public static Vote findVote(String id) {
        List<Vote> votes = Ref.votes;
        for(Vote vote: votes) {
            if(vote.getId().equals(id)) {
                return vote;
            }
        }
        int i = Integer.parseInt(id.substring(1));
        for(Vote vote: votes) {
            if(vote.getIndex() == i) {
                return vote;
            }
        }
        return null;
    }
}