import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.audit.ActionType;
import net.dv8tion.jda.core.audit.AuditLogEntry;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageDeleteEvent;

public class Delete {


    public static void log(MessageDeleteEvent evt){
        Message msg = Mhistory.fetch(evt.getMessageId());
        List<AuditLogEntry> res = evt.getGuild().getAuditLogs().complete();
        AuditLogEntry audit = res.get(0);
        User mem = audit.getUser();
//	   	 System.out.println(msg + " != null");
//	   	 System.out.println(audit.getType());
//	   	 System.out.println(audit.getTargetId() + " == " + msg.getAuthor().getId());
        if(msg != null && audit.getType().equals(ActionType.MESSAGE_DELETE) && audit.getTargetId().equals(msg.getAuthor().getId())) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
            Date date = new Date (System.currentTimeMillis());
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle(mem.getName()+" has deleted `"+ msg.getContentRaw() + "` by "+ msg.getAuthor().getName());
            builder.setColor(Color.decode("#EA2029"));
            builder.setDescription("in "+ msg.getChannel().getName() + " " + sdf.format(date)+ " at " + stf.format(date));
            builder.setAuthor(mem.getName(), mem.getAvatarUrl(), mem.getAvatarUrl());
            builder.setThumbnail("//https://docs.google.com/drawings/d/e/2PACX-1vRo3OlBtygDBiXoQkXIar1hkg3B9aPcQjIh2eKCn7Joam1AhlRjhTIOR4oGgGx13u02OcebUFQ1YJWy/pub?w=720&h=720");
            Ref.here.sendMessage(builder.build()).queue();
        }
    }


}

