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
        super("setfjmessage", "litebans.mute", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 1) {
            String message = args[0];
            FileConfiguration file = FileUtil.getFile("firstjoinmessages.yml");
            file.set("messages." + ((Player) sender).getUniqueId(), message);
            FileUtil.saveFile(file, "filesjoinmessages.yml");

            sender.sendMessage(Language.FIRST_JOIN_MESSAGE.getMessage().replace("%message%", Utils.color(message)));
            return;
        }

        sender.sendMessage(Language.USAGE.getMessage().replace("%command%", "setfjmessage <message>"));
    }

}
