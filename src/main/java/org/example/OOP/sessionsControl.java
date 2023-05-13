package org.example.OOP;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class sessionsControl {

    private static List<ActiveSession> activeSessionsList = new ArrayList<>();
    public static ActiveSession InitiateSession(User sessionOwner)
    {
        String SessionId = UUID.randomUUID().toString();
        ActiveSession newSession = new ActiveSession(SessionId, sessionOwner);
        activeSessionsList.add(newSession);
        return newSession;
    }

    public static boolean TerminateSession(String sessionId)
    {
        return activeSessionsList.removeIf((element) -> element.getSessionId().equals(sessionId));
    }

    public static boolean TerminateSession(ActiveSession session)
    {
        return activeSessionsList.removeIf((element) -> element.getSessionId().equals(session.getSessionId()));
    }

    public static boolean isSessionActive(String sessionId)
    {
        boolean isActive = false;
        for (ActiveSession session : activeSessionsList)
        {
            if (session.getSessionId().equals(sessionId))
            {
                isActive = true;
                break;
            }
        }
        return isActive;
    }

    public static ActiveSession getSessionFromId(String sessionId)
    {
        return activeSessionsList.stream().filter(session -> session.getSessionId().equals(sessionId)).findFirst().orElse(null);
    }
}