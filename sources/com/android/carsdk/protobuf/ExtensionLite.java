package com.android.carsdk.protobuf;

import com.android.carsdk.protobuf.MessageLite;
import com.android.carsdk.protobuf.WireFormat;

/* loaded from: classes.dex */
public abstract class ExtensionLite<ContainingType extends MessageLite, Type> {
    public abstract Type getDefaultValue();

    public abstract WireFormat.FieldType getLiteType();

    public abstract MessageLite getMessageDefaultInstance();

    public abstract int getNumber();

    public abstract boolean isRepeated();

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLite() {
        return true;
    }
}
