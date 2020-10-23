
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.List;


    public class Ref {
        public static final String token = "NDgyMzgyNTI5NTk3Mjc2MTkw.W39zvQ.zylyyxGT8RS3cI6-loUEHUL4uUA";
        public static final String prefix = "<";
        public static MessageChannel here = null;
        public static List<Guild> List = new ArrayList<Guild>();
        public static Guild main;
        public static List<MessageChannel> portal = new ArrayList<MessageChannel>();
        public static List<Message> answeres = new ArrayList<Message>();
        public static Message prev = null;
        public static Boolean playing = false;
        public static Boolean got = false;
        public static MessageChannel pch = null;
        public static int round = 0;
        public static List<Vote> votes = new ArrayList<Vote>();
        public static List<User> notify = new ArrayList<User>();
        public static Guild bigMain;
        public static Boolean didFlail;
        public static Role lastCur;
        public static boolean on = false;

    }
