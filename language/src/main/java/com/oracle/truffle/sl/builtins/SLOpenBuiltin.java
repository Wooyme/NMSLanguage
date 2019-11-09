package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.sl.SLException;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.runtime.SLContext;
import com.oracle.truffle.sl.runtime.SLNull;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@NodeInfo(shortName = "open")
public abstract class SLOpenBuiltin extends SLBuiltinNode {
    @Specialization
    public Object open(String path,String op,@CachedContext(SLLanguage.class) SLContext context) {
        Object result = null;

        switch (op) {
            case "<":
                if (path.startsWith("file:") || path.startsWith("http://") || path.startsWith("https://"))
                    result = getReaderCommon(path);
                break;
            case ">":
                if (path.startsWith("file:")) {
                    result = getFileWriter(path,false,false);
                }
                break;
            case ">+":
                if (path.startsWith("file:")) {
                    result = getFileWriter(path,false,true);
                }
                break;
            case ">>":
                if (path.startsWith("file:")) {
                    result = getFileWriter(path,true,false);
                }
                break;
            case ">>+":
                if (path.startsWith("file:")) {
                    result = getFileWriter(path,true,true);
                }
                break;
        }
        if (result == null) {
            result = SLNull.SINGLETON;
        }
        return result;
    }

    @TruffleBoundary
    private Object getReaderCommon(String path) {
        try {
            return new BufferedReader(new InputStreamReader(new URL(path).openStream()));
        } catch (IOException e) {
            return SLNull.SINGLETON;
        }
    }

    @TruffleBoundary
    private Object getFileWriter(String path,boolean isAppend,boolean create) {
        try {
            File file = new File(new URL(path).getFile());
            if(create && !file.exists()){
                if(!file.createNewFile()){
                    return SLNull.SINGLETON;
                }
            }
            return new BufferedWriter(new FileWriter(new URL(path).getFile(),isAppend));
        } catch (IOException e) {
            return SLNull.SINGLETON;
        }
    }

}
