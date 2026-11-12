package it.unibo.deathnote.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteimpl implements DeathNote{

    private class Person{
        private String name;
        private String causeDeath;
        private String details;

        public Person(String name){
            this.name = name;
            this.causeDeath = "Heart attack";
            this.details = "";
        }
    }
    private List<Person> deaths = new ArrayList<>();
    private long TimeName;
    private long timeCause;
    

    @Override
    public String getRule(int ruleNumber) {
        if(ruleNumber < 1 || ruleNumber > RULES.size()){
            throw new IllegalArgumentException("Argument out of border");
        }

        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        if(name == null){
            throw new NullPointerException("non-existent name");
        }

        deaths.add(new Person(name));
        this.TimeName = System.currentTimeMillis();
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(deaths.isEmpty() || cause == null){
            throw new IllegalStateException("non existing cause");
        }

        timeCause = System.currentTimeMillis();

        if(timeCause - this.TimeName <= 40){
            this.deaths.get(this.deaths.size() - 1).causeDeath = cause;
            return true;
        }
        return false; 
    }

    @Override
    public boolean writeDetails(String details) {
                if(deaths.isEmpty() || details == null){
            throw new IllegalStateException("non existing cause");
        }

        long ActualTime = System.currentTimeMillis();
        if(ActualTime - this.timeCause <= 6040){
            this.deaths.get(this.deaths.size() - 1).details = details;
            return true;
        }
        return false; 
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }
    
}
