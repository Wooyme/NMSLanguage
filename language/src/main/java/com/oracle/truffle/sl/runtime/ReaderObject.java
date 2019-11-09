package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;

import java.io.BufferedReader;
import java.io.Reader;

@ExportLibrary(InteropLibrary.class)
public final class ReaderObject implements TruffleObject {
    private final BufferedReader reader;
    public ReaderObject(BufferedReader reader){
        this.reader = reader;
    }
    public Reader getValue() {
        return this.reader;
    }
}
