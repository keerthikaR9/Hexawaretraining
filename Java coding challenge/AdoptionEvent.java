package entity;

import java.util.ArrayList;
import java.util.List;

public class AdoptionEvent {
    private List<IAdoptable> participants;

   
    public AdoptionEvent() {
        this.participants = new ArrayList<>();
    }

    
    public void registerParticipant(IAdoptable participant) {
        participants.add(participant);
        System.out.println("Participant registered.");
    }

    
    public void hostEvent() {
        System.out.println("Adoption Event Started!");
        for (IAdoptable participant : participants) {
            participant.adopt(); 
        }
        System.out.println("Adoption Event Completed!");
    }
}

