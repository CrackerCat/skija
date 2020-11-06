package org.jetbrains.skija.shaper;

import org.jetbrains.annotations.*;
import org.jetbrains.skija.*;
import org.jetbrains.skija.impl.*;

public class FontMgrRunIterator extends ManagedRunIterator<FontRun> {
    static { Library.load(); }

    @ApiStatus.Internal
    public FontMgrRunIterator(ManagedString text, Font font, FontMgr fontMgr) {
        super(_nMake(Native.getPtr(text), Native.getPtr(font), Native.getPtr(fontMgr)), text);
        Stats.onNativeCall();
    }

    public FontMgrRunIterator(String text, Font font, FontMgr fontMgr) {
        this(new ManagedString(text), font, fontMgr);
    }

    @Override
    public FontRun next() {
        _nConsume(_ptr);
        return new FontRun(_getEndOfCurrentRun(), new Font(_nGetCurrentFont(_ptr)));
    }

    @ApiStatus.Internal public static native long _nMake(long textPtr, long fontPtr, long fontMgrPtr);
    @ApiStatus.Internal public static native long _nGetCurrentFont(long ptr);
}