# Inverted-Index

#### Sequence diagram 



In this sequence diagram:
![InvertedIndex](https://github.com/NoshinNirvana916Prachi/Inverted-Index/assets/58959257/7cdd3da5-47f9-497b-a96c-8d89d8ed8dd7)

1. The RMI client looks up the RMI registry for the InvertedIndexService.
2. The RMI registry returns the reference to the InvertedIndexService.
3. The RMI client invokes the getInvertedIndex method on the service, passing the filename.
4. The RMI service processes the request by performing the indexing task, using either ForkJoinPool or Executors with Callable.
5. Once the indexing task is complete, the service returns the inverted index to the client.
6. The RMI client receives the inverted index and processes it to display the top 5 tokens with their locations.
