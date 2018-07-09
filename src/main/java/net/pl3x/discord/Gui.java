package net.pl3x.discord;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui {
    Discord discord;

    JPanel contentPane;
    JTextField applicationId;
    JTextField steamId;
    JButton disconnectButton;
    JButton connectButton;
    JTextField state;
    JTextField details;
    JTextField start;
    JTextField end;
    JTextField largeKey;
    JTextField largeText;
    JTextField smallKey;
    JTextField smallText;
    JTextField partyID;
    JTextField partySize;
    JTextField partyMax;
    JTextField matchSecret;
    JTextField joinSecret;
    JTextField spectateSecret;
    JTextField instance;
    JButton updatePresenceButton;
    JLabel status;

    public Gui() {
        discord = new Discord(this);

        connectButton.addActionListener(actionEvent -> discord.connect());
        disconnectButton.addActionListener(actionEvent -> discord.disconnect());
        updatePresenceButton.addActionListener(actionEvent -> discord.updatePresence());
    }
}
