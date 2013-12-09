ExceptionThrowingPrintWriter
============================


 By design PrintWriters never throw exceptions from their write methods.
 This means the caller has to explicitly call checkError() to find out if  there is an error, eg: the underlying
 socket is closed.

 This class wraps a PrintWriter so clients don't have to do this, and instead errors are checked on each write
 and an IOException is thrown is the underlying PrintWriter is in error. NB: Passing this class to another
 PrintWriter won't work, because that PrintWriter will ignore the errors this class throws!

 Because checking for errors cases a flush, the flush interval (ie: number of writes before checking for errors
 and hence flushing) can be specified.