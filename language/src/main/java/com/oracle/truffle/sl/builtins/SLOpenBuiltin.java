package com.oracle.truffle.sl.builtins;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.interop.ArityException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.interop.UnsupportedTypeException;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.sl.SLLanguage;
import com.oracle.truffle.sl.runtime.SLContext;
import com.oracle.truffle.sl.runtime.SLFunction;
import com.oracle.truffle.sl.runtime.SLNull;
import com.oracle.truffle.sl.runtime.SLUndefinedNameException;
import com.oracle.truffle.sl.utils.ServerSocketFactory;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@NodeInfo(shortName = "open")
public abstract class SLOpenBuiltin extends SLBuiltinNode {

    @Specialization
    public Object open(String path, String op
            , @CachedContext(SLLanguage.class) SLContext context
            , @Cached("context.getAllocationReporter()") AllocationReporter reporter) {
        Object result = null;
        switch (op) {
            case "<":
                if (path.startsWith("file:") || path.startsWith("http:") || path.startsWith("https:"))
                    result = getReaderCommon(path);
                if (path.startsWith("shell:"))
                    result = getShell(path);
                break;
            case ">":
                if (path.startsWith("file:")) {
                    result = getFileWriter(path, false, false);
                }
                break;
            case ">+":
                if (path.startsWith("file:")) {
                    result = getFileWriter(path, false, true);
                }
                break;
            case ">>":
                if (path.startsWith("file:")) {
                    result = getFileWriter(path, true, false);
                }
                break;
            case ">>+":
                if (path.startsWith("file:")) {
                    result = getFileWriter(path, true, true);
                }
                break;
            case "<>":
                if (path.startsWith("tcp:")) {
                    result = getBlockingTcpClient(path, context, reporter);
                }
                break;
        }
        if (result == null) {
            result = SLNull.SINGLETON;
        }
        return result;
    }
    @Specialization(limit = "3")
    public Object open(String path, SLFunction function
            , @CachedLibrary("function") InteropLibrary library
            , @CachedContext(SLLanguage.class) SLContext context
            , @Cached("context.getAllocationReporter()") AllocationReporter reporter) {
        Object result = null;
        if(path.startsWith("tcp:")){
            result = getNonBlockingTcpSever(path,context,reporter,function,library);
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
    private Object getFileWriter(String path, boolean isAppend, boolean create) {
        try {
            File file = new File(new URL(path).getFile());
            if (create && !file.exists()) {
                if (!file.createNewFile()) {
                    return SLNull.SINGLETON;
                }
            }
            return new BufferedWriter(new FileWriter(new URL(path).getFile(), isAppend));
        } catch (IOException e) {
            return SLNull.SINGLETON;
        }
    }

    private Object getShell(String path) {
        String cmd = path.substring(6);
        try {
            final Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            p.waitFor();
            return reader;
        } catch (IOException | InterruptedException e) {
            return SLNull.SINGLETON;
        }
    }

    private Object getBlockingTcpClient(String path, SLContext context, AllocationReporter reporter) {
        String address = path.substring(4, path.lastIndexOf(":"));
        int port = Integer.valueOf(path.substring(path.lastIndexOf(":") + 1));
        try {
            Socket socket = new Socket(address, port);
            DynamicObject obj = context.createObject(reporter);
            obj.define("in", new BufferedReader(new InputStreamReader(socket.getInputStream())));
            obj.define("out", new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            return obj;
        } catch (IOException e) {
            return SLNull.SINGLETON;
        }
    }

    private Object getNonBlockingTcpSever(String path, SLContext context, AllocationReporter reporter, SLFunction function, InteropLibrary library) {
        int port = Integer.valueOf(path.substring(path.lastIndexOf(":") + 1));
        if (ServerSocketFactory.createServerSocket(port,socketData -> {
            DynamicObject obj = context.createObject(reporter);
            obj.define("out",socketData.getOut());
            obj.define("addr",socketData.getAddress());
            obj.define("action",socketData.getAction());
            if(socketData.getBytes()!=null)
                obj.define("data", socketData.getBytes().toString(StandardCharsets.UTF_8));
            Object[] args = new Object[1];
            args[0] = obj;
            try {
                library.execute(function,args);
            } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
                throw SLUndefinedNameException.undefinedFunction(this, function);
            }
        })) {
            return "server_socket";
        } else {
            return SLNull.SINGLETON;
        }
    }
}
