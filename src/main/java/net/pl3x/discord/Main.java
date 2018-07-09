package net.pl3x.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String args[]) {
        Gui gui = new Gui();

        JFrame frame = new JFrame("Gui");
        frame.setContentPane(gui.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().setVisible(false);
                if (gui.discord.thread != null) {
                    gui.discord.thread.interrupt();
                }
            }
        });
    }

    public void method(String[] args) {
        /*if (args.length == 0) {
            System.err.println("You must specify an application ID in the arguments!");
            System.exit(-1);
        }
        DiscordRPC lib = DiscordRPC.INSTANCE;
        DiscordRichPresence presence = new DiscordRichPresence();
        String applicationId = args.length < 1 ? "" : args[0];
        String steamId = args.length < 2 ? "" : args[1];

        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> System.out.println("Ready!");

        lib.Discord_Initialize(applicationId, handlers, true, steamId);


        presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second
        presence.smallImageText = "small";
        presence.smallImageKey = "small";
        presence.largeImageText = "large";
        presence.largeImageKey = "large";
        presence.endTimestamp = presence.startTimestamp + 200000;
        presence.details = "Details here";
        presence.state = "State here";
        presence.partySize = 1;
        presence.partyMax = 4;
        lib.Discord_UpdatePresence(presence);

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    lib.Discord_Shutdown();
                    break;
                }
            }
        }, "RPC-Callback-Handler");
        thread.start();

        JButton submit = new JButton("Update Presence");
        submit.addActionListener(e -> {
            presence.details = detailsText.getText();
            presence.state = stateText.getText();
            try {
                presence.partySize = Integer.parseInt(partySizeText.getText());
            } catch (Exception ignored) {
            }
            try {
                presence.partyMax = Integer.parseInt(partyMaxText.getText());
            } catch (Exception ignored) {
            } // if text isn't a number, ignore it

            lib.Discord_UpdatePresence(presence);
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().setVisible(false);
                thread.interrupt();
            }
        });*/
    }

}
