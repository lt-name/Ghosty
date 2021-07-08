package net.easecation.ghosty.v1.recording;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.TaskHandler;

public class RecordEngine {

    private final Player player;
    private final TaskHandler taskHandler;

    private int tick = 0;
    private boolean recording = true;
    private boolean stopped = false;

    private PlayerRecord record;

    public RecordEngine(Plugin plugin, Player player) {
        this.player = player;
        this.record = new LmlPlayerRecord(player);
        this.taskHandler = Server.getInstance().getScheduler().scheduleRepeatingTask(plugin, this::onTick, 1);
        Server.getInstance().getLogger().warning(player.getName() + " Record started!");
    }

    public boolean isRecording() {
        return recording;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setRecording(boolean recording) {
        this.recording = recording;
    }

    public Player getPlayer() {
        return player;
    }

    public void onTick() {
        if (this.isRecording()) {
            if (!this.player.isOnline()) {
                stopRecord();
            }
            this.record.record(this.tick, RecordNode.of(this.player));
        }
        this.tick++;
    }

    public PlayerRecord stopRecord() {
        this.setRecording(false);
        this.stopped = true;
        Server.getInstance().getLogger().warning(this.player.getName() + " Record stopped!");
        this.taskHandler.cancel();
        return this.record;
    }

}
