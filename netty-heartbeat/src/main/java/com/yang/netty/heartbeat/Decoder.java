package com.yang.netty.heartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Decoder extends ReplayingDecoder<Decoder.State> {
    private static final Logger log = LoggerFactory.getLogger(Decoder.class);

    private Message message;

    public enum State {
        TYPE,
        LENGTH,
        CONTENT
    }

    public Decoder() {
        super(State.TYPE);
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        State state = state();
        switch (state) {
            case TYPE:
              message = new Message();
              byte type = in.readByte();
              log.debug("type: " + type);
              message.setType(type);
              checkpoint(State.LENGTH);
              break;
            case LENGTH:
                int length = in.readInt();
                message.setLength(length);
                if (length > 0) {
                    checkpoint(State.LENGTH);
                } else {
                    checkpoint(State.TYPE);
                }
                break;
            case CONTENT:
                byte[] bytes = new byte[message.getLength()];
                in.readBytes(bytes);
                message.setContent(new String(bytes));
                out.add(message);
                checkpoint(State.TYPE);
                break;
            default:
                throw new IllegalStateException("invaild state");
        }
        log.debug("end state:" + state + "list:" + out.toString());
    }
}
