import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter {
    static int ran;
    public static void pong(Message m) {
        String[] words  = m.getContentRaw().split(" ");

        if(words[0].equalsIgnoreCase(Ref.prefix+"ping")) {

            ran = (int) Math.random() * 1500 + 1500;
            if(!Ref.playing) {
                Ref.pch = m.getChannel();
                Ref.playing = true;
                if(Ref.round == 0) {
                    m.getChannel().sendMessage("start; playng:" + Ref.playing).queue();
                    m.getChannel().sendMessage("**Challenging me to a duel are you?** we shall play, "
                            + "respond to my pong with a <ping within a secong to hit it back").queue();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    m.getChannel().sendMessage("<pong").queue();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(!Ref.got) {
                        m.getChannel().sendMessage("stop; " + Ref.got + "playing: " + Ref.playing).queue();
                        m.getChannel().sendMessage("You lose... no one can beat the bot GG").queue();

                        Ref.playing = false;
                        Ref.round = 0;
                    }
                }



            } else {
                if(true){
                    Ref.round++;
                    Ref.got = true;
                    m.getChannel().sendMessage("null; " + Ref.got).queue();
                    try {
                        Thread.sleep(ran);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    m.getChannel().sendMessage("<pong").queue();
                    try {
                        Thread.sleep(1400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(!Ref.got) {
                        m.getChannel().sendMessage("You lose... no one can beat the bot GG").queue();
                        Ref.playing = false;
                        Ref.round = 0;
                    }
                } else {
                    m.getChannel().sendMessage("**I am busy playing someone else right now, try again in a minute").queue();
                }

            }


        }
    }
}
