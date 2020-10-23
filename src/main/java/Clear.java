import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Clear {
    public static void logClear(GuildMessageReceivedEvent evt) {
        String[] words = evt.getMessage().getContentRaw().split(" ");
        int num;
        List<Message> cHistory = new ArrayList<Message>();
        if(words[0].equalsIgnoreCase("!clear") && Poll.isNum(words[1], 1, 200)) {
            for(int i = 0; i < Mhistory.hs.length; i++) {
                if(Mhistory.hs[i] != null){
                    if(Mhistory.hs[i].getChannel().equals(evt.getChannel())) {
                        cHistory.add(Mhistory.hs[i]);
                    }
                }
            }
            num = Integer.parseInt(words[1]);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
            Date date = new Date (System.currentTimeMillis());
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle(evt.getAuthor().getName() + " has cleared " + words[1] + " message(s) in "+ evt.getChannel().getName());
            builder.setColor(Color.decode("#1e90ff"));
            builder.setDescription(sdf.format(date)+ " at " + stf.format(date));
            builder.setAuthor(evt.getAuthor().getName(), evt.getAuthor().getAvatarUrl(), evt.getAuthor().getAvatarUrl());
            builder.setThumbnail("https://cdn.glitch.com/7c1e6bd9-6327-4f41-8793-885f6b4e2a57%2Fclear%20icon%20(1).png?1546753171009");
            for(int I = 0; I < Integer.parseInt(words[1]); I++) {
                OffsetDateTime t = cHistory.get(I).getCreationTime();
                builder.addField("Cleared: `" + cHistory.get(I).getContentRaw()+ "`",
                        "By: " + cHistory.get(I).getAuthor().getName() + ", "+t.getDayOfMonth() + "/" + t.getMonthValue() + "/" + t.getYear() + " at, " + amify(t.getHour(), t.getMinute()), false);
            }

            Ref.here.sendMessage(builder.build()).queue();
        }
    }
    public static String amify(int h, int m) {

        //return (h-12) + ":" + m + "pm";

        return h + ":" + m + "am" ;

    }
}
