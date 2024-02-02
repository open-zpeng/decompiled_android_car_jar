package com.android.carsdk.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class Utf8 {
    private static final long ASCII_MASK_LONG = -9187201950435737472L;
    public static final int COMPLETE = 0;
    public static final int MALFORMED = -1;
    static final int MAX_BYTES_PER_CHAR = 3;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Logger logger = Logger.getLogger(Utf8.class.getName());
    private static final Processor processor;

    static {
        processor = UnsafeProcessor.isAvailable() ? new UnsafeProcessor() : new SafeProcessor();
    }

    public static boolean isValidUtf8(byte[] bytes) {
        return processor.isValidUtf8(bytes, 0, bytes.length);
    }

    public static boolean isValidUtf8(byte[] bytes, int index, int limit) {
        return processor.isValidUtf8(bytes, index, limit);
    }

    public static int partialIsValidUtf8(int state, byte[] bytes, int index, int limit) {
        return processor.partialIsValidUtf8(state, bytes, index, limit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int byte1) {
        if (byte1 <= -12) {
            return byte1;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int byte1, int byte2) {
        if (byte1 > -12 || byte2 > -65) {
            return -1;
        }
        return (byte2 << 8) ^ byte1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int byte1, int byte2, int byte3) {
        if (byte1 > -12 || byte2 > -65 || byte3 > -65) {
            return -1;
        }
        return ((byte2 << 8) ^ byte1) ^ (byte3 << 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(byte[] bytes, int index, int limit) {
        int byte1 = bytes[index - 1];
        switch (limit - index) {
            case 0:
                return incompleteStateFor(byte1);
            case 1:
                return incompleteStateFor(byte1, bytes[index]);
            case 2:
                return incompleteStateFor(byte1, bytes[index], bytes[index + 1]);
            default:
                throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(ByteBuffer buffer, int byte1, int index, int remaining) {
        switch (remaining) {
            case 0:
                return incompleteStateFor(byte1);
            case 1:
                return incompleteStateFor(byte1, buffer.get(index));
            case 2:
                return incompleteStateFor(byte1, buffer.get(index), buffer.get(index + 1));
            default:
                throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class UnpairedSurrogateException extends IllegalArgumentException {
        private UnpairedSurrogateException(int index, int length) {
            super("Unpaired surrogate at index " + index + " of " + length);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encodedLength(CharSequence sequence) {
        int utf16Length = sequence.length();
        int utf8Length = utf16Length;
        int i = 0;
        while (i < utf16Length && sequence.charAt(i) < 128) {
            i++;
        }
        while (true) {
            if (i < utf16Length) {
                char c = sequence.charAt(i);
                if (c < 2048) {
                    utf8Length += (127 - c) >>> 31;
                    i++;
                } else {
                    utf8Length += encodedLengthGeneral(sequence, i);
                    break;
                }
            } else {
                break;
            }
        }
        if (utf8Length < utf16Length) {
            throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (utf8Length + 4294967296L));
        }
        return utf8Length;
    }

    private static int encodedLengthGeneral(CharSequence sequence, int start) {
        int utf16Length = sequence.length();
        int utf8Length = 0;
        int utf8Length2 = start;
        while (utf8Length2 < utf16Length) {
            char c = sequence.charAt(utf8Length2);
            if (c < 2048) {
                utf8Length += (127 - c) >>> 31;
            } else {
                utf8Length += 2;
                if (55296 <= c && c <= 57343) {
                    int cp = Character.codePointAt(sequence, utf8Length2);
                    if (cp < 65536) {
                        throw new UnpairedSurrogateException(utf8Length2, utf16Length);
                    }
                    utf8Length2++;
                }
            }
            utf8Length2++;
        }
        return utf8Length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encode(CharSequence in, byte[] out, int offset, int length) {
        return processor.encodeUtf8(in, out, offset, length);
    }

    static boolean isValidUtf8(ByteBuffer buffer) {
        return processor.isValidUtf8(buffer, buffer.position(), buffer.remaining());
    }

    static int partialIsValidUtf8(int state, ByteBuffer buffer, int index, int limit) {
        return processor.partialIsValidUtf8(state, buffer, index, limit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void encodeUtf8(CharSequence in, ByteBuffer out) {
        processor.encodeUtf8(in, out);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int estimateConsecutiveAscii(ByteBuffer buffer, int index, int limit) {
        int i = index;
        int lim = limit - 7;
        while (i < lim && (buffer.getLong(i) & ASCII_MASK_LONG) == 0) {
            i += 8;
        }
        return i - index;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class Processor {
        abstract int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2);

        abstract void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer);

        abstract int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3);

        abstract int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3);

        Processor() {
        }

        final boolean isValidUtf8(byte[] bytes, int index, int limit) {
            return partialIsValidUtf8(0, bytes, index, limit) == 0;
        }

        final boolean isValidUtf8(ByteBuffer buffer, int index, int limit) {
            return partialIsValidUtf8(0, buffer, index, limit) == 0;
        }

        final int partialIsValidUtf8(int state, ByteBuffer buffer, int index, int limit) {
            if (buffer.hasArray()) {
                int offset = buffer.arrayOffset();
                return partialIsValidUtf8(state, buffer.array(), offset + index, offset + limit);
            } else if (buffer.isDirect()) {
                return partialIsValidUtf8Direct(state, buffer, index, limit);
            } else {
                return partialIsValidUtf8Default(state, buffer, index, limit);
            }
        }

        final int partialIsValidUtf8Default(int state, ByteBuffer buffer, int index, int limit) {
            int index2;
            if (state != 0) {
                if (index >= limit) {
                    return state;
                }
                byte byte1 = (byte) state;
                if (byte1 < -32) {
                    if (byte1 >= -62) {
                        index2 = index + 1;
                        if (buffer.get(index) > -65) {
                        }
                    }
                    return -1;
                } else if (byte1 < -16) {
                    byte byte2 = (byte) (~(state >> 8));
                    if (byte2 == 0) {
                        int index3 = index + 1;
                        byte2 = buffer.get(index);
                        if (index3 >= limit) {
                            return Utf8.incompleteStateFor(byte1, byte2);
                        }
                        index = index3;
                    }
                    if (byte2 <= -65 && ((byte1 != -32 || byte2 >= -96) && (byte1 != -19 || byte2 < -96))) {
                        index2 = index + 1;
                        if (buffer.get(index) > -65) {
                        }
                    }
                    return -1;
                } else {
                    byte byte22 = (byte) (~(state >> 8));
                    byte byte3 = 0;
                    if (byte22 == 0) {
                        int index4 = index + 1;
                        byte22 = buffer.get(index);
                        if (index4 >= limit) {
                            return Utf8.incompleteStateFor(byte1, byte22);
                        }
                        index = index4;
                    } else {
                        byte3 = (byte) (state >> 16);
                    }
                    if (byte3 == 0) {
                        int index5 = index + 1;
                        byte3 = buffer.get(index);
                        if (index5 >= limit) {
                            return Utf8.incompleteStateFor(byte1, byte22, byte3);
                        }
                        index = index5;
                    }
                    if (byte22 <= -65 && (((byte1 << 28) + (byte22 + 112)) >> 30) == 0 && byte3 <= -65) {
                        int index6 = index + 1;
                        if (buffer.get(index) <= -65) {
                            index2 = index6;
                        }
                    }
                    return -1;
                }
            }
            index2 = index;
            return partialIsValidUtf8(buffer, index2, limit);
        }

        private static int partialIsValidUtf8(ByteBuffer buffer, int index, int limit) {
            int index2 = index + Utf8.estimateConsecutiveAscii(buffer, index, limit);
            while (index2 < limit) {
                int index3 = index2 + 1;
                int index4 = buffer.get(index2);
                if (index4 >= 0) {
                    index2 = index3;
                } else if (index4 < -32) {
                    if (index3 >= limit) {
                        return index4;
                    }
                    if (index4 < -62 || buffer.get(index3) > -65) {
                        return -1;
                    }
                    index2 = index3 + 1;
                } else if (index4 < -16) {
                    if (index3 >= limit - 1) {
                        return Utf8.incompleteStateFor(buffer, index4, index3, limit - index3);
                    }
                    int index5 = index3 + 1;
                    int index6 = buffer.get(index3);
                    if (index6 > -65 || ((index4 == -32 && index6 < -96) || ((index4 == -19 && index6 >= -96) || buffer.get(index5) > -65))) {
                        return -1;
                    }
                    index2 = index5 + 1;
                } else if (index3 >= limit - 2) {
                    return Utf8.incompleteStateFor(buffer, index4, index3, limit - index3);
                } else {
                    int index7 = index3 + 1;
                    int index8 = buffer.get(index3);
                    if (index8 <= -65 && (((index4 << 28) + (index8 + 112)) >> 30) == 0) {
                        int index9 = index7 + 1;
                        if (buffer.get(index7) <= -65) {
                            index2 = index9 + 1;
                            if (buffer.get(index9) > -65) {
                            }
                        }
                    }
                    return -1;
                }
            }
            return 0;
        }

        final void encodeUtf8(CharSequence in, ByteBuffer out) {
            if (out.hasArray()) {
                int offset = out.arrayOffset();
                int endIndex = Utf8.encode(in, out.array(), out.position() + offset, out.remaining());
                out.position(endIndex - offset);
            } else if (out.isDirect()) {
                encodeUtf8Direct(in, out);
            } else {
                encodeUtf8Default(in, out);
            }
        }

        final void encodeUtf8Default(CharSequence in, ByteBuffer out) {
            int inLength = in.length();
            int outIx = out.position();
            int inIx = 0;
            while (inIx < inLength) {
                try {
                    char c = in.charAt(inIx);
                    if (c >= 128) {
                        break;
                    }
                    out.put(outIx + inIx, (byte) c);
                    inIx++;
                } catch (IndexOutOfBoundsException e) {
                    int badWriteIndex = out.position() + Math.max(inIx, (outIx - out.position()) + 1);
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(inIx) + " at index " + badWriteIndex);
                }
            }
            if (inIx == inLength) {
                out.position(outIx + inIx);
                return;
            }
            int outIx2 = outIx + inIx;
            while (inIx < inLength) {
                char c2 = in.charAt(inIx);
                if (c2 < 128) {
                    out.put(outIx2, (byte) c2);
                } else if (c2 < 2048) {
                    int outIx3 = outIx2 + 1;
                    try {
                        out.put(outIx2, (byte) (192 | (c2 >>> 6)));
                        out.put(outIx3, (byte) (('?' & c2) | 128));
                        outIx2 = outIx3;
                    } catch (IndexOutOfBoundsException e2) {
                        outIx = outIx3;
                        int badWriteIndex2 = out.position() + Math.max(inIx, (outIx - out.position()) + 1);
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(inIx) + " at index " + badWriteIndex2);
                    }
                } else if (c2 >= 55296 && 57343 >= c2) {
                    if (inIx + 1 != inLength) {
                        inIx++;
                        char low = in.charAt(inIx);
                        if (Character.isSurrogatePair(c2, low)) {
                            int codePoint = Character.toCodePoint(c2, low);
                            int outIx4 = outIx2 + 1;
                            try {
                                out.put(outIx2, (byte) (240 | (codePoint >>> 18)));
                                int outIx5 = outIx4 + 1;
                                out.put(outIx4, (byte) (((codePoint >>> 12) & 63) | 128));
                                int outIx6 = outIx5 + 1;
                                out.put(outIx5, (byte) (((codePoint >>> 6) & 63) | 128));
                                out.put(outIx6, (byte) ((63 & codePoint) | 128));
                                outIx2 = outIx6;
                            } catch (IndexOutOfBoundsException e3) {
                                outIx = outIx4;
                                int badWriteIndex22 = out.position() + Math.max(inIx, (outIx - out.position()) + 1);
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(inIx) + " at index " + badWriteIndex22);
                            }
                        }
                    }
                    throw new UnpairedSurrogateException(inIx, inLength);
                } else {
                    int outIx7 = outIx2 + 1;
                    out.put(outIx2, (byte) (224 | (c2 >>> '\f')));
                    outIx2 = outIx7 + 1;
                    out.put(outIx7, (byte) (((c2 >>> 6) & 63) | 128));
                    out.put(outIx2, (byte) (('?' & c2) | 128));
                }
                inIx++;
                outIx2++;
            }
            out.position(outIx2);
        }
    }

    /* loaded from: classes.dex */
    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        @Override // com.android.carsdk.protobuf.Utf8.Processor
        int partialIsValidUtf8(int state, byte[] bytes, int index, int limit) {
            int index2;
            if (state != 0) {
                if (index >= limit) {
                    return state;
                }
                int byte1 = (byte) state;
                if (byte1 < -32) {
                    if (byte1 >= -62) {
                        index2 = index + 1;
                        if (bytes[index] > -65) {
                        }
                    }
                    return -1;
                } else if (byte1 < -16) {
                    int byte2 = (byte) (~(state >> 8));
                    if (byte2 == 0) {
                        int index3 = index + 1;
                        byte2 = bytes[index];
                        if (index3 >= limit) {
                            return Utf8.incompleteStateFor(byte1, byte2);
                        }
                        index = index3;
                    }
                    if (byte2 <= -65 && ((byte1 != -32 || byte2 >= -96) && (byte1 != -19 || byte2 < -96))) {
                        index2 = index + 1;
                        if (bytes[index] > -65) {
                        }
                    }
                    return -1;
                } else {
                    int byte22 = (byte) (~(state >> 8));
                    int byte3 = 0;
                    if (byte22 == 0) {
                        int index4 = index + 1;
                        byte22 = bytes[index];
                        if (index4 >= limit) {
                            return Utf8.incompleteStateFor(byte1, byte22);
                        }
                        index = index4;
                    } else {
                        byte3 = (byte) (state >> 16);
                    }
                    if (byte3 == 0) {
                        int index5 = index + 1;
                        byte3 = bytes[index];
                        if (index5 >= limit) {
                            return Utf8.incompleteStateFor(byte1, byte22, byte3);
                        }
                        index = index5;
                    }
                    if (byte22 <= -65 && (((byte1 << 28) + (byte22 + 112)) >> 30) == 0 && byte3 <= -65) {
                        int index6 = index + 1;
                        if (bytes[index] <= -65) {
                            index2 = index6;
                        }
                    }
                    return -1;
                }
            }
            index2 = index;
            return partialIsValidUtf8(bytes, index2, limit);
        }

        @Override // com.android.carsdk.protobuf.Utf8.Processor
        int partialIsValidUtf8Direct(int state, ByteBuffer buffer, int index, int limit) {
            return partialIsValidUtf8Default(state, buffer, index, limit);
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0025, code lost:
            return r16 + r1;
         */
        @Override // com.android.carsdk.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int encodeUtf8(java.lang.CharSequence r14, byte[] r15, int r16, int r17) {
            /*
                Method dump skipped, instructions count: 267
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.carsdk.protobuf.Utf8.SafeProcessor.encodeUtf8(java.lang.CharSequence, byte[], int, int):int");
        }

        @Override // com.android.carsdk.protobuf.Utf8.Processor
        void encodeUtf8Direct(CharSequence in, ByteBuffer out) {
            encodeUtf8Default(in, out);
        }

        private static int partialIsValidUtf8(byte[] bytes, int index, int limit) {
            while (index < limit && bytes[index] >= 0) {
                index++;
            }
            if (index >= limit) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bytes, index, limit);
        }

        private static int partialIsValidUtf8NonAscii(byte[] bytes, int index, int limit) {
            while (index < limit) {
                int index2 = index + 1;
                int index3 = bytes[index];
                if (index3 >= 0) {
                    index = index2;
                } else if (index3 < -32) {
                    if (index2 >= limit) {
                        return index3;
                    }
                    if (index3 >= -62) {
                        index = index2 + 1;
                        if (bytes[index2] > -65) {
                        }
                    }
                    return -1;
                } else if (index3 < -16) {
                    if (index2 >= limit - 1) {
                        return Utf8.incompleteStateFor(bytes, index2, limit);
                    }
                    int index4 = index2 + 1;
                    int index5 = bytes[index2];
                    if (index5 <= -65 && ((index3 != -32 || index5 >= -96) && (index3 != -19 || index5 < -96))) {
                        index = index4 + 1;
                        if (bytes[index4] > -65) {
                        }
                    }
                    return -1;
                } else {
                    int index6 = limit - 2;
                    if (index2 >= index6) {
                        return Utf8.incompleteStateFor(bytes, index2, limit);
                    }
                    int index7 = index2 + 1;
                    int index8 = bytes[index2];
                    if (index8 <= -65 && (((index3 << 28) + (index8 + 112)) >> 30) == 0) {
                        int index9 = index7 + 1;
                        if (bytes[index7] <= -65) {
                            index = index9 + 1;
                            if (bytes[index9] > -65) {
                            }
                        }
                    }
                    return -1;
                }
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class UnsafeProcessor extends Processor {
        private static final boolean AVAILABLE;
        private static final Unsafe UNSAFE = getUnsafe();
        private static final long BUFFER_ADDRESS_OFFSET = fieldOffset(field(Buffer.class, "address"));
        private static final int ARRAY_BASE_OFFSET = byteArrayBaseOffset();

        UnsafeProcessor() {
        }

        static {
            AVAILABLE = BUFFER_ADDRESS_OFFSET != -1 && ARRAY_BASE_OFFSET % 8 == 0;
        }

        static boolean isAvailable() {
            return AVAILABLE;
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0032, code lost:
            if (com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE.getByte(r18, r2) > (-65)) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0068, code lost:
            if (com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE.getByte(r18, r2) > (-65)) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00b3, code lost:
            if (com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE.getByte(r18, r2) > (-65)) goto L58;
         */
        @Override // com.android.carsdk.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int partialIsValidUtf8(int r17, byte[] r18, int r19, int r20) {
            /*
                Method dump skipped, instructions count: 230
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.carsdk.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0035, code lost:
            if (com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE.getByte(r2) > (-65)) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x006b, code lost:
            if (com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE.getByte(r2) > (-65)) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00b6, code lost:
            if (com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE.getByte(r2) > (-65)) goto L58;
         */
        @Override // com.android.carsdk.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        int partialIsValidUtf8Direct(int r17, java.nio.ByteBuffer r18, int r19, int r20) {
            /*
                Method dump skipped, instructions count: 236
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.carsdk.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8Direct(int, java.nio.ByteBuffer, int, int):int");
        }

        @Override // com.android.carsdk.protobuf.Utf8.Processor
        int encodeUtf8(CharSequence in, byte[] out, int offset, int length) {
            char c;
            long j;
            char c2;
            char c3;
            long outIx = ARRAY_BASE_OFFSET + offset;
            long outLimit = length + outIx;
            int inLimit = in.length();
            if (inLimit > length || out.length - length < offset) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(inLimit - 1) + " at index " + (offset + length));
            }
            int inIx = 0;
            while (true) {
                c = 128;
                j = 1;
                if (inIx >= inLimit || (c3 = in.charAt(inIx)) >= 128) {
                    break;
                }
                UNSAFE.putByte(out, outIx, (byte) c3);
                inIx++;
                outIx = 1 + outIx;
            }
            if (inIx == inLimit) {
                return (int) (outIx - ARRAY_BASE_OFFSET);
            }
            while (inIx < inLimit) {
                char c4 = in.charAt(inIx);
                if (c4 < c && outIx < outLimit) {
                    UNSAFE.putByte(out, outIx, (byte) c4);
                    outIx += j;
                } else if (c4 < 2048 && outIx <= outLimit - 2) {
                    long outIx2 = outIx + j;
                    UNSAFE.putByte(out, outIx, (byte) (960 | (c4 >>> 6)));
                    UNSAFE.putByte(out, outIx2, (byte) (('?' & c4) | 128));
                    outIx = outIx2 + 1;
                } else if ((c4 >= 55296 && 57343 >= c4) || outIx > outLimit - 3) {
                    if (outIx > outLimit - 4) {
                        if (55296 > c4 || c4 > 57343 || (inIx + 1 != inLimit && Character.isSurrogatePair(c4, in.charAt(inIx + 1)))) {
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + c4 + " at index " + outIx);
                        }
                        throw new UnpairedSurrogateException(inIx, inLimit);
                    }
                    if (inIx + 1 != inLimit) {
                        inIx++;
                        char low = in.charAt(inIx);
                        if (Character.isSurrogatePair(c4, low)) {
                            int codePoint = Character.toCodePoint(c4, low);
                            long outIx3 = outIx + 1;
                            UNSAFE.putByte(out, outIx, (byte) (240 | (codePoint >>> 18)));
                            long outIx4 = outIx3 + 1;
                            c2 = 128;
                            UNSAFE.putByte(out, outIx3, (byte) (128 | (63 & (codePoint >>> 12))));
                            long outIx5 = outIx4 + 1;
                            UNSAFE.putByte(out, outIx4, (byte) (128 | (63 & (codePoint >>> 6))));
                            outIx = outIx5 + 1;
                            UNSAFE.putByte(out, outIx5, (byte) (128 | (63 & codePoint)));
                            inIx++;
                            c = c2;
                            j = 1;
                        }
                    }
                    throw new UnpairedSurrogateException(inIx - 1, inLimit);
                } else {
                    long outIx6 = outIx + 1;
                    UNSAFE.putByte(out, outIx, (byte) (480 | (c4 >>> '\f')));
                    long outIx7 = outIx6 + 1;
                    UNSAFE.putByte(out, outIx6, (byte) (128 | ((c4 >>> 6) & 63)));
                    UNSAFE.putByte(out, outIx7, (byte) (128 | ('?' & c4)));
                    outIx = outIx7 + 1;
                }
                c2 = 128;
                inIx++;
                c = c2;
                j = 1;
            }
            return (int) (outIx - ARRAY_BASE_OFFSET);
        }

        @Override // com.android.carsdk.protobuf.Utf8.Processor
        void encodeUtf8Direct(CharSequence in, ByteBuffer out) {
            char c;
            long j;
            long address;
            long j2;
            char c2;
            char c3;
            long address2 = addressOffset(out);
            long outIx = out.position() + address2;
            long outLimit = out.limit() + address2;
            int inLimit = in.length();
            if (inLimit > outLimit - outIx) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(inLimit - 1) + " at index " + out.limit());
            }
            int inIx = 0;
            while (true) {
                c = 128;
                j = 1;
                if (inIx >= inLimit || (c3 = in.charAt(inIx)) >= 128) {
                    break;
                }
                UNSAFE.putByte(outIx, (byte) c3);
                inIx++;
                outIx = 1 + outIx;
            }
            if (inIx == inLimit) {
                out.position((int) (outIx - address2));
                return;
            }
            while (inIx < inLimit) {
                char c4 = in.charAt(inIx);
                if (c4 >= c || outIx >= outLimit) {
                    if (c4 >= 2048 || outIx > outLimit - 2) {
                        if (c4 >= 55296 && 57343 >= c4) {
                            address = address2;
                        } else if (outIx <= outLimit - 3) {
                            long outIx2 = outIx + 1;
                            UNSAFE.putByte(outIx, (byte) (480 | (c4 >>> '\f')));
                            address = address2;
                            long outIx3 = outIx2 + 1;
                            UNSAFE.putByte(outIx2, (byte) (((c4 >>> 6) & 63) | 128));
                            UNSAFE.putByte(outIx3, (byte) (('?' & c4) | 128));
                            outIx = outIx3 + 1;
                        } else {
                            address = address2;
                        }
                        if (outIx > outLimit - 4) {
                            if (55296 <= c4 && c4 <= 57343 && (inIx + 1 == inLimit || !Character.isSurrogatePair(c4, in.charAt(inIx + 1)))) {
                                throw new UnpairedSurrogateException(inIx, inLimit);
                            }
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + c4 + " at index " + outIx);
                        }
                        if (inIx + 1 != inLimit) {
                            inIx++;
                            char low = in.charAt(inIx);
                            if (Character.isSurrogatePair(c4, low)) {
                                int codePoint = Character.toCodePoint(c4, low);
                                j2 = 1;
                                long outIx4 = outIx + 1;
                                UNSAFE.putByte(outIx, (byte) (240 | (codePoint >>> 18)));
                                long outIx5 = outIx4 + 1;
                                c2 = 128;
                                UNSAFE.putByte(outIx4, (byte) (128 | (63 & (codePoint >>> 12))));
                                long outIx6 = outIx5 + 1;
                                UNSAFE.putByte(outIx5, (byte) (128 | (63 & (codePoint >>> 6))));
                                outIx = outIx6 + 1;
                                UNSAFE.putByte(outIx6, (byte) (128 | (63 & codePoint)));
                                inIx++;
                                j = j2;
                                c = c2;
                                address2 = address;
                            }
                        }
                        throw new UnpairedSurrogateException(inIx - 1, inLimit);
                    }
                    long outIx7 = outIx + j;
                    UNSAFE.putByte(outIx, (byte) (960 | (c4 >>> 6)));
                    UNSAFE.putByte(outIx7, (byte) (('?' & c4) | 128));
                    address = address2;
                    outIx = outIx7 + 1;
                    j2 = 1;
                } else {
                    UNSAFE.putByte(outIx, (byte) c4);
                    address = address2;
                    j2 = j;
                    outIx += j;
                }
                c2 = 128;
                inIx++;
                j = j2;
                c = c2;
                address2 = address;
            }
            out.position((int) (outIx - address2));
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bytes, long offset, int maxChars) {
            if (maxChars < 16) {
                return 0;
            }
            int unaligned = ((int) offset) & 7;
            long offset2 = offset;
            int j = unaligned;
            while (j > 0) {
                long offset3 = 1 + offset2;
                if (UNSAFE.getByte(bytes, offset2) >= 0) {
                    j--;
                    offset2 = offset3;
                } else {
                    return unaligned - j;
                }
            }
            int remaining = maxChars - unaligned;
            while (remaining >= 8 && (UNSAFE.getLong(bytes, offset2) & Utf8.ASCII_MASK_LONG) == 0) {
                offset2 += 8;
                remaining -= 8;
            }
            return maxChars - remaining;
        }

        private static int unsafeEstimateConsecutiveAscii(long address, int maxChars) {
            if (maxChars < 16) {
                return 0;
            }
            int unaligned = ((int) address) & 7;
            long address2 = address;
            int j = unaligned;
            while (j > 0) {
                long address3 = 1 + address2;
                if (UNSAFE.getByte(address2) >= 0) {
                    j--;
                    address2 = address3;
                } else {
                    return unaligned - j;
                }
            }
            int remaining = maxChars - unaligned;
            while (remaining >= 8 && (UNSAFE.getLong(address2) & Utf8.ASCII_MASK_LONG) == 0) {
                address2 += 8;
                remaining -= 8;
            }
            return maxChars - remaining;
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0041, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0073, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00ab, code lost:
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static int partialIsValidUtf8(byte[] r11, long r12, int r14) {
            /*
                int r0 = unsafeEstimateConsecutiveAscii(r11, r12, r14)
                int r14 = r14 - r0
                long r1 = (long) r0
                long r12 = r12 + r1
            L7:
                r1 = 0
                r2 = r12
                r12 = r1
            La:
                r4 = 1
                if (r14 <= 0) goto L1e
                sun.misc.Unsafe r13 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r6 = r2 + r4
                byte r13 = r13.getByte(r11, r2)
                r12 = r13
                if (r13 < 0) goto L1d
                int r14 = r14 + (-1)
                r2 = r6
                goto La
            L1d:
                r2 = r6
            L1e:
                if (r14 != 0) goto L21
                return r1
            L21:
                int r14 = r14 + (-1)
                r13 = -32
                r1 = -65
                r6 = -1
                if (r12 >= r13) goto L42
                if (r14 != 0) goto L2d
                return r12
            L2d:
                int r14 = r14 + (-1)
                r13 = -62
                if (r12 < r13) goto L41
                sun.misc.Unsafe r13 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r4 = r4 + r2
                byte r13 = r13.getByte(r11, r2)
                if (r13 <= r1) goto L3e
                r2 = r4
                goto L41
            L3e:
                r12 = r4
                goto La8
            L41:
                return r6
            L42:
                r7 = -16
                if (r12 >= r7) goto L74
                r7 = 2
                if (r14 >= r7) goto L4e
                int r13 = unsafeIncompleteStateFor(r11, r12, r2, r14)
                return r13
            L4e:
                int r14 = r14 + (-2)
                sun.misc.Unsafe r7 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r8 = r2 + r4
                byte r2 = r7.getByte(r11, r2)
                r3 = r2
                if (r2 > r1) goto L72
                r2 = -96
                if (r12 != r13) goto L61
                if (r3 < r2) goto L72
            L61:
                r13 = -19
                if (r12 != r13) goto L67
                if (r3 >= r2) goto L72
            L67:
                sun.misc.Unsafe r13 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r4 = r4 + r8
                byte r13 = r13.getByte(r11, r8)
                if (r13 <= r1) goto L71
                goto L73
            L71:
                goto L3e
            L72:
                r4 = r8
            L73:
                return r6
            L74:
                r13 = 3
                if (r14 >= r13) goto L7c
                int r13 = unsafeIncompleteStateFor(r11, r12, r2, r14)
                return r13
            L7c:
                int r14 = r14 + (-3)
                sun.misc.Unsafe r13 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r7 = r2 + r4
                byte r13 = r13.getByte(r11, r2)
                r2 = r13
                if (r13 > r1) goto Lab
                int r13 = r12 << 28
                int r3 = r2 + 112
                int r13 = r13 + r3
                int r13 = r13 >> 30
                if (r13 != 0) goto Lab
                sun.misc.Unsafe r13 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r9 = r7 + r4
                byte r13 = r13.getByte(r11, r7)
                if (r13 > r1) goto Laa
                sun.misc.Unsafe r13 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r7 = r9 + r4
                byte r13 = r13.getByte(r11, r9)
                if (r13 <= r1) goto La7
                goto Lab
            La7:
                r12 = r7
            La8:
                goto L7
            Laa:
                r7 = r9
            Lab:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.carsdk.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(byte[], long, int):int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0041, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0075, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00ac, code lost:
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static int partialIsValidUtf8(long r11, int r13) {
            /*
                int r0 = unsafeEstimateConsecutiveAscii(r11, r13)
                long r1 = (long) r0
                long r11 = r11 + r1
                int r13 = r13 - r0
            L7:
                r1 = 0
                r2 = r11
                r11 = r1
            La:
                r4 = 1
                if (r13 <= 0) goto L1e
                sun.misc.Unsafe r12 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r6 = r2 + r4
                byte r12 = r12.getByte(r2)
                r11 = r12
                if (r12 < 0) goto L1d
                int r13 = r13 + (-1)
                r2 = r6
                goto La
            L1d:
                r2 = r6
            L1e:
                if (r13 != 0) goto L21
                return r1
            L21:
                int r13 = r13 + (-1)
                r12 = -32
                r1 = -65
                r6 = -1
                if (r11 >= r12) goto L42
                if (r13 != 0) goto L2d
                return r11
            L2d:
                int r13 = r13 + (-1)
                r12 = -62
                if (r11 < r12) goto L41
                sun.misc.Unsafe r12 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r4 = r4 + r2
                byte r12 = r12.getByte(r2)
                if (r12 <= r1) goto L3e
                r2 = r4
                goto L41
            L3e:
                r11 = r4
                goto La9
            L41:
                return r6
            L42:
                r7 = -16
                if (r11 >= r7) goto L76
                r7 = 2
                if (r13 >= r7) goto L4e
                int r12 = unsafeIncompleteStateFor(r2, r11, r13)
                return r12
            L4e:
                int r13 = r13 + (-2)
                sun.misc.Unsafe r7 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r8 = r2 + r4
                byte r2 = r7.getByte(r2)
                if (r2 > r1) goto L74
                r3 = -96
                if (r11 != r12) goto L60
                if (r2 < r3) goto L74
            L60:
                r12 = -19
                if (r11 != r12) goto L66
                if (r2 >= r3) goto L74
            L66:
                sun.misc.Unsafe r12 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r3 = r8 + r4
                byte r12 = r12.getByte(r8)
                if (r12 <= r1) goto L71
                goto L75
            L71:
                r11 = r3
                goto La9
            L74:
                r3 = r8
            L75:
                return r6
            L76:
                r12 = 3
                if (r13 >= r12) goto L7e
                int r12 = unsafeIncompleteStateFor(r2, r11, r13)
                return r12
            L7e:
                int r13 = r13 + (-3)
                sun.misc.Unsafe r12 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r7 = r2 + r4
                byte r12 = r12.getByte(r2)
                if (r12 > r1) goto Lac
                int r2 = r11 << 28
                int r3 = r12 + 112
                int r2 = r2 + r3
                int r2 = r2 >> 30
                if (r2 != 0) goto Lac
                sun.misc.Unsafe r2 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r9 = r7 + r4
                byte r2 = r2.getByte(r7)
                if (r2 > r1) goto Lab
                sun.misc.Unsafe r2 = com.android.carsdk.protobuf.Utf8.UnsafeProcessor.UNSAFE
                long r7 = r9 + r4
                byte r2 = r2.getByte(r9)
                if (r2 <= r1) goto La8
                goto Lac
            La8:
                r11 = r7
            La9:
                goto L7
            Lab:
                r7 = r9
            Lac:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.carsdk.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(long, int):int");
        }

        private static int unsafeIncompleteStateFor(byte[] bytes, int byte1, long offset, int remaining) {
            switch (remaining) {
                case 0:
                    return Utf8.incompleteStateFor(byte1);
                case 1:
                    return Utf8.incompleteStateFor(byte1, UNSAFE.getByte(bytes, offset));
                case 2:
                    return Utf8.incompleteStateFor(byte1, UNSAFE.getByte(bytes, offset), UNSAFE.getByte(bytes, 1 + offset));
                default:
                    throw new AssertionError();
            }
        }

        private static int unsafeIncompleteStateFor(long address, int byte1, int remaining) {
            switch (remaining) {
                case 0:
                    return Utf8.incompleteStateFor(byte1);
                case 1:
                    return Utf8.incompleteStateFor(byte1, UNSAFE.getByte(address));
                case 2:
                    return Utf8.incompleteStateFor(byte1, UNSAFE.getByte(address), UNSAFE.getByte(1 + address));
                default:
                    throw new AssertionError();
            }
        }

        private static Field field(Class<?> clazz, String fieldName) {
            Field field;
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
            } catch (Throwable th) {
                field = null;
            }
            Logger logger = Utf8.logger;
            Level level = Level.FINEST;
            Object[] objArr = new Object[3];
            objArr[0] = clazz.getName();
            objArr[1] = fieldName;
            objArr[2] = field != null ? "available" : "unavailable";
            logger.log(level, "{0}.{1}: {2}", objArr);
            return field;
        }

        private static long fieldOffset(Field field) {
            if (field == null || UNSAFE == null) {
                return -1L;
            }
            return UNSAFE.objectFieldOffset(field);
        }

        private static <T> int byteArrayBaseOffset() {
            if (UNSAFE == null) {
                return -1;
            }
            return UNSAFE.arrayBaseOffset(byte[].class);
        }

        private static long addressOffset(ByteBuffer buffer) {
            return UNSAFE.getLong(buffer, BUFFER_ADDRESS_OFFSET);
        }

        private static Unsafe getUnsafe() {
            Unsafe unsafe = null;
            try {
                unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.android.carsdk.protobuf.Utf8.UnsafeProcessor.1
                    @Override // java.security.PrivilegedExceptionAction
                    public Unsafe run() throws Exception {
                        Field[] declaredFields;
                        UnsafeProcessor.checkRequiredMethods(Unsafe.class);
                        for (Field f : Unsafe.class.getDeclaredFields()) {
                            f.setAccessible(true);
                            Object x = f.get(null);
                            if (Unsafe.class.isInstance(x)) {
                                return (Unsafe) Unsafe.class.cast(x);
                            }
                        }
                        return null;
                    }
                });
            } catch (Throwable th) {
            }
            Utf8.logger.log(Level.FINEST, "sun.misc.Unsafe: {}", unsafe != null ? "available" : "unavailable");
            return unsafe;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void checkRequiredMethods(Class<Unsafe> clazz) throws NoSuchMethodException, SecurityException {
            clazz.getMethod("arrayBaseOffset", Class.class);
            clazz.getMethod("getByte", Object.class, Long.TYPE);
            clazz.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            clazz.getMethod("getLong", Object.class, Long.TYPE);
            clazz.getMethod("objectFieldOffset", Field.class);
            clazz.getMethod("getByte", Long.TYPE);
            clazz.getMethod("getLong", Object.class, Long.TYPE);
            clazz.getMethod("putByte", Long.TYPE, Byte.TYPE);
            clazz.getMethod("getLong", Long.TYPE);
        }
    }

    private Utf8() {
    }
}
