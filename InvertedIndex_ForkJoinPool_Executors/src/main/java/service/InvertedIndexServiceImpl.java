package service;

//InvertedIndexServiceImpl.java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class InvertedIndexServiceImpl extends UnicastRemoteObject implements InvertedIndexService {
 private static final long serialVersionUID = 1L;

 public InvertedIndexServiceImpl() throws RemoteException {
     super();
 }

 @Override
 public Map<String, List<Integer>> getInvertedIndex(String fileName) throws RemoteException {
     // Using ForkJoinPool
     ForkJoinPool forkJoinPool = new ForkJoinPool();
     Map<String, List<Integer>> resultForkJoin = forkJoinPool.invoke(new IndexingTask(fileName));

     // Using Executors with Callable
     ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
     Map<String, List<Integer>> resultExecutors = null;
     try {
         resultExecutors = executorService.submit(new IndexingCallable(fileName)).get();
     } catch (InterruptedException | ExecutionException e) {
         e.printStackTrace();
     } finally {
         executorService.shutdown();
     }

     // Returning results from either approach
     return resultForkJoin != null ? resultForkJoin : resultExecutors;
 }

 private static class IndexingTask extends RecursiveTask<Map<String, List<Integer>>> {
     private static final long serialVersionUID = 1L;
     private final String fileName;

     public IndexingTask(String fileName) {
         this.fileName = fileName;
     }

     @Override
     protected Map<String, List<Integer>> compute() {
         // Implement inverted index logic using ForkJoinPool
         // Return the result
         return new ConcurrentHashMap<>(); // Dummy implementation
     }
 }

 private static class IndexingCallable implements Callable<Map<String, List<Integer>>> {
     private final String fileName;

     public IndexingCallable(String fileName) {
         this.fileName = fileName;
     }

     @Override
     public Map<String, List<Integer>> call() {
         // Implement inverted index logic using Executors with Callable
         // Return the result
         return new ConcurrentHashMap<>(); // Dummy implementation
     }
 }
}
