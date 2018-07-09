package net.pl3x.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class Discord {
    Gui gui;
    DiscordRPC rpc;
    Thread thread;
    DiscordEventHandlers handlers = new DiscordEventHandlers();

    public Discord(Gui gui) {
        this.gui = gui;
    }

    public void connect() {
        gui.status.setText("Connecting...");

        this.rpc = DiscordRPC.INSTANCE;

        handlers.ready = (user) -> {
            gui.status.setText("Connected as " + user.username + " (" + user.userId + ")");
            System.out.println("Connected as " + user.username + " (" + user.userId + ")");
        };
        handlers.disconnected = (code, msg) -> {
            gui.status.setText("Disconnected: " + msg + " (" + code + ")");
            System.out.println("Disconnected: " + msg + " (" + code + ")");
        };
        handlers.errored = (code, msg) -> {
            gui.status.setText("Errored: " + msg + " (" + code + ")");
            System.out.println("Errored: " + msg + " (" + code + ")");
        };

        rpc.Discord_Initialize(gui.applicationId.getText(), handlers, true, gui.steamId.getText());

        updatePresence();

        thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                rpc.Discord_RunCallbacks();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    rpc.Discord_Shutdown();
                    break;
                }
            }
        }, "RPC-Callback-Handler");
        thread.start();
    }

    public void disconnect() {
        thread.interrupt();
    }

    public void updatePresence() {
        DiscordRichPresence presence = new DiscordRichPresence();

        presence.state = gui.state.getText();
        presence.details = gui.details.getText();
        try {
            presence.startTimestamp = Long.parseLong(gui.start.getText());
            presence.endTimestamp = Long.parseLong(gui.end.getText());
        } catch (Exception e) {
            presence.startTimestamp = System.currentTimeMillis() / 1000;
        }
        presence.largeImageKey = gui.largeKey.getText();
        presence.largeImageText = gui.largeText.getText();
        presence.smallImageKey = gui.smallKey.getText();
        presence.smallImageText = gui.smallText.getText();
        presence.partyId = gui.partyID.getText();
        try {
            presence.partySize = Integer.parseInt(gui.partySize.getText());
        } catch (Exception ignore) {
        }
        try {
            presence.partyMax = Integer.parseInt(gui.partyMax.getText());
        } catch (Exception ignore) {
        }
        presence.matchSecret = gui.matchSecret.getText();
        presence.joinSecret = gui.joinSecret.getText();
        presence.spectateSecret = gui.spectateSecret.getText();
        try {
            presence.instance = Byte.parseByte(gui.instance.getText());
        } catch (Exception ignore) {
        }

        rpc.Discord_UpdatePresence(presence);
    }
}
