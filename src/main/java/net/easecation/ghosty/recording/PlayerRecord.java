package net.easecation.ghosty.recording;

import cn.nukkit.entity.data.Skin;
import cn.nukkit.utils.BinaryStream;

import java.util.Base64;

/**
 * Created by Mulan Lin('Snake1999') on 2016/11/19 15:08.
 */
public interface PlayerRecord {

    byte OBJECT_LML = 0;

    void record(long tick, RecordNode node);

    RecordIterator iterator();

    String getPlayerName();

    Skin getSkin();

    default String toBase64String() {
        return Base64.getEncoder().encodeToString(this.toBinary());
    }

    byte[] toBinary();

    static PlayerRecord fromBase64String(String string) {
        return fromBinary(Base64.getDecoder().decode(string));
    }

    static PlayerRecord fromBinary(byte[] data) {
        BinaryStream stream = new BinaryStream(data);
        byte type = (byte) stream.getByte();
        switch (type) {
            case OBJECT_LML:
                return new LmlPlayerRecord(stream);
        }
        return null;
    }

}
