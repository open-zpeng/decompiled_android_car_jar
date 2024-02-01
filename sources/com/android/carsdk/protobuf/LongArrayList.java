package com.android.carsdk.protobuf;

import com.android.carsdk.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes.dex */
final class LongArrayList extends AbstractProtobufList<Long> implements Internal.LongList, RandomAccess {
    private static final LongArrayList EMPTY_LIST = new LongArrayList();
    private long[] array;
    private int size;

    static {
        EMPTY_LIST.makeImmutable();
    }

    public static LongArrayList emptyList() {
        return EMPTY_LIST;
    }

    LongArrayList() {
        this(new long[10], 0);
    }

    private LongArrayList(long[] array, int size) {
        this.array = array;
        this.size = size;
    }

    @Override // com.android.carsdk.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IntArrayList)) {
            return super.equals(o);
        }
        LongArrayList other = (LongArrayList) o;
        if (this.size != other.size) {
            return false;
        }
        long[] arr = other.array;
        for (int i = 0; i < this.size; i++) {
            if (this.array[i] != arr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.carsdk.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < this.size; i++) {
            result = (31 * result) + Internal.hashLong(this.array[i]);
        }
        return result;
    }

    @Override // com.android.carsdk.protobuf.Internal.ProtobufList, com.android.carsdk.protobuf.Internal.BooleanList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Long> mutableCopyWithCapacity2(int capacity) {
        if (capacity < this.size) {
            throw new IllegalArgumentException();
        }
        return new LongArrayList(Arrays.copyOf(this.array, capacity), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public Long get(int index) {
        return Long.valueOf(getLong(index));
    }

    @Override // com.android.carsdk.protobuf.Internal.LongList
    public long getLong(int index) {
        ensureIndexInRange(index);
        return this.array[index];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    @Override // com.android.carsdk.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Long set(int index, Long element) {
        return Long.valueOf(setLong(index, element.longValue()));
    }

    @Override // com.android.carsdk.protobuf.Internal.LongList
    public long setLong(int index, long element) {
        ensureIsMutable();
        ensureIndexInRange(index);
        long previousValue = this.array[index];
        this.array[index] = element;
        return previousValue;
    }

    @Override // com.android.carsdk.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int index, Long element) {
        addLong(index, element.longValue());
    }

    @Override // com.android.carsdk.protobuf.Internal.LongList
    public void addLong(long element) {
        addLong(this.size, element);
    }

    private void addLong(int index, long element) {
        ensureIsMutable();
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(index));
        }
        if (this.size < this.array.length) {
            System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
        } else {
            int length = ((this.size * 3) / 2) + 1;
            long[] newArray = new long[length];
            System.arraycopy(this.array, 0, newArray, 0, index);
            System.arraycopy(this.array, index, newArray, index + 1, this.size - index);
            this.array = newArray;
        }
        this.array[index] = element;
        this.size++;
        this.modCount++;
    }

    @Override // com.android.carsdk.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Long> collection) {
        ensureIsMutable();
        if (collection == null) {
            throw new NullPointerException();
        }
        if (!(collection instanceof LongArrayList)) {
            return super.addAll(collection);
        }
        LongArrayList list = (LongArrayList) collection;
        if (list.size == 0) {
            return false;
        }
        int overflow = Integer.MAX_VALUE - this.size;
        if (overflow < list.size) {
            throw new OutOfMemoryError();
        }
        int newSize = this.size + list.size;
        if (newSize > this.array.length) {
            this.array = Arrays.copyOf(this.array, newSize);
        }
        System.arraycopy(list.array, 0, this.array, this.size, list.size);
        this.size = newSize;
        this.modCount++;
        return true;
    }

    @Override // com.android.carsdk.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object o) {
        ensureIsMutable();
        for (int i = 0; i < this.size; i++) {
            if (o.equals(Long.valueOf(this.array[i]))) {
                System.arraycopy(this.array, i + 1, this.array, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // com.android.carsdk.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Long remove(int index) {
        ensureIsMutable();
        ensureIndexInRange(index);
        long value = this.array[index];
        System.arraycopy(this.array, index + 1, this.array, index, this.size - index);
        this.size--;
        this.modCount++;
        return Long.valueOf(value);
    }

    private void ensureIndexInRange(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(index));
        }
    }

    private String makeOutOfBoundsExceptionMessage(int index) {
        return "Index:" + index + ", Size:" + this.size;
    }
}