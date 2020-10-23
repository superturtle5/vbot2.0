import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;

public class Rgb {
    final static int h = 5000;
    final static int roleNum = 23;
    static WriteFile store = new WriteFile("E:/code/cache.txt", true);
    static WriteFile rStore = new WriteFile("E:/code/cache.txt", false);
    static Role[] r = new Role[4];

    public static void ree(GuildMessageReceivedEvent evt) throws IOException {
        Message m = evt.getMessage();
        Member o = evt.getMember();
        Guild g = evt.getGuild();
        String mem = "";
        List<Role> roleList;
        String[] ls;
        int ree = 5;

        if(evt.getMessage().getContentRaw().equalsIgnoreCase(Ref.prefix + "Transcend to RGB")) {
//			if(getList().length > 0) {
//				for(String cur : getList()) {
//					System.out.println(cur.strip() + " =? " + o.getUser().getId());
//					if(!cur.strip().equals(o.getUser().getId())) {
//						 mem = o.getUser().getId();
//						store.writeToFile(mem);
//						System.out.println(mem + " is bign addd to the list, \n");
//						System.out.println("adding role: " +   g.getRolesByName("rgb0", true).get(0));
//						return;
//					}
//				}
//			}
            if(o.getRoles().containsAll(Arrays.asList(generateRoles(9, g)))){
                evt.getChannel().sendMessage("Sorry, but you have already transended... you simply lack the power required to advance futher down the path of RGB \n __***RGB REEEEEEEEEEEEEE***__").queue();
                return;
            }
            evt.getGuild().getController().addRolesToMember(o, Arrays.asList(generateRoles(roleNum, g))).queue();
            m.getChannel().sendMessage(o.getAsMention() + " has transended to rgb!").queue();
        }
        if(evt.getMessage().getContentRaw().equals(Ref.prefix + "descend")) {
            if(!isOnList(o)) {
                return;
            }
            String nls = "";
            for(String cur : getList()) {
                if(!cur.trim().equals(o)) {
                    nls += (cur + "\n");
                }
            }
            //rStore.writeToFile(nls);
            g.getController().removeRolesFromMember(o, Arrays.asList(generateRoles(9, g))).queue();
            evt.getChannel().sendMessage("As a coward, " + o.getAsMention() + " has bakced down from thier epic place amoung the transcended only to live as a mere mortal, lacking RGB").queue();
        }

    }
    public static Member memById(Guild s, String id) {
        List<Member> memLs = s.getMembers();
        Member match = null;
        for(Member tarMem : memLs) {
            //	System.out.println("<- " + tarMem.getUser().getId() + " =? " + id.strip() + "->" );
            if(tarMem.getUser().getId().equals(id.trim()))
                match = tarMem;
        }
        //System.out.println(match);
        return match;
    }
    public static void flash(Role[] r, Member o, int reps) {
        int i = 1;
        GuildController con = o.getGuild().getController();
        for(int y = 0; y <= reps; y++) {
            for(Role c : r ) {
                con.addSingleRoleToMember(o, c).queueAfter(i, TimeUnit.MILLISECONDS);;
                i += h;
                con.removeSingleRoleFromMember(o, c).queueAfter(i, TimeUnit.MILLISECONDS);
            }
        }
        i = 0;
    }
    public static String[] getList() throws IOException{
        return store.read().split("\n");

    }
    public static void update(Member o, int n) {
        Guild g = o.getGuild();
        int ros;
        int rep = 0;
        Role[] rs = generateRoles(n, g);
        Role cur = rs[0], nxt = rs[0];
//		System.out.println(n);
//		System.out.println(o.getRoles());
//		System.out.println(rs);
        for(Role lko: o.getRoles()) {
            for(Role flok : rs) {
                //System.out.println(flok + " =? " + lko);
                if(lko.equals(flok)) {
                    cur = lko;
                    ros = Integer.parseInt(cur.getName().charAt(3) + "");
                    //System.out.println("ros should be: " + Integer.parseInt(cur.getName().charAt(3) + "") + "but is " + ros + "Repersenting " + cur);
                    if((ros + 1) == n) {
                        ros = -1;
                    }
                    //System.out.println("MATCH, " + o.getAsMention() + " has the role I was looking for " + ros);
                    nxt = g.getRolesByName("rgb" + (ros  + 1), true).get(0);
                    rep++;
                }
            }
        }
        if(Ref.lastCur != null) {
            if(Ref.lastCur.equals(cur)) {
                System.out.println("Failure: discord doesn't like me, waiting 3000 then retrying");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {}
            }

        }
        if(rep != 1) {
            reset(o);
            System.out.println("Failure: has too many roles; reseting user");
            return;
        }
        Ref.lastCur = cur;
        System.out.println(cur.getName() + " --> " + nxt.getName());
        g.getController().addSingleRoleToMember(o,  nxt).queue();
        g.getController().removeSingleRoleFromMember(o, cur).queue();

    }
    public static boolean isOnList(Member o) throws IOException {
        Guild g = o.getGuild();
        String[] us = store.read().split("\n");
        for(int i = 0; i < us.length; i++) {
            //System.out.println(i + " checking spot..");
            //System.out.println("<- " + us[i].strip() + " =? " +memById(g, o.getUser().getId()) + "->" );
            if(us[i].trim().equals(memById(g, o.getUser().getId()).getUser().getId())) {
                //System.out.println("User " + o.getAsMention() + " typing. They are on the rgb list.");
                return true;
            }
        }
        return false;

    }
    public static void reset(Member o) {
        for(Role s: o.getRoles()) {
            if(s.getName().substring(0,3).equals("rgb")) {
                System.out.println("Removing: " + s.getName() + "  " + s.getName().substring(0,3));
                o.getGuild().getController().removeSingleRoleFromMember(o, s).queue();
            }
        }
        o.getGuild().getController().addSingleRoleToMember(o, o.getGuild().getRolesByName("rgb0", true).get(0)).queue();
    }
    public static Role[] generateRoles(int num, Guild g) {
        Role[] rs = new Role[num];
        for(int i = 0; i < num; i++) {
            rs[i] = g.getRolesByName("rgb" + i, true).get(0);
        }
        return rs;
    }

    public static Role[] sortRoles(Role[] rus, Guild g) {
        int don = 0;
        Role[] rs = new Role[roleNum];
        Role highest = g.getPublicRole();
        for(int o = 0; o < roleNum; o++ ) {
            System.out.println("SLOT: " + o);
            for(int i = 0; i < roleNum; i++) {
                System.out.println("Checking " + rus[i].getName() + " at " + rus[i].getPosition() + " against " +  highest.getName() + " at " + highest.getPosition() + "");
                if(rus[i].getPosition() > highest.getPosition()) {
                    System.out.println("highest has changed to: " + rus[i].getName() + " and don = " + i);
                    highest = rus[i];
                    don = i;
                }
            }
            System.out.println("adding: " + highest.getName() + " to "  + o + " from " + don);
            rs[o] = highest;
            rus[don] = g.getPublicRole();
            highest = g.getPublicRole();
        }
        return rs;

    }

}