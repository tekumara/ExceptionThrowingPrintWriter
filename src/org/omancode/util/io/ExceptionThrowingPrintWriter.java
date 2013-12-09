package org.omancode.util.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;

/*
 By design PrintWriters never throw exceptions from their write methods.
 This means the caller has to explicitly call checkError() to find out if  there is an error, eg: the underlying
 socket is closed.

 This class wraps a PrintWriter so clients don't have to do this, and instead errors are checked on each write
 and an IOException is thrown is the underlying PrintWriter is in error. NB: Passing this class to another
 PrintWriter won't work, because that PrintWriter will ignore the errors this class throws!

 Because checking for errors cases a flush, the flush interval (ie: number of writes before checking for errors
 and hence flushing) can be specified.
 */
public class ExceptionThrowingPrintWriter extends Writer {

    public static final int DEFAULT_FLUSH_INTERVAL = 100;
    public static final String DEFAULT_EXCEPTION_MESSAGE = "error on write %s";
    private final PrintWriter writer;

    private int count = 0;
    private final int flushInterval;
    private final String exceptionMessage;

    public ExceptionThrowingPrintWriter(PrintWriter writer) {
        this(writer, DEFAULT_FLUSH_INTERVAL);
    }

    public ExceptionThrowingPrintWriter(PrintWriter writer, int flushInterval) {
        this(writer, flushInterval, DEFAULT_EXCEPTION_MESSAGE);
    }

    public ExceptionThrowingPrintWriter(PrintWriter writer, int flushInterval, String exceptionMessage) {
        this.writer = writer;
        this.flushInterval = flushInterval;
        this.exceptionMessage = exceptionMessage;
    }

    private void checkOutputWriterNotInError() throws IOException {
        count++;

        //checkError on the underlying writer does a flush, so only check every FLUSH_INTERVAL
        if ((count % flushInterval==0) && writer.checkError()) {
            //System.err.println(String.format(exceptionMessage, count));
            throw new IOException(String.format(exceptionMessage, count));
        }
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }

    public boolean checkError() {
        return writer.checkError();
    }

    @Override
    public void write(int c) throws IOException {
        checkOutputWriterNotInError();
        writer.write(c);
    }

    @Override
    public void write(char[] buf, int off, int len) throws IOException {
        checkOutputWriterNotInError();
        writer.write(buf, off, len);
    }

    @Override
    public void write(char[] buf) throws IOException {
        checkOutputWriterNotInError();
        writer.write(buf);
    }

    @Override
    public void write(String s, int off, int len) throws IOException {
        checkOutputWriterNotInError();
        writer.write(s, off, len);
    }

    @Override
    public void write(String s) throws IOException {
        checkOutputWriterNotInError();
        writer.write(s);
    }

    public void print(boolean b) throws IOException {
        checkOutputWriterNotInError();
        writer.print(b);
    }

    public void print(char c) throws IOException {
        checkOutputWriterNotInError();
        writer.print(c);
    }

    public void print(int i) throws IOException {
        checkOutputWriterNotInError();
        writer.print(i);
    }

    public void print(long l) throws IOException {
        checkOutputWriterNotInError();
        writer.print(l);
    }

    public void print(float f) throws IOException {
        checkOutputWriterNotInError();
        writer.print(f);
    }

    public void print(double d) throws IOException {
        checkOutputWriterNotInError();
        writer.print(d);
    }

    public void print(char[] s) throws IOException {
        checkOutputWriterNotInError();
        writer.print(s);
    }

    public void print(String s) throws IOException {
        checkOutputWriterNotInError();
        writer.print(s);
    }

    public void print(Object obj) throws IOException {
        checkOutputWriterNotInError();
        writer.print(obj);
    }

    public void println() throws IOException {
        checkOutputWriterNotInError();
        writer.println();
    }

    public void println(boolean x) throws IOException {
        checkOutputWriterNotInError();
        writer.println(x);
    }

    public void println(char x) throws IOException {
        checkOutputWriterNotInError();
        writer.println(x);
    }

    public void println(int x) throws IOException {
        checkOutputWriterNotInError();
        writer.println(x);
    }

    public void println(long x) throws IOException {
        checkOutputWriterNotInError();
        writer.println(x);
    }

    public void println(float x) throws IOException {
        checkOutputWriterNotInError();
        writer.println(x);
    }

    public void println(double x) throws IOException {
        checkOutputWriterNotInError();
        writer.println(x);
    }

    public void println(char[] x) throws IOException {
        checkOutputWriterNotInError();
        writer.println(x);
    }

    public void println(String x) throws IOException {
        checkOutputWriterNotInError();
        writer.println(x);
    }

    public void println(Object x) throws IOException {
        checkOutputWriterNotInError();
        writer.println(x);
    }

    public PrintWriter printf(String format, Object... args) throws IOException {
        checkOutputWriterNotInError();
        return writer.printf(format, args);
    }

    public PrintWriter printf(Locale l, String format, Object... args) throws IOException {
        checkOutputWriterNotInError();
        return writer.printf(l, format, args);
    }

    public PrintWriter format(String format, Object... args) throws IOException {
        checkOutputWriterNotInError();
        return writer.format(format, args);
    }

    public PrintWriter format(Locale l, String format, Object... args) throws IOException {
        checkOutputWriterNotInError();
        return writer.format(l, format, args);
    }

    public PrintWriter append(CharSequence csq) throws IOException {
        checkOutputWriterNotInError();
        return writer.append(csq);
    }

    public PrintWriter append(CharSequence csq, int start, int end) throws IOException {
        checkOutputWriterNotInError();
        return writer.append(csq, start, end);
    }

    public PrintWriter append(char c) throws IOException {
        checkOutputWriterNotInError();
        return writer.append(c);
    }
}
