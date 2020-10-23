import java.io.IOException;
import java.util.List;

import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

public class CoolThread implements Runnable{

    String name;
    Thread thre;

    CoolThread(String tName){
        name = tName;
        thre = new Thread(this, tName);

        //System.out.println("new thread! " + tName);
        thre.start();
    }
    public Thread getThread() {
        return this.thre;
    }

    public void run() {
        if(this.name.equals("One")) {
            threadOneRun();
        }
        if(this.name.equals("Two")) {
            threadTwoRun();
        }
        if(this.name.equals("Three")) {
            threadThreeRun();
        }


    }

    public static void threadOneRun() {
        System.out.println("Thread One Stating: \n handleing: \"most things\"");


    }
    public static void threadTwoRun() {
        System.out.println("Thread Two Stating: \n handleing: \"Message History\"");
    }
    public static void threadThreeRun() {
        System.out.println("Thread Three Stating: \n handleing: \"RGB Update\"");
        int restTim = 0;
        List<Member> mem;
        Role top;
        Guild g = Ref.bigMain;
        Role[] rs =  Rgb.sortRoles(Rgb.generateRoles(Rgb.roleNum, g), g);

        int start = rs[0].getPosition();


        while(true) {
            System.out.println("RGB tick");

            g.getController().modifyRolePositions().selectPosition(start).moveTo(start - 22).queue();

            try {
                Thread.sleep(Rgb.h);
            } catch (InterruptedException e) {}
        }
    }

}