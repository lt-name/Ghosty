package net.easecation.ghosty.v1.recording;

import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.LongEntityData;
import cn.nukkit.utils.BinaryStream;
import net.easecation.ghosty.v1.entity.PlaybackNPC;

/**
 * Created by Mulan Lin('Snake1999') on 2016/11/19 15:23.
 */
class UpdatedDataFlags implements Updated {

    static UpdatedDataFlags of(long flags) {
        return new UpdatedDataFlags(flags);
    }

    @Override
    public int getUpdateTypeId() {
        return Updated.TYPE_DATA_FLAGS;
    }

    private long flags;

    @Override
    public void processTo(PlaybackNPC ghost) {
        ghost.setDataProperty(new LongEntityData(Entity.DATA_FLAGS, this.flags));
    }

    @Override
    public RecordNode applyTo(RecordNode node) {
        node.setDataFlags(flags);
        return node;
    }

    public UpdatedDataFlags(BinaryStream stream) {
        read(stream);
    }

    private UpdatedDataFlags(long flags) {
        this.flags = flags;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof UpdatedDataFlags)) return false;
        UpdatedDataFlags o = (UpdatedDataFlags) obj;
        return flags == o.flags;
    }

    @Override
    public void write(BinaryStream stream) {
        stream.putVarLong(this.flags);
    }

    @Override
    public void read(BinaryStream stream) {
        this.flags = stream.getVarLong();
    }
}