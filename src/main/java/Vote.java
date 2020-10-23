import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.dv8tion.jda.core.entities.User;

public class Vote {

    private int index;
    private  int yes;
    private  int no;
    private  int maybe;
    private String id;
    private  String vote;
    private  HashMap<User, String> people = new HashMap<User, String>();

    public Vote(String vote, String id) {
        this.yes = 0;
        this.no = 0;
        this.maybe = 0;
        this.vote = vote;
        this.index = Ref.votes.size();
        this.id = id;
    }

    public int getYes() {
        return this.yes;
    }
    public int getIndex() {
        return this.index;
    }
    public String getId(){
        return this.id;
    }
    public void addYes(User who) {
        if(!this.didVote(who)) {
            this.yes++;
            this.people.put(who, "y");
            System.out.println(who.getName() + " has voted yes to " + this.getVote());
        }else {
            this.changeTo(who, "y");
        }
        this.string();
    }
    public void addMaybe(User who) {
        if(!this.didVote(who)) {
            this.maybe++;
            this.people.put(who, "m");
            System.out.println(who.getName() + " has voted maybe to " + this.getVote());
        } else {
            this.changeTo(who, "m");
        }
    }
    public void addNo(User who) {
        if(!this.didVote(who)) {
            this.no++;
            this.people.put(who, "n");
            System.out.println(who.getName() + " has voted no to " + this.getVote());
        }else {
            this.changeTo(who, "n");
        }
    }
    public int getMaybe() {
        return this.maybe;
    }
    public int getNo() {
        return this.no;
    }
    public String getVote() {
        return this.vote;
    }
    public boolean didVote(User who) {
        for(int i = 0; i < people.size(); i++) {
            if(people.keySet().toArray()[i].equals(who)) {
                return true;
            }
        }
        return false;
    }
    private void changeTo(User user, String vote) {
        String ov = this.people.get(user);
        if(ov.equals("y")) {
            this.yes--;
        }
        if(ov.equals("m")) {
            this.maybe--;
        }
        if(ov.equals("n")) {
            this.no--;
        }
        this.people.remove(user);

        if(vote.equals("y")) {
            this.addYes(user);
        }
        if(vote.equals("m")) {
            this.addMaybe(user);
        }
        if(vote.equals("n")) {
            this.addNo(user);
        }

    }
    public String string() {
        List<User> persons = new ArrayList();
        persons.addAll(this.people.keySet());
        String str = "";
        str =this.vote+" | ";
        str += "" + this.yes + this.maybe + this.no + " | ";
        for(int i = 0; i < this.people.size(); i++) {
            str += persons.get(i).getId() + "-" + this.people.get(persons.get(i)) + " ! ";
        }
        str += " | " + this.getIndex();
        System.out.println("saved: " + str);
        return str;
    }
}
