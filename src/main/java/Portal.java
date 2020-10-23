import java.awt.Color;
import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Portal {
    public static void warp(GuildMessageReceivedEvent evt) {
        String[] words = evt.getMessage().getContentRaw().split(" ");
        String[] wordsD = evt.getMessage().getContentRaw().split("&");
        java.util.List<TextChannel> portal = evt.getGuild().getTextChannelsByName("portal", true);
        java.util.List<TextChannel> botPortal = evt.getGuild().getTextChannelsByName("bot-poking", true);
        MessageChannel connection = null;
        String list = "";
        String server = "";
        String msg = "";
        if(words[0].equalsIgnoreCase(Ref.prefix + "set")){
            if(portal.size() >= 1) {
                Ref.List.add(evt.getGuild());
                Ref.portal.add(evt.getChannel());
                evt.getChannel().sendMessage("Your server has successfully been connected. Use `<list` to see the other connected servers, then try sending mesages by `<tell servername (message)`").queue();
            }else {
                evt.getChannel().sendMessage("Please make sure that you have a channel called `portal` on your server to use that function").queue();
            }
        }if(words[0].equalsIgnoreCase(Ref.prefix + "list")){
            if(Ref.List.size() == 0) {
                evt.getChannel().sendMessage("There are no servers connected").queue();
            }else {
                for(int i = 0; i < Ref.List.size(); i++) {
                    list = list + "\n -`" +Ref.List.get(i).getName().replace(" ", "") + "`";
                }
                evt.getChannel().sendMessage("**Here's a list of connected servers:**" + list).queue();
            }
        }if(words[0].equalsIgnoreCase(Ref.prefix + "tell")) {
            if(words.length >= 3) {
                for(int i = 0; i < Ref.List.size(); i++) {
                    server = Ref.List.get(i).getName().replace(" ", "");
                    if(words[1].equalsIgnoreCase(server))  {
                        if(Ref.List.size() > 1) {
                            connection = Ref.portal.get(i);
                        }else {
                            connection = Ref.List.get(i).getTextChannelsByName("portal", true).get(0);
                        }
                    }
                }
                if(connection != null) {
                    for(int u = 2; u < words.length; u++) {
                        msg = msg + " " + words[u];
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
                    Date date = new Date (System.currentTimeMillis());
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle(msg);
                    builder.setColor(Color.decode("#0652DD"));
                    builder.setDescription(sdf.format(date)+ " at " + stf.format(date));
                    builder.setAuthor(evt.getGuild().getName(), evt.getGuild().getIconUrl(), evt.getGuild().getIconUrl());
                    builder.setThumbnail("https://images-ext-1.discordapp.net/external/6RxE6NEG-rlYhaelycFE88kR1l8tMBoNsC_8BWBIBE8/%3Fq%3Dtbn%3AANd9GcRhmUxRvxRUHylqlZvn16LrW6dJRgWHAPgyD-j6jb-YO_Z4VVqb/https/encrypted-tbn0.gstatic.com/images");
                    connection.sendMessage(builder.build()).queue();
                    evt.getChannel().sendMessage("your message has been sent to " + connection.getName()).queue();
                }else {
                    evt.getChannel().sendMessage("Please enter the name of a connected server, use `<list` for a list of connected servers").queue();
                }
            }else {
                evt.getChannel().sendMessage("Please use the format: `<tell &server name& (message)`!").queue();
            }
        }
    }
}
