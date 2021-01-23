package net.easecation.ghosty;

import cn.nukkit.entity.data.Skin;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import net.easecation.ghosty.entity.PlaybackNPC;
import net.easecation.ghosty.recording.PlayerRecord;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GhostyPlugin extends PluginBase {

    private static GhostyPlugin instance;

    /* 录制完成的成品 */
    private final List<PlayerRecord> playerRecords = new ArrayList<>();

    public static GhostyPlugin getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        if (instance == null) instance = this;
        InputStream skinStream = this.getResource("skin.png");
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(skinStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PlaybackNPC.defaultSkin = new Skin();
        PlaybackNPC.defaultSkin.setSkinData(bufferedImage);
    }

    @Override
    public void onEnable() {
        this.getLogger().notice("GhostyPlugin enabled!");
    }

    public List<PlayerRecord> getPlayerRecords() {
        return playerRecords;
    }

}
