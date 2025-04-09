package dao;


import entity.IAdoptable;
import java.util.List;

public interface AdoptionEventDAO {
    List<String> getUpcomingEvents();
    boolean registerParticipant(IAdoptable participant, int eventId);
}


