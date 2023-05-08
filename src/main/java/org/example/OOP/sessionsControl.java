package org.example.OOP;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class sessionsControl {

    private static List<ActiveSession> activeSessionsList = new ArrayList<>();
    public static ActiveSession InitiateSession(User sessionOwner)
    {
        ActiveSession newSession = new ActiveSession(UUID.randomUUID().toString(), sessionOwner);
        return newSession;
    }

    public static boolean TerminateSession(String sessionId)
    {
        return activeSessionsList.removeIf((element) -> element.sessionId == sessionId);
    }

    public static boolean TerminateSession(ActiveSession session)
    {
        return activeSessionsList.removeIf((element) -> element.sessionId == session.sessionId);
    }
}
