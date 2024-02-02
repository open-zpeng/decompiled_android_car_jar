package com.android.carsdk.protobuf;

import com.android.carsdk.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;
/* loaded from: classes.dex */
final class IntArrayList extends AbstractProtobufList<Integer> implements Internal.IntList, RandomAccess {
    private static final IntArrayList EMPTY_LIST = new IntArrayList();
    private int[] array;
    private int size;

    static {
        EMPTY_LIST.makeImmutable();
    }

    public static IntArrayList emptyList() {
        return EMPTY_LIST;
    }

    IntArrayList() {
        this(new int[10], 0);
    }

    private IntArrayList(int[] array, int size) {
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
        IntArrayList other = (IntArrayList) o;
        if (this.size != other.size) {
            return false;
        }
        int[] arr = other.array;
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
            result = (31 * result) + this.array[i];
        }
        return result;
    }

    @Override // com.android.carsdk.protobuf.Internal.ProtobufList, com.android.carsdk.protobuf.Internal.BooleanList
    /* renamed from: mutableCopyWithCapacity */
    public Internal.ProtobufList<Integer> mutableCopyWithCapacity2(int capacity) {
        if (capacity < this.size) {
            throw new IllegalArgumentException();
        }
        return new IntArrayList(Arrays.copyOf(this.array, capacity), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public Integer get(int index) {
        return Integer.valueOf(getInt(index));
    }

    @Override // com.android.carsdk.protobuf.Internal.IntList
    public int getInt(int index) {
        ensureIndexInRange(index);
        return this.array[index];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    @Override // com.android.carsdk.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Integer set(int index, Integer element) {
        return Integer.valueOf(setInt(index, element.intValue()));
    }

    @Override // com.android.carsdk.protobuf.Internal.IntList
    public int setInt(int index, int element) {
        ensureIsMutable();
        ensureIndexInRange(index);
        int previousValue = this.array[index];
        this.array[index] = element;
        return previousValue;
    }

    @Override // com.android.carsdk.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int index, Integer element) {
        addInt(index, element.intValue());
    }

    @Override // com.android.carsdk.protobuf.Internal.IntList
    public void addInt(int element) {
        addInt(this.size, element);
    }

    private void addInt(int index, int element) {
        ensureIsMutable();
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(index));
        }
        if (this.size < this.array.length) {
            System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
        } else {
            int length = ((this.size * 3) / 2) + 1;
            int[] newArray = new int[length];
            System.arraycopy(this.array, 0, newArray, 0, index);
            System.arraycopy(this.array, index, newArray, index + 1, this.size - index);
            this.array = newArray;
        }
        this.array[index] = element;
        this.size++;
        this.modCount++;
    }

    @Override // com.android.carsdk.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends Integer> collection) {
        ensureIsMutable();
        if (collection == null) {
            throw new NullPointerException();
        }
        if (!(collection instanceof IntArrayList)) {
            return super.addAll(collection);
        }
        IntArrayList list = (IntArrayList) collection;
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
            if (o.equals(Integer.valueOf(this.array[i]))) {
                System.arraycopy(this.array, i + 1, this.array, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // com.android.carsdk.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public Integer remove(int index) {
        ensureIsMutable();
        ensureIndexInRange(index);
        int value = this.array[index];
        System.arraycopy(this.array, index + 1, this.array, index, this.size - index);
        this.size--;
        this.modCount++;
        return Integer.valueOf(value);
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
