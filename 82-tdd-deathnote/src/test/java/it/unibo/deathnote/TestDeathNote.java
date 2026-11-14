package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteimpl;

class TestDeathNote {

    private DeathNoteimpl deathNote;


    @BeforeEach 
    void setUp(){
        this.deathNote = new DeathNoteimpl();
    }
    @Test 
    public void NotExistingRule() {
        assertThrows(IllegalArgumentException.class, () -> deathNote.getRule(0));
        assertThrows(IllegalArgumentException.class, () -> deathNote.getRule(50));
    }

    @Test
    public void NullRule(){
        for(int i = 1; i <= DeathNote.RULES.size(); i++){
            final String rule = deathNote.getRule(i);
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
    }

    @Test 
    public void TestWrittenPerson(){
        assertFalse(deathNote.isNameWritten("marco"));

        deathNote.writeName("marco");
        assertTrue(deathNote.isNameWritten("marco"));

        assertFalse(deathNote.isNameWritten("luca"));
        assertFalse(deathNote.isNameWritten(" "));        
    }

    @Test 
    public void TestDeathCause() throws InterruptedException{
        assertThrows(IllegalStateException.class, () -> deathNote.writeDeathCause("cancer"));
        deathNote.writeName("marco");
        
        assertEquals("Heart attack", deathNote.getDeathCause("marco"));

        deathNote.writeName("alessio");
        assertTrue(deathNote.writeDeathCause("karting accident"));

        assertEquals("karting accident", deathNote.getDeathCause("alessio"));

        Thread.sleep(100);

        deathNote.writeDeathCause("suicide bombing");

        assertEquals("karting accident", deathNote.getDeathCause("alessio"));
    }

    @Test 
    public void TestDetails() throws InterruptedException{
        assertThrows(IllegalStateException.class, () -> deathNote.writeDetails("really dead"));

        deathNote.writeName("marco");

        assertEquals("", deathNote.getDeathDetails("marco"));

        assertTrue(deathNote.writeDeathCause("ran for too long"));
        assertEquals("ran for too long", deathNote.getDeathCause("marco"));

        deathNote.writeName("alessio");
        Thread.sleep(6100);

        deathNote.writeDeathCause("he descovered he could not fly");
        assertEquals("ran for too long", deathNote.getDeathCause("marco"));
    }
}
