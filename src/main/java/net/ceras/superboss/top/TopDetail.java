package net.ceras.superboss.top;

import java.util.List;

public class TopDetail {
    private List<String> command;
    private List<String> messagePlayer;
    private List<String> messageGlobal;

    public TopDetail(List<String> command, List<String> messagePlayer) {
        this.command = command;
        this.messagePlayer = messagePlayer;
    }

    public List<String> getCommand() {
        return command;
    }

    public List<String> getMessagePlayer() {
        return messagePlayer;
    }

    public List<String> getMessageGlobal() {
        return messageGlobal;
    }
}
