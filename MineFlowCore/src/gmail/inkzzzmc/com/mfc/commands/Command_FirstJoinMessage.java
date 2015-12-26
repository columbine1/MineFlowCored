package gmail.inkzzzmc.com.mfc.commands;

import gmail.inkzzzmc.com.mfc.commands.api.CommandFactory;
import gmail.inkzzzmc.com.mfc.language.Language;
import gmail.inkzzzmc.com.mfc.utils.FileUtil;
import gmail.inkzzzmc.com.mfc.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Command_FirstJoinMessage extends CommandFactory {

    public Command_FirstJoinMessage() {
        super("welcomemessage", "litebans.mute", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length >= 1) {
            FileConfiguration file = FileUtil.getFile("firstjoinmessages.yml");

            if(args[0].equalsIgnoreCase("on")) {
                sender.sendMessage(Language.FIRST_JOIN_MESSAGE_ON.getMessage());

                return;
            } else if(args[0].equalsIgnoreCase("off")) {
                file.set("messages." + ((Player) sender).getUniqueId(), null);
                FileUtil.saveFile(file, "firstjoinmessages.yml");

                sender.sendMessage(Language.FIRST_JOIN_MESSAGE_OFF.getMessage());

                return;
            }

            String message = "";

            for(int i = 0; i < args.length; i++) {
                message += args[i] + " ";
            }

            message = message.substring(0, message.length() - 1);
            file.set("messages." + ((Player) sender).getUniqueId(), message);
            FileUtil.saveFile(file, "filesjoinmessages.yml");

            sender.sendMessage(Language.FIRST_JOIN_MESSAGE.getMessage().replace("%message%", Utils.color(message)));
            return;
        }

        sender.sendMessage(Language.USAGE.getMessage().replace("%command%", "welcomemessage <on/off/message>\n&ePlaceholders:\n &e- %newplayer%"));
    }

}
