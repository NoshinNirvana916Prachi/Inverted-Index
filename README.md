# Inverted-Index

#### Sequence diagram 

![InvertedIndex](https://github.com/NoshinNirvana916Prachi/Inverted-Index/assets/58959257/7cdd3da5-47f9-497b-a96c-8d89d8ed8dd7)

In this sequence diagram:
1. The RMI client looks up the RMI registry for the InvertedIndexService.
2. The RMI service receives the request and divides the file processing into concurrent tasks.
3. The RMI service creates an ExecutorService (either ForkJoinPool or Executors) to manage the concurrent execution of tasks.
4. The ExecutorService processes the submitted tasks in parallel or concurrently.
5.As tasks complete, the RMI service collects the results from the completed tasks.
6. The RMI service combines the results from all tasks into the final inverted index.
and, The RMI service returns the inverted index to the RMI client.
7. The RMI client receives the inverted index and processes it to display the top 5 tokens with their locations.
